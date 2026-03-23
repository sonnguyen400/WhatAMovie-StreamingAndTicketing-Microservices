# Code Guide — HCM Developer Handbook

> **Mục đích**: Hướng dẫn developer thêm tính năng mới vào project HCM.
> Mọi ví dụ code đều lấy từ codebase thực tế.
> **Đọc thêm**: [code-standards.md](./code-standards.md) — Coding conventions chi
> tiết | [codebase-summary.md](./codebase-summary.md) — Tổng quan hệ thống.
> **Cập nhật**: 2026-03-19

---

## 1. Quick Start

### 1.1 Prerequisites

| Tool           | Version                     | Check command      |
|----------------|-----------------------------|--------------------|
| **Java**       | 21 (Eclipse Temurin)        | `java -version`    |
| **Maven**      | 3.9.5+ (Wrapper included)   | `./mvnw -version`  |
| **PostgreSQL** | 14+                         | `psql --version`   |
| **Redis**      | 7+                          | `redis-cli ping`   |
| **Docker**     | 24+                         | `docker --version` |
| **Git**        | 2.40+                       | `git --version`    |
| **IDE**        | IntelliJ IDEA (khuyến nghị) | —                  |

### 1.2 IntelliJ IDEA Setup

1. Import project: `File → Open → d:\hcm\{module-name}`
2. Set JDK: `File → Project Structure → SDK → temurin-21`
3. Enable annotation processing: `Settings → Build → Compiler → Annotation Processors → Enable`
4. Install plugins: **Lombok**, **MapStruct Support**, **Spring Boot Assistant**
5. Import code style: IntelliJ defaults (4 spaces, K&R braces, ~120 char line length)

### 1.3 Clone & Run Module lần đầu

```bash
# 1. Clone mono-repo
git clone https://git.evotek.vn/evohcm/1.wip/source-code/hcm-be/hcm-platform.git
cd hcm-platform

# 2. Build shared library trước (BẮT BUỘC — các module phụ thuộc vào hcm-common)
cd hcm-common
mvn clean install -DskipTests

# 3. Chạy 1 module cụ thể (ví dụ: hcm-tenant)
cd ../hcm-tenant
./mvnw spring-boot:run -Dspring-boot.run.profiles=local

# Hoặc build Docker image
docker build -t hcm-tenant:dev .
```

### 1.4 Các lệnh thường dùng

| Lệnh                                                      | Mô tả                          |
|-----------------------------------------------------------|--------------------------------|
| `./mvnw spring-boot:run -Dspring-boot.run.profiles=local` | Chạy local                     |
| `./mvnw clean package -DskipTests`                        | Build JAR                      |
| `./mvnw test`                                             | Chạy unit tests                |
| `./mvnw compile`                                          | Compile + MapStruct generation |
| `./mvnw liquibase:update`                                 | Apply DB migrations            |
| `docker build -t hcm-{module}:dev .`                      | Build Docker image             |

### 1.5 Services phụ thuộc

> ⚠️ Chạy **tối thiểu** các services sau trước khi start bất kỳ module nào:

| Service        | Lý do              | Default port |
|----------------|--------------------|--------------|
| **PostgreSQL** | Database chính     | 5432         |
| **Redis**      | Cache + Pub/Sub    | 6379         |
| **hcm-iam**    | JWT authentication | 8080         |
| **Eureka**     | Service discovery  | 8761         |

---

## 2. Kiến trúc Module — Hiểu trước khi Code

### 2.1 Cấu trúc thư mục (per Bounded Context)

```
src/main/java/com/evotek/{bounded-context}/
├── presentation/
│   └── rest/
│       ├── ContactUsResource.java          ← Interface (API contract)
│       └── impl/
│           └── ContactUsResourceImpl.java  ← Implementation
├── application/
│   ├── dto/
│   │   ├── request/ContactUsCreateRequest.java  ← Input DTO (@Valid)
│   │   └── response/ContactUsDTO.java           ← Output DTO
│   ├── mapper/
│   │   └── ContactUsMapper.java            ← MapStruct: Request → Cmd
│   └── service/
│       ├── ContactUsCommandService.java    ← Interface
│       ├── ContactUsQueryService.java      ← Interface
│       └── impl/
│           ├── ContactUsCommandServiceImpl.java  ← Write operations
│           └── ContactUsQueryServiceImpl.java    ← Read operations
├── domain/
│   ├── ContactUs.java                      ← Aggregate Root (business logic)
│   ├── command/
│   │   ├── ContactUsCreateCmd.java         ← Command object
│   │   └── ContactUsHandleCmd.java
│   ├── query/
│   │   └── ContactUsSearchQuery.java       ← Query object
│   └── repository/
│       └── ContactUsDomainRepository.java  ← Repository interface
└── infrastructure/
    ├── domainrepository/
    │   └── ContactUsDomainRepositoryImpl.java  ← Repository impl
    ├── persistence/
    │   ├── entity/ContactUsEntity.java     ← JPA Entity
    │   ├── mapper/ContactUsEntityMapper.java    ← MapStruct: Domain ↔ Entity
    │   └── repository/
    │       ├── ContactUsEntityRepository.java   ← Spring Data JPA
    │       └── impl/ContactUsEntityRepositoryImpl.java  ← Custom queries
    └── support/
        ├── exception/
        │   ├── BadRequestError.java        ← Error codes
        │   └── NotFoundError.java
        └── constant/Constants.java
```

### 2.2 Data Flow (Request → Response)

```
HTTP Request
    ↓
[1] ContactUsResource (interface — @PreAuthorize, @Valid)
    ↓
[2] ContactUsResourceImpl (route to service)
    ↓
[3] ContactUsCommandServiceImpl (@Transactional)
    ├── autoMapper.from(request) → ContactUsCreateCmd
    ├── new ContactUs(cmd)       → Domain validation trong constructor
    └── domainRepo.save(contactUs)
                ↓
[4] ContactUsDomainRepositoryImpl
    ├── entityMapper.toEntity(domain)  → ContactUsEntity
    └── jpaRepository.save(entity)     → PostgreSQL
                ↓
Response<ContactUs> ← mapped to JSON
```

---

## 3. Thêm Domain mới (Feature hoàn chỉnh)

> **Scenario**: Thêm domain "Announcement" (Thông báo nội bộ) vào module `hcm-system`.

### Step 1: Tạo Command Object

📁 `domain/command/AnnouncementCreateCmd.java`

```java
// Lấy mẫu từ: hcm-tenant/domain/command/ContactUsCreateCmd.java
package com.evotek.system.domain.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnouncementCreateCmd {

    private String title;
    private String content;
    private String priority;   // HIGH, MEDIUM, LOW
}
```

### Step 2: Tạo Domain Aggregate

📁 `domain/Announcement.java`

```java
// Lấy mẫu từ: hcm-tenant/domain/ContactUs.java
package com.evotek.system.domain;

import com.evotek.common.domain.AuditableDomain;
import com.evotek.common.util.IdUtils;
import com.evotek.system.domain.command.AnnouncementCreateCmd;
import com.evotek.system.domain.command.AnnouncementUpdateCmd;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Setter(AccessLevel.PRIVATE)       // ← PRIVATE setters — BẮT BUỘC
@Getter
public class Announcement extends AuditableDomain {

    private UUID id;
    private String title;
    private String content;
    private String priority;
    private Boolean deleted;

    // Constructor per Command — business validation ở đây
    public Announcement(AnnouncementCreateCmd cmd) {
        this.id = IdUtils.nextId();         // ← Luôn dùng IdUtils, KHÔNG UUID.randomUUID()
        this.title = cmd.getTitle();
        this.content = cmd.getContent();
        this.priority = cmd.getPriority();
        this.deleted = Boolean.FALSE;
    }

    // Business method — state change
    public void update(AnnouncementUpdateCmd cmd) {
        this.title = cmd.getTitle();
        this.content = cmd.getContent();
        this.priority = cmd.getPriority();
    }

    // Soft delete — KHÔNG xóa vật lý
    public void deleted() {
        this.deleted = Boolean.TRUE;
    }
}
```

### Step 3: Tạo Domain Repository Interface

📁 `domain/repository/AnnouncementDomainRepository.java`

```java
package com.evotek.system.domain.repository;

import com.evotek.system.domain.Announcement;
import java.util.UUID;

public interface AnnouncementDomainRepository {
    void save(Announcement announcement);
    Announcement getById(UUID id);
}
```

### Step 4: Tạo JPA Entity

📁 `infrastructure/persistence/entity/AnnouncementEntity.java`

```java
package com.evotek.system.infrastructure.persistence.entity;

import com.evotek.common.entity.TenancyEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "announcement")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AnnouncementEntity extends TenancyEntity {

    @Id
    @Column(name = "id", columnDefinition = "uuid")    // ← uuid column type
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "priority")
    private String priority;

    @Column(name = "deleted")
    private Boolean deleted;
}
```

### Step 5: Tạo Entity-Domain Mapper (MapStruct)

📁 `infrastructure/persistence/mapper/AnnouncementEntityMapper.java`

```java
package com.evotek.system.infrastructure.persistence.mapper;

import com.evotek.common.mapper.EntityMapper;
import com.evotek.system.domain.Announcement;
import com.evotek.system.infrastructure.persistence.entity.AnnouncementEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnnouncementEntityMapper
        extends EntityMapper<Announcement, AnnouncementEntity> {
    // MapStruct auto-generates: toDomain(entity), toEntity(domain)
}
```

### Step 6: Tạo JPA Repository (Spring Data)

📁 `infrastructure/persistence/repository/AnnouncementEntityRepository.java`

```java
package com.evotek.system.infrastructure.persistence.repository;

import com.evotek.system.infrastructure.persistence.entity.AnnouncementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AnnouncementEntityRepository
        extends JpaRepository<AnnouncementEntity, UUID> {
}
```

### Step 7: Implement Domain Repository

📁 `infrastructure/domainrepository/AnnouncementDomainRepositoryImpl.java`

```java
// Lấy mẫu từ: hcm-tenant/infastructure/domainrepository/ContactUsDomainRepositoryImpl.java
package com.evotek.system.infrastructure.domainrepository;

import com.evotek.common.exception.ResponseException;
import com.evotek.common.mapper.EntityMapper;
import com.evotek.common.webapp.support.AbstractDomainRepository;
import com.evotek.system.domain.Announcement;
import com.evotek.system.domain.repository.AnnouncementDomainRepository;
import com.evotek.system.infrastructure.persistence.entity.AnnouncementEntity;
import com.evotek.system.infrastructure.support.exception.NotFoundError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AnnouncementDomainRepositoryImpl
        extends AbstractDomainRepository<Announcement, AnnouncementEntity, UUID>
        implements AnnouncementDomainRepository {

    protected AnnouncementDomainRepositoryImpl(
            JpaRepository<AnnouncementEntity, UUID> jpaRepository,
            EntityMapper<Announcement, AnnouncementEntity> mapper) {
        super(jpaRepository, mapper);
    }

    @Override
    public Announcement getById(UUID id) {
        return this.findById(id)
                .orElseThrow(() -> new ResponseException(
                        NotFoundError.ANNOUNCEMENT_NOT_FOUND));
    }
}
```

### Step 8: Tạo Error Codes

📁 `infrastructure/support/exception/NotFoundError.java` — thêm mới:

```java
ANNOUNCEMENT_NOT_FOUND(40401xxx,"Announcement not found"),
```

📁 `infrastructure/support/exception/BadRequestError.java` — thêm nếu cần:

```java
ANNOUNCEMENT_TITLE_REQUIRED(40001xxx,"Announcement title is required"),
```

### Step 9: Tạo Request DTO

📁 `application/dto/request/AnnouncementCreateRequest.java`

```java
package com.evotek.system.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AnnouncementCreateRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 255)
    private String title;

    @NotBlank(message = "Content is required")
    private String content;

    private String priority;
}
```

### Step 10: Tạo Application Service

📁 `application/service/AnnouncementCommandService.java` (Interface)

```java
package com.evotek.system.application.service;

import com.evotek.system.application.dto.request.AnnouncementCreateRequest;
import com.evotek.system.domain.Announcement;
import java.util.UUID;

public interface AnnouncementCommandService {
    Announcement create(AnnouncementCreateRequest request);
    Announcement update(UUID id, AnnouncementUpdateRequest request);
    void delete(UUID id);
}
```

📁 `application/service/impl/AnnouncementCommandServiceImpl.java`

```java
// Lấy mẫu từ: hcm-tenant/application/service/impl/ContactUsCommandServiceImpl.java
package com.evotek.system.application.service.impl;

import com.evotek.system.application.dto.request.AnnouncementCreateRequest;
import com.evotek.system.application.mapper.AnnouncementMapper;
import com.evotek.system.application.service.AnnouncementCommandService;
import com.evotek.system.domain.Announcement;
import com.evotek.system.domain.command.AnnouncementCreateCmd;
import com.evotek.system.domain.repository.AnnouncementDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor              // ← Constructor injection, KHÔNG @Autowired
@Transactional                        // ← Class-level cho CommandService
public class AnnouncementCommandServiceImpl implements AnnouncementCommandService {

    private final AnnouncementDomainRepository domainRepository;
    private final AnnouncementMapper mapper;

    @Override
    public Announcement create(AnnouncementCreateRequest request) {
        // 1. Map Request → Command
        AnnouncementCreateCmd cmd = mapper.from(request);

        // 2. Create Domain (business validation inside constructor)
        Announcement announcement = new Announcement(cmd);

        // 3. Save
        domainRepository.save(announcement);

        return announcement;
    }

    @Override
    public void delete(UUID id) {
        Announcement announcement = domainRepository.getById(id);
        announcement.deleted();          // ← Soft delete
        domainRepository.save(announcement);
    }
}
```

### Step 11: Tạo REST Controller

📁 `presentation/rest/AnnouncementResource.java` (Interface)

```java
// Lấy mẫu từ: hcm-tenant/presentation/rest/ContactUsResource.java
package com.evotek.system.presentation.rest;

import com.evotek.common.dto.response.Response;
import com.evotek.system.application.dto.request.AnnouncementCreateRequest;
import com.evotek.system.domain.Announcement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Announcement Resource")
@RequestMapping("/api")
@Validated
public interface AnnouncementResource {

    @Operation(summary = "Create announcement")
    @PostMapping("/announcements")
    @PreAuthorize("hasPermission(null, 'announcement.create')")    // ← BẮT BUỘC
    Response<Announcement> create(
            @RequestBody @Valid AnnouncementCreateRequest request);

    @Operation(summary = "Get announcement by ID")
    @GetMapping("/announcements/{id}")
    @PreAuthorize("hasPermission(null, 'announcement.view')")
    Response<Announcement> getById(@PathVariable("id") UUID id);

    @Operation(summary = "Delete announcement")
    @PostMapping("/announcements/{id}/delete")                     // ← POST, KHÔNG DELETE
    @PreAuthorize("hasPermission(null, 'announcement.delete')")
    Response<Void> delete(@PathVariable("id") UUID id);
}
```

📁 `presentation/rest/impl/AnnouncementResourceImpl.java`

```java
package com.evotek.system.presentation.rest.impl;

import com.evotek.common.dto.response.Response;
import com.evotek.system.application.dto.request.AnnouncementCreateRequest;
import com.evotek.system.application.service.AnnouncementCommandService;
import com.evotek.system.domain.Announcement;
import com.evotek.system.presentation.rest.AnnouncementResource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AnnouncementResourceImpl implements AnnouncementResource {

    private final AnnouncementCommandService commandService;

    @Override
    public Response<Announcement> create(AnnouncementCreateRequest request) {
        return Response.of(commandService.create(request));
    }

    @Override
    public Response<Void> delete(UUID id) {
        commandService.delete(id);
        return Response.ok();
    }
}
```

### Step 12: Tạo SQL Migration

📁 `sql/YYYYMMDD_create_announcement.sql`

```sql
-- Announcement table
CREATE TABLE IF NOT EXISTS announcement
(
    id
    UUID
    PRIMARY
    KEY,
    title
    VARCHAR
(
    255
) NOT NULL,
    content TEXT,
    priority VARCHAR
(
    20
) DEFAULT 'MEDIUM',
    deleted BOOLEAN DEFAULT FALSE,
    tenant_id UUID NOT NULL, -- multi-tenancy (BẮT BUỘC)
    created_by UUID,
    created_at TIMESTAMP DEFAULT NOW
(
),
    last_modified_by UUID,
    last_modified_at TIMESTAMP DEFAULT NOW
(
)
    );

-- Index cho tìm kiếm
CREATE INDEX IF NOT EXISTS idx_announcement_tenant_id ON announcement(tenant_id);
CREATE INDEX IF NOT EXISTS idx_announcement_deleted ON announcement(deleted);
```

### Step 13: Summary — Files cần tạo

| #  | File                                          | Layer          | Mô tả                   |
|----|-----------------------------------------------|----------------|-------------------------|
| 1  | `AnnouncementCreateCmd.java`                  | Domain         | Command object          |
| 2  | `Announcement.java`                           | Domain         | Aggregate root          |
| 3  | `AnnouncementDomainRepository.java`           | Domain         | Repository interface    |
| 4  | `AnnouncementEntity.java`                     | Infrastructure | JPA Entity              |
| 5  | `AnnouncementEntityMapper.java`               | Infrastructure | MapStruct Entity↔Domain |
| 6  | `AnnouncementEntityRepository.java`           | Infrastructure | Spring Data JPA         |
| 7  | `AnnouncementDomainRepositoryImpl.java`       | Infrastructure | Repository impl         |
| 8  | `BadRequestError.java` / `NotFoundError.java` | Infrastructure | Error codes (update)    |
| 9  | `AnnouncementCreateRequest.java`              | Application    | Request DTO             |
| 10 | `AnnouncementMapper.java`                     | Application    | MapStruct Request→Cmd   |
| 11 | `AnnouncementCommandService.java`             | Application    | Service interface       |
| 12 | `AnnouncementCommandServiceImpl.java`         | Application    | Service impl            |
| 13 | `AnnouncementResource.java`                   | Presentation   | REST interface          |
| 14 | `AnnouncementResourceImpl.java`               | Presentation   | REST impl               |
| 15 | `YYYYMMDD_create_announcement.sql`            | SQL            | DB migration            |

> **Total**: 15 files cho 1 domain CRUD hoàn chỉnh (13 Java + 1 SQL + 1 update error enum).

---

## 4. Thêm API Endpoint mới (vào domain hiện có)

### 4.1 Checklist nhanh

```
1. ✅ Thêm method vào *Resource.java (interface) — với @PreAuthorize
2. ✅ Implement trong *ResourceImpl.java
3. ✅ Thêm method vào *CommandService / *QueryService interface
4. ✅ Implement trong *ServiceImpl.java
5. ✅ Nếu cần Request mới → tạo *Request.java (@Valid)
6. ✅ Nếu cần Command mới → tạo *Cmd.java
7. ✅ Nếu business logic → thêm method vào Domain class
8. ❌ KHÔNG skip @PreAuthorize (trừ /api/public/*)
```

### 4.2 Ví dụ: Thêm endpoint "handle" cho ContactUs

**Bước 1** — Domain method (từ `ContactUs.java` thực tế):

```java
public void handle(ContactUsHandleCmd cmd){
        this.note=cmd.getNote();
        this.status=ContactUsStatus.DONE;
        }
```

**Bước 2** — Service method (từ `ContactUsCommandServiceImpl.java` thực tế):

```java
@Override
@Transactional
public ContactUs handle(UUID id,ContactUsHandleRequest request){
        ContactUs contactUs=this.contactUsDomainRepository.getById(id);
        ContactUsHandleCmd cmd=this.autoMapper.from(request);
        contactUs.handle(cmd);
        this.contactUsDomainRepository.save(contactUs);
        return contactUs;
        }
```

**Bước 3** — Controller (từ `ContactUsResource.java` thực tế):

```java
@Operation(summary = "Handle contact us")
@PostMapping("/contact-us/{id}/handle")
@PreAuthorize("hasPermission(null, 'contact_us.update')")
Response<ContactUs> handle(
@PathVariable("id") UUID id,
@RequestBody ContactUsHandleRequest request);
```

### 4.3 Pattern cho các loại endpoint phổ biến

| Loại         | HTTP       | URL Pattern                      | Response              |
|--------------|------------|----------------------------------|-----------------------|
| Tạo mới      | `POST`     | `/api/{resources}`               | `Response<T>`         |
| Cập nhật     | `POST`     | `/api/{resources}/{id}/update`   | `Response<T>`         |
| Xóa mềm      | `POST`     | `/api/{resources}/{id}/delete`   | `Response<Void>`      |
| Phê duyệt    | `POST`     | `/api/{resources}/{id}/approve`  | `Response<T>`         |
| Tìm kiếm     | `GET`      | `/api/{resources}`               | `PagingResponse<DTO>` |
| Chi tiết     | `GET`      | `/api/{resources}/{id}`          | `Response<DTO>`       |
| Export       | `GET`      | `/api/{resources}/export`        | `void` (stream)       |
| Import       | `POST`     | `/api/{resources}/import`        | `Response<T>`         |
| Batch action | `POST`     | `/api/{resources}/approve/batch` | `Response<Void>`      |
| Public       | `POST/GET` | `/api/public/{resources}`        | — (no auth)           |

> ⚠️ **Quy tắc tuyệt đối**: Dùng `POST` cho tất cả mutations. **KHÔNG** dùng `PUT`/`PATCH`/`DELETE`.

---

## 5. Thêm Approval Workflow

> Dùng `common-workflow`. Xem module `hcm-time-management` (Timesheet, LeaveRequest) hoặc `hcm-talent-management` (
> RecruitmentProposal, RecruitmentRequest) làm mẫu.

### 5.1 Workflow Flow

```
DRAFT → PENDING_APPROVAL → APPROVED
  ↓          ↓
  (edit)   REJECTED → (edit) → PENDING_APPROVAL
```

### 5.2 Cần thêm

1. **POM**: Thêm dependency `common-workflow` nếu chưa có
2. **Domain**: Thêm `status` field, methods `requestApproval()`, `approve()`, `rejectApproval()`
3. **Service**: Inject `ApprovalWorkFlowCommandService`, gọi workflow engine
4. **API**: Endpoints `request-approval`, `approve`, `reject-approval`
5. **SQL**: Thêm cột `status`, `approval_status`

---

## 6. Tích hợp Service khác (Inter-Service Communication)

### 6.1 Gọi service khác qua OpenFeign

Feign clients được định nghĩa sẵn trong `common-hcm-client`:

```java
// Đã có sẵn trong common-hcm-client
@FeignClient(name = "hcm-notification", url = "${service.notification.url}")
public interface NotificationClient {
    @PostMapping("/api/notifications/send")
    Response<Void> send(@RequestBody NotificationRequest request);
}
```

**Sử dụng trong service:**

```java
@Service
@RequiredArgsConstructor
public class MyServiceImpl {
    private final NotificationClient notificationClient;  // ← inject như bình thường

    public void doSomething() {
        // Gọi service khác
        notificationClient.send(new NotificationRequest(...));
    }
}
```

### 6.2 Lấy dữ liệu Worker

> ⚠️ **NEVER** lưu worker data (name, email) trong service khác. Chỉ lưu `workerId`.

```java
// Enrich worker data khi cần hiển thị
List<WorkerResponse> workers=workerRemoteRepo.enrichList(workerIds);
```

### 6.3 Config service URLs

📁 `application-{profile}.yaml`:

```yaml
service:
  iam:
    url: http://hcm-iam       # Kubernetes service name
  notification:
    url: http://hcm-notification
  organization:
    url: http://hcm-organization-management
```

---

## 7. Thêm Scheduled Job (Background Task)

### 7.1 Sử dụng db-scheduler (KHÔNG dùng @Scheduled)

```java
// Tham khảo: hcm-time-management/common/config/TaskConfiguration.java
@Component
public class MyTaskConfiguration {

    @Bean
    public Task<Void> myRecurringTask(MyService myService) {
        return Tasks.recurring("my-recurring-task", FixedDelay.ofMinutes(30))
                .execute((taskInstance, executionContext) -> {
                    myService.doPeriodicWork();
                });
    }
}
```

**Quy tắc**:

- Dùng `db-scheduler` — PostgreSQL-backed, distributed, persistent
- Khai báo trong `TaskConfiguration` class
- KHÔNG dùng `@Scheduled` (Spring) — không distributed-safe

---

## 8. Thêm Excel Import/Export

### 8.1 Export

```java
// Service method
@Transactional(propagation = Propagation.NOT_SUPPORTED)  // ← KHÔNG transaction cho export
public void export(HttpServletResponse response,SearchRequest request){
        List<DataDTO> data=queryService.search(request);
        // Use common-excel for Apache POI operations
        ExcelUtils.writeToResponse(response,data,"export.xlsx");
        }
```

### 8.2 Import

```java
// Controller
@PostMapping("/announcements/import")
@PreAuthorize("hasPermission(null, 'announcement.create')")
Response<ImportResult> importFromExcel(@RequestParam("file") MultipartFile file);
```

---

## 9. Thêm Redis Pub/Sub Event

### 9.1 Publish Event

```java
// Trong CommandServiceImpl
redisPublisher.publish("CHANNEL_NAME",new MyEvent(entityId));
```

### 9.2 Subscribe to Event

```java
// Tham khảo: hcm-tenant/presentation/queue/IAMSubscriber.java
@Component
public class MySubscriber implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
        MyEvent event = deserialize(message.getBody());
        // Handle event
    }
}
```

Cấu hình channel trong `RedisSubscriberConfig.java`.

---

## 10. Thêm Field mới vào Entity hiện có

### Checklist step-by-step

```
1. ✅ Tạo SQL migration: ALTER TABLE ... ADD COLUMN ...
2. ✅ Thêm field vào *Entity.java (@Column)
3. ✅ Thêm field vào Domain class (với @Setter(PRIVATE) — đã có class-level)
4. ✅ Thêm field vào *Cmd nếu set qua command
5. ✅ MapStruct mapper tự động pick up (chỉ cần compile lại)
6. ✅ Cập nhật DTO nếu cần expose ra API
7. ✅ Cập nhật Request nếu cần nhận từ client
```

### SQL Migration file

```sql
-- sql/YYYYMMDD_add_priority_to_announcement.sql
ALTER TABLE announcement
    ADD COLUMN IF NOT EXISTS priority VARCHAR (20) DEFAULT 'MEDIUM';
```

> ⚠️ **Backward-compatible**: Luôn dùng `ADD COLUMN` (nullable hoặc có default). KHÔNG `ALTER COLUMN` type
> hoặc `DROP COLUMN` trong lần đầu.

---

## 11. Testing Guide

### 11.1 Unit Test cho Domain aggregate

```java
// Tham khảo: code-standards.md Section 11.4
@Test
@DisplayName("Should create announcement with valid command")
void testCreateAnnouncement(){
        // Arrange
        AnnouncementCreateCmd cmd=AnnouncementCreateCmd.builder()
        .title("Test Title")
        .content("Test Content")
        .priority("HIGH")
        .build();

        // Act
        Announcement announcement=new Announcement(cmd);

        // Assert
        assertNotNull(announcement.getId());
        assertEquals("Test Title",announcement.getTitle());
        assertEquals(Boolean.FALSE,announcement.getDeleted());
        }

@Test
@DisplayName("Should soft delete announcement")
void testDeleteAnnouncement(){
        // Arrange
        Announcement announcement=Announcement.builder()
        .id(IdUtils.nextId())
        .deleted(Boolean.FALSE)
        .build();

        // Act
        announcement.deleted();

        // Assert
        assertEquals(Boolean.TRUE,announcement.getDeleted());
        }
```

### 11.2 Chạy tests

```bash
# Chạy tất cả tests
./mvnw test

# Chạy test class cụ thể
./mvnw test -Dtest=AnnouncementTest

# Chạy với coverage report
./mvnw test jacoco:report
```

### 11.3 Cần test / Không cần test

| ✅ Test                             | ❌ Không cần test                   |
|------------------------------------|------------------------------------|
| Domain constructor validation      | Lombok getters/setters             |
| Business methods (approve, delete) | MapStruct mappers (auto-generated) |
| State transitions                  | Spring framework code              |
| Edge cases (null, empty)           | OpenFeign clients                  |
| Error throwing (ResponseException) | Infrastructure wiring              |

---

## 12. Checklist trước khi tạo PR

### Code Quality

- [ ] Code follows [code-standards.md](./code-standards.md)
- [ ] `@Setter(AccessLevel.PRIVATE)` trên domain classes
- [ ] Constructor injection (`@RequiredArgsConstructor`), KHÔNG `@Autowired`
- [ ] `IdUtils.nextId()` cho UUID, KHÔNG `UUID.randomUUID()`
- [ ] Soft delete (`deleted = true`), KHÔNG physical DELETE
- [ ] `POST` cho mutations, KHÔNG `PUT`/`PATCH`/`DELETE`

### Security

- [ ] `@PreAuthorize` trên mọi endpoint (trừ `/api/public/*`)
- [ ] `@Valid` trên Request DTOs
- [ ] Không log sensitive data (password, token, PII)
- [ ] `tenant_id` filter trong queries (multi-tenancy)

### Database

- [ ] SQL migration file cho schema changes
- [ ] Column names: snake_case
- [ ] UUID columns: `columnDefinition = "uuid"`
- [ ] Backward-compatible migration (add nullable, KHÔNG alter/drop)

### Testing

- [ ] Unit tests cho domain business logic
- [ ] Tests passing: `./mvnw test`
- [ ] No lint/compile errors: `./mvnw compile`

### Documentation

- [ ] Changelog updated (nếu new feature/breaking change)
- [ ] Swagger annotations (@Operation, @Tag)
- [ ] Error codes documented trong `BadRequestError`/`NotFoundError`

### Git

- [ ] Branch name: `feature/HCM-xxx-short-description`
- [ ] Commit message: `feat(module): description`
- [ ] Self-review completed
- [ ] Screenshots/recordings (nếu UI changes)

---

## 13. Troubleshooting

### 13.1 Lỗi thường gặp

| Lỗi                              | Nguyên nhân                                      | Fix                                     |
|----------------------------------|--------------------------------------------------|-----------------------------------------|
| `MapStruct mapper not generated` | Chưa compile                                     | `./mvnw compile`                        |
| `Bean not found`                 | Missing `@Service`/`@Component`                  | Thêm annotation                         |
| `Unauthorized 401`               | Missing `@PreAuthorize` hoặc sai permission name | Check permission                        |
| `Table not found`                | Chưa apply migration                             | Chạy Liquibase                          |
| `Feign client timeout`           | Service chưa start                               | Start service dependency                |
| `tenant_id is null`              | Request thiếu JWT                                | Check token                             |
| `IdUtils.nextId() NPE`           | Sai import                                       | Import `com.evotek.common.util.IdUtils` |

### 13.2 Debug tips

```bash
# Xem logs
tail -f logs/hcm-{module}.log

# Check health
curl http://localhost:{port}/actuator/health

# Check Eureka registration
curl http://localhost:8761/eureka/apps

# Check Redis
redis-cli ping
redis-cli keys "hcm:*"

# Check DB
psql -h localhost -U postgres -d hcm_{module} -c "SELECT * FROM {table} LIMIT 5;"
```

### 13.3 Common Compilation Issues

```bash
# MapStruct not generating — force recompile
./mvnw clean compile

# Lombok not working — regenerate
# IntelliJ: File → Invalidate Caches → Restart

# Dependency not found — install common first
cd ../hcm-common && mvn clean install -DskipTests
```

### 13.4 Multi-module Build Order

```bash
# Khi thay đổi common library:
1. cd hcm-common → mvn clean install -DskipTests
2. cd hcm-{your-module} → ./mvnw clean compile

# Khi chạy từ đầu:
1. hcm-common (install)
2. hcm-iam (start — cho auth)
3. hcm-service-discovery (start — Eureka)
4. hcm-{your-module} (start)
```

---

## 14. Quick Reference Card

### File Naming

| Suffix                         | Layer                        | Ví dụ                           |
|--------------------------------|------------------------------|---------------------------------|
| `*Resource.java`               | Presentation (interface)     | `ContactUsResource`             |
| `*ResourceImpl.java`           | Presentation (impl)          | `ContactUsResourceImpl`         |
| `*CommandService.java`         | Application (interface)      | `ContactUsCommandService`       |
| `*CommandServiceImpl.java`     | Application (impl)           | `ContactUsCommandServiceImpl`   |
| `*QueryService.java`           | Application (interface)      | `ContactUsQueryService`         |
| No suffix                      | Domain (aggregate)           | `ContactUs`                     |
| `*Cmd.java`                    | Domain (command)             | `ContactUsCreateCmd`            |
| `*Entity.java`                 | Infrastructure (JPA)         | `ContactUsEntity`               |
| `*EntityMapper.java`           | Infrastructure (MapStruct)   | `ContactUsEntityMapper`         |
| `*EntityRepository.java`       | Infrastructure (Spring Data) | `ContactUsEntityRepository`     |
| `*DomainRepository.java`       | Domain (interface)           | `ContactUsDomainRepository`     |
| `*DomainRepositoryImpl.java`   | Infrastructure (impl)        | `ContactUsDomainRepositoryImpl` |
| `*Request.java`                | Application (DTO in)         | `ContactUsCreateRequest`        |
| `*DTO.java` / `*Response.java` | Application (DTO out)        | `ContactUsDTO`                  |

### Layer Rules (ONE-WAY dependency)

```
Presentation → Application → Domain ← Infrastructure
     ↓              ↓          ↑            ↑
  (REST)    (Orchestration)  (Logic)  (DB, External)
```

> **Domain** KHÔNG depend vào bất kỳ layer nào. Infrastructure implements Domain interfaces.

---

*Code Guide — HCM Developer Handbook by Evotek. Cập nhật: 2026-03-19.*
