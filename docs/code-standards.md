# Coding Standard

> **Mục đích**: Chuẩn hóa cách viết code trong hệ thống HCM để đảm bảo consistency, giúp developer và AI refactor đúng
> convention.
> **Nguồn**: Phân tích từ source code thực tế + best practices
> từ `@clean-code`, `@code-review-checklist`, `@testing-patterns`.
> **Cập nhật**: 2026-03-19

---

## 1. Naming Conventions

### 1.1 Files & Folders

| Loại                       | Convention                                          | Ví dụ                            |
|----------------------------|-----------------------------------------------------|----------------------------------|
| **Maven module**           | `hcm-{domain-name}` (kebab-case)                    | `hcm-time-management`, `hcm-iam` |
| **Java package**           | `com.evotek.{module}.{layer}.{sub}` (all lowercase) | `com.evotek.iam.domain.command`  |
| **Java class file**        | PascalCase                                          | `UserCommandServiceImpl.java`    |
| **SQL migration**          | `v{version}__{description}.sql` (Liquibase)         | `v1.0.1__add_worker_shift.sql`   |
| **Config files**           | `application-{profile}.yaml`                        | `application-dev.yaml`           |
| **Bounded context folder** | lowercase domain name                               | `workcalendar/`, `leave/`, `ot/` |

### 1.2 Classes

| Loại                      | Pattern / Suffix                    | Ví dụ                                    |
|---------------------------|-------------------------------------|------------------------------------------|
| REST Interface            | `*Resource`                         | `UserResource`, `RoleResource`           |
| REST Implementation       | `*ResourceImpl`                     | `UserResourceImpl`                       |
| Command Service Interface | `*CommandService`                   | `AccountCommandService`                  |
| Command Service Impl      | `*CommandServiceImpl`               | `AccountCommandServiceImpl`              |
| Query Service Interface   | `*QueryService`                     | `UserQueryService`                       |
| Query Service Impl        | `*QueryServiceImpl`                 | `UserQueryServiceImpl`                   |
| Domain Aggregate          | Danh từ đơn, **không suffix**       | `User`, `Worker`, `LeaveRequest`         |
| Domain Repository (IF)    | `*DomainRepository`                 | `UserDomainRepository`                   |
| Domain Repository (Impl)  | `*DomainRepositoryImpl`             | `UserDomainRepositoryImpl`               |
| JPA Entity                | `*Entity`                           | `UserEntity`, `RoleEntity`               |
| JPA Repository            | `*EntityRepository`                 | `UserEntityRepository`                   |
| Command Object            | `*Cmd` (`*CreateCmd`, `*UpdateCmd`) | `UserCreateCmd`                          |
| Request DTO               | `*Request`                          | `UserCreateRequest`, `UserSearchRequest` |
| Response DTO              | `*Response` or `*DTO`               | `UserDetailResponse`, `UserDTO`          |
| Error Enum                | `BadRequestError`, `NotFoundError`  | `BadRequestError.USER_INVALID`           |
| Event                     | `*Event`                            | `PersonnelCreatedEvent`                  |
| Scheduled Job             | `*Task` or `*Job`                   | `ResyncTimesheetTask`                    |
| Feign Client              | `*Client`                           | `NotificationClient`                     |
| Mapper                    | `*Mapper` (MapStruct)               | `UserMapper`                             |
| Config                    | `*Configuration`                    | `SecurityConfiguration`                  |
| Subscriber                | `*Subscriber`                       | `PersonnelSubscriber`                    |

### 1.3 Methods

**Domain Layer:**

| Pattern      | Naming                   | Ví dụ                                       |
|--------------|--------------------------|---------------------------------------------|
| State change | verb (business intent)   | `approve()`, `resign()`, `changePassword()` |
| Soft delete  | `deleted()` (past tense) | `user.deleted()`                            |
| Undelete     | `unDelete()`             | `userRole.unDelete()`                       |
| Enrich       | `enrich*`                | `enrichRoles()`, `enrichTenant()`           |
| Calculate    | `cal*` or `calculate*`   | `calLeaveBalances()`                        |

**Application Layer:**

| Pattern       | Naming                                         |
|---------------|------------------------------------------------|
| Tạo mới       | `create(request)`                              |
| Cập nhật      | `update(id, request)`                          |
| Xóa mềm       | `delete(id)`                                   |
| Kích hoạt/hủy | `active(id)` / `inactive(id)`                  |
| Phê duyệt     | `approve(id, request)` / `reject(id, request)` |
| Tìm kiếm      | `search(request)`                              |
| Lấy chi tiết  | `findById(id)` / `getById(id)`                 |
| Export        | `export(request, response)`                    |

### 1.4 Variables & Constants

| Loại            | Convention               | Ví dụ                                      |
|-----------------|--------------------------|--------------------------------------------|
| Local variables | camelCase, reveal intent | `userCount`, `isActive`                    |
| Boolean         | Question form            | `isDeleted`, `hasPermission`, `canApprove` |
| Constants       | SCREAMING_SNAKE_CASE     | `MAX_RETRY_COUNT`, `DEFAULT_PAGE_SIZE`     |
| Enum values     | SCREAMING_SNAKE_CASE     | `APPROVED`, `PENDING`, `REJECTED`          |
| UUID fields     | camelCase + `Id` suffix  | `workerId`, `tenantId`, `leaveRequestId`   |

### 1.5 Database

| Element        | Convention                  | Ví dụ                                         |
|----------------|-----------------------------|-----------------------------------------------|
| Table name     | snake_case, singular        | `user`, `leave_request`, `worker_shift`       |
| Column name    | snake_case                  | `created_by`, `last_modified_at`, `tenant_id` |
| FK column      | `{referenced_table}_id`     | `worker_id`, `org_unit_id`                    |
| Boolean column | `is_*` or verb past tense   | `deleted`, `is_active`                        |
| UUID column    | `columnDefinition = "uuid"` | —                                             |
| Timestamp      | `*_at` (Instant)            | `created_at`, `deleted_at`                    |

### 1.6 URLs và HTTP Methods

> ⚠️ **ĐẶC THÙ PROJECT**: Hệ thống **không dùng `PUT`/`PATCH`/`DELETE`** HTTP methods.

| Hành động     | HTTP Method     | URL Pattern                     | Ví dụ                         |
|---------------|-----------------|---------------------------------|-------------------------------|
| Tạo mới       | `POST`          | `/api/{resources}`              | `POST /api/users`             |
| **Cập nhật**  | `POST`          | `/api/{resources}/{id}/update`  | `POST /api/users/{id}/update` |
| **Xóa**       | `POST`          | `/api/{resources}/{id}/delete`  | `POST /api/users/{id}/delete` |
| **Kích hoạt** | `POST`          | `/api/{resources}/{id}/active`  | —                             |
| **Phê duyệt** | `POST`          | `/api/{resources}/{id}/approve` | —                             |
| Tìm theo IDs  | `POST`          | `/api/{resources}/find-by-ids`  | —                             |
| Import        | `POST`          | `/api/{resources}/import`       | —                             |
| Export        | `GET`           | `/api/{resources}/export`       | —                             |
| Search        | `GET`           | `/api/{resources}`              | `GET /api/users`              |
| Lấy chi tiết  | `GET`           | `/api/{resources}/{id}`         | `GET /api/users/{id}`         |
| Public API    | `GET/POST`      | `/api/public/{resources}`       | —                             |
| Internal S2S  | + `@ClientOnly` | `/api/{resources}/*`            | —                             |

**URL Naming Rules**: kebab-case, số nhiều (`leave-requests`, `salary-periods`).

### 1.7 Permission Naming

```
{resource}.{action}
→ user.create, user.view, role.update, leave-request.approve
```

---

## 2. Code Style

### 2.1 Formatting Rules

| Rule                | Value                                            |
|---------------------|--------------------------------------------------|
| **Indentation**     | 4 spaces (Java standard)                         |
| **Max line length** | ~120 characters (IntelliJ default)               |
| **Braces**          | K&R style (opening brace on same line)           |
| **Blank lines**     | 1 blank line between methods, 2 between sections |
| **Encoding**        | UTF-8                                            |
| **Line ending**     | LF (Unix)                                        |

### 2.2 Import Ordering

```java
// 1. Java/Jakarta standard library
import java.time.Instant;
import java.util.*;
import jakarta.persistence.*;

// 2. Third-party libraries
import org.springframework.stereotype.Service;
import org.mapstruct.Mapper;
import lombok.*;

// 3. Project internal (com.evotek.*)
import com.evotek.common.model.*;
import com.evotek.iam.domain.*;
```

**Rules**:

- No wildcard imports (`*`) trừ `java.util.*` (chấp nhận)
- Không import unused classes
- Group imports theo package prefix, tách nhóm bằng blank line

### 2.3 File Structure Template (Class Order)

```java
// 1. Package declaration
package com.evotek.iam.domain;

// 2. Imports (theo thứ tự 2.2)

// 3. Lombok annotations (@Getter, @Setter, @Builder, etc.)
// 4. Class declaration
public class User extends AuditableDomain {

    // 5. Static constants
    private static final String DEFAULT_ROLE = "USER";

    // 6. Instance fields (grouped logically)
    private UUID id;
    private String username;
    // ...

    // 7. Constructors (command constructor first)
    public User(UserCreateCmd cmd, List<Role> roles) { ... }

    // 8. Business methods (public first, then package-private)
    public void changePassword(...) { ... }
    public void approve() { ... }
    public void deleted() { ... }

    // 9. Enrich methods
    public void enrichRoles(List<Role> roles) { ... }

    // 10. Private helper methods
    private void validateInvariants() { ... }
}
```

---

## 3. Java / Spring Standards

### 3.1 Lombok Conventions

| Annotation                                   | Khi nào dùng                                              |
|----------------------------------------------|-----------------------------------------------------------|
| `@Getter`                                    | Tất cả class                                              |
| `@Setter`                                    | Entity và DTO. Domain dùng `@Setter(AccessLevel.PRIVATE)` |
| `@RequiredArgsConstructor`                   | Service class (constructor injection)                     |
| `@NoArgsConstructor` + `@AllArgsConstructor` | Domain, Command, DTO                                      |
| `@SuperBuilder`                              | Domain và Command (builder inheritance)                   |
| `@Data`                                      | Command objects only                                      |
| `@EqualsAndHashCode(callSuper = false)`      | Domain class                                              |
| `@Slf4j`                                     | Service và infrastructure class                           |

### 3.2 Dependency Injection — Constructor Only

```java
// ✅ ĐÚNG — Lombok constructor injection
@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl {
    private final UserDomainRepository userDomainRepository;
    private final NotificationClient notificationClient;
}

// ❌ SAI — field injection
@Autowired
private UserDomainRepository userDomainRepository;
```

### 3.3 Error Handling Pattern

```
Business Rule Violation:
  → throw new ResponseException(BadRequestError.USER_INVALID)
        ↓ ExceptionHandleAdvice handles globally
        ↓ ResponseEntity<ErrorResponse>(status=400)

System Error:
  → unhandled RuntimeException
        ↓ ExceptionHandleAdvice catches
        ↓ ResponseEntity<ErrorResponse>(status=500)
```

**Error Enum Pattern** (module-specific — `infrastructure/support/exception/`):

```java
@Getter
public enum BadRequestError implements ResponseError {
    USER_EMAIL_EXITED(40001001, "Email exited"),
    WRONG_PASSWORD(40001004, "Wrong password"),
    ROLE_INVALID(40001035, "Role is invalid {0}"),  // {0} = placeholder
    ;
    private final Integer code;   // 5-8 chữ số, duy nhất trong service
    private final String message;
}
```

**Rules**:

- ✅ `throw new ResponseException(BadRequestError.XXX)` — luôn dùng enum
- ✅ `throw new ResponseException(BadRequestError.XXX, param)` — với params
- ❌ `throw new RuntimeException("msg")` — KHÔNG dùng generic exception
- ❌ Business logic validation trong Controller — phải trong Domain layer

### 3.4 Validation

| Layer              | Mechanism                                                   |
|--------------------|-------------------------------------------------------------|
| **Request DTO**    | Bean Validation: `@NotNull`, `@NotBlank`, `@Size`, `@Valid` |
| **Search params**  | `@ValidatePaging` custom annotation (validate sort fields)  |
| **Business rules** | `ResponseException` thrown in Domain methods                |

### 3.5 Null Handling

```java
// ✅ ĐÚNG — null-safe check
if (Boolean.TRUE.equals(this.deleted)) { ... }
if (Objects.nonNull(user.getEmail())) { ... }
if (StringUtils.isNotBlank(request.getKeyword())) { ... }

// ✅ ĐÚNG — Optional cho return types
Optional<User> findByUsername(String username);

// ❌ SAI — direct null comparison cho Boolean
if (this.deleted == true) { ... }  // NPE risk với Boolean wrapper
```

### 3.6 Transaction Rules

```java
// CommandService: class-level @Transactional
@Service
@Transactional
public class XxxCommandServiceImpl { ... }

// QueryService: readOnly
@Service
@Transactional(readOnly = true)
public class XxxQueryServiceImpl { ... }

// Non-transactional method override (e.g., export):
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public void exportToFile(...) { ... }
```

### 3.7 ID Generation

```java
// ✅ ĐÚNG — luôn dùng IdUtils
this.id = IdUtils.nextId();  // → UUID

// ❌ SAI
this.id = UUID.randomUUID();
// ❌ SAI — auto-increment
```

---

## 4. Module / Bounded Context Patterns

### 4.1 Layered Architecture (per bounded context)

```
{bounded-context}/
├── presentation/rest/     ← REST interface + impl → gọi Application Service
├── application/
│   ├── dto/request/       ← Input DTOs (Bean Validation)
│   ├── dto/response/      ← Output DTOs
│   ├── mapper/            ← MapStruct: Request → Command
│   └── service/impl/      ← CommandServiceImpl + QueryServiceImpl
├── domain/
│   ├── {Aggregate}.java   ← Business logic, invariants
│   ├── command/            ← XxxCreateCmd, XxxUpdateCmd
│   └── repository/        ← DomainRepository interface
└── infrastructure/
    ├── persistence/
    │   ├── entity/         ← JPA Entity
    │   ├── repository/     ← EntityRepository (Spring Data)
    │   └── impl/           ← DomainRepositoryImpl
    └── support/
        ├── config/         ← @Configuration
        └── exception/      ← Error enums
```

### 4.2 Layer Responsibilities

| Layer              | Trách nhiệm                                                  | KHÔNG được làm                            |
|--------------------|--------------------------------------------------------------|-------------------------------------------|
| **Presentation**   | REST contract, `@PreAuthorize`, `@Valid`, map to service     | Business logic                            |
| **Application**    | Orchestration, transaction, load→validate→domain→save→events | Business invariants                       |
| **Domain**         | Business invariants, state changes, throw ResponseException  | Framework deps (`@Service`, `@Autowired`) |
| **Infrastructure** | DB access, entity mapping, external adapters, configs        | Business logic                            |

### 4.3 Domain Entity Rules

```java
@Setter(AccessLevel.PRIVATE)   // ← PRIVATE setters — enforce encapsulation
@Getter
public class LeaveRequest extends AuditableDomain {

    // Constructor per command — domain object tự validate
    public LeaveRequest(LeaveRequestCreateCmd cmd, Worker worker, ...) {
        this.id = IdUtils.nextId();
        // validate business rules here
        // set fields from cmd
    }

    // Business method — NOT a setter
    public void approve() {
        if (this.status != Status.PENDING) {
            throw new ResponseException(BadRequestError.INVALID_STATUS);
        }
        this.status = Status.APPROVED;
    }

    // Soft delete
    public void deleted() {
        this.deleted = true;
        this.deletedAt = Instant.now();
    }
}
```

### 4.4 Application Service Pattern

```java
@Service
@RequiredArgsConstructor
@Transactional
public class LeaveRequestCommandServiceImpl implements LeaveRequestCommandService {

    private final LeaveRequestDomainRepository domainRepo;
    private final LeaveRequestMapper mapper;

    @Override
    public LeaveRequest create(LeaveRequestCreateRequest request) {
        // 1. Map request → command
        LeaveRequestCreateCmd cmd = mapper.from(request);

        // 2. Load dependencies (Feign, other repos)
        Worker worker = workerRemoteRepo.findById(cmd.getWorkerId());

        // 3. Create domain object (business validation inside)
        LeaveRequest leaveRequest = new LeaveRequest(cmd, worker, ...);

        // 4. Save
        domainRepo.save(leaveRequest);

        // 5. Side effects (async events, notifications)
        eventPublisher.publish(channel, new LeaveCreatedEvent(leaveRequest));

        return leaveRequest;
    }
}
```

### 4.5 Controller Pattern (Interface + Impl)

```java
// *Resource.java — interface (contract definition)
@RequestMapping("/api")
@Validated
public interface LeaveRequestResource {

    @PostMapping("/leave-requests")
    @PreAuthorize("hasPermission(null, 'leave-request.create')")
    Response<LeaveRequestDTO> create(@RequestBody @Valid LeaveRequestCreateRequest request);
}

// *ResourceImpl.java — implementation
@RestController
@RequiredArgsConstructor
public class LeaveRequestResourceImpl implements LeaveRequestResource {

    private final LeaveRequestCommandService commandService;
    private final LeaveRequestQueryService queryService;

    @Override
    public Response<LeaveRequestDTO> create(LeaveRequestCreateRequest request) {
        LeaveRequest result = commandService.create(request);
        return Response.of(mapper.toDTO(result));
    }
}
```

---

## 5. API & Data Layer

### 5.1 Response Format

```java
// Single object
Response<T> { int code; String error; String message; T data; }

// Paged list
PagingResponse<T> { int code; String error; String message; List<T> data;
                     long totalCount; int page; int pageSize; }

// Validation error
InvalidInputResponse { int code; String error; String message;
                        Set<FieldErrorResponse> errors; }

// Void response (delete, active, approve)
Response<Void>

// Boolean response
Response<Boolean>
```

### 5.2 Persistence Patterns

```
DomainRepository (interface, domain/)
    └─► DomainRepositoryImpl (infrastructure/)
            ├─ EntityRepository (JPA, extends JpaRepository)
            └─ Maps domain ↔ entity bidirectionally
```

**Entity base class**: All entities extend `TenancyEntity` (from `common-persistence`):

- `createdBy`, `createdAt`, `lastModifiedBy`, `lastModifiedAt`
- `tenantId` (multi-tenancy, extracted from JWT)

**Column naming**: snake_case, UUID columns use `columnDefinition = "uuid"`.

**Soft delete**: `deleted = true` + `deletedAt`. No physical DELETE. All queries filter `deleted = false`.

### 5.3 Cross-Service Data

| Pattern             | Rule                                                                                       |
|---------------------|--------------------------------------------------------------------------------------------|
| **Worker data**     | NEVER store in other services. Only `workerId` reference. Enrich via `hcm-pa.enrichList()` |
| **Org data**        | Reference `orgUnitId`, fetch details from `hcm-organization`                               |
| **S2S calls**       | OpenFeign clients defined in `common-hcm-client`                                           |
| **Pull-based sync** | hcm-payroll pulls from hcm-time (`sync-for-payroll` endpoint)                              |

---

## 6. Async & Scheduled Jobs

### 6.1 Redis Pub/Sub Events

```java
// Publish (trong CommandService)
redisPublisher.publish(PersonnelChannel.PERSONNEL, new PersonnelEvent(workerId));

// Subscribe (infrastructure, @Component)
@Component
public class PersonnelSubscriber implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
        PersonnelEvent event = deserialize(message.getBody());
        accountService.createUserFromWorker(event);
    }
}
```

### 6.2 db-scheduler Background Jobs

```java
@Component
public class ResyncTimesheetTask implements RecurringTask<Void> {
    // Schedule config: "0 */30 * * * *" (mỗi 30 phút)
}
```

**Rules**:

- Dùng `db-scheduler` (PostgreSQL-backed) — **KHÔNG** dùng Spring `@Scheduled`
- Job class đặt trong `infrastructure/job/` hoặc `application/job/`
- Schedule nên cấu hình trong `application.yaml`

---

## 7. Logging Standards

```java
@Slf4j
public class UserCommandServiceImpl {
    log.info("Creating user: {}", cmd.getUsername());       // Business milestone
    log.debug("Roles resolved: {}", roleIds);              // Technical detail
    log.warn("Failed request {}: {}", uri, e.getMessage()); // Non-fatal
    log.error("DB error {}: {}", uri, e.getMessage(), e);   // System error + stacktrace
}
```

| Level   | Khi nào                                       | Stacktrace |
|---------|-----------------------------------------------|------------|
| `INFO`  | Business milestones (create, approve, delete) | ❌          |
| `DEBUG` | Technical details (disabled in production)    | ❌          |
| `WARN`  | Business errors (validation, not found)       | ❌          |
| `ERROR` | System errors (DB, external service)          | ✅          |

**KHÔNG log**: password, JWT token, PII. Dùng SLF4J placeholder `{}`, không dùng `+` concat.

---

## 8. Security & Authorization

```java
// API-level security
@PreAuthorize("hasPermission(null, 'user.create')")

// S2S only endpoint
@ClientOnly  // AOP: chỉ service token, không phải browser

// Public endpoint (no auth)
@GetMapping("/api/public/files/{id}")
```

**JWT**: RS256, validate via JWK endpoint (self-validate, no IAM call per request).
**Token revocation**: Redis blacklist with TTL.
**Password**: BCrypt encoding. `lastAuthChangeAt` invalidates old refresh tokens.

---

## 9. Jackson / JSON Conventions

| Type               | Serialization                            |
|--------------------|------------------------------------------|
| `Instant` → JSON   | Epoch milliseconds (custom deserializer) |
| `LocalDate` → JSON | `yyyy-MM-dd`                             |
| `UUID` → JSON      | Custom deserializer (trim whitespace)    |
| Null fields        | `@JsonInclude(NON_NULL)` on DTOs         |
| Sensitive fields   | `@JsonIgnore` on `password`, `userRoles` |

---

## 10. OpenFeign Client Conventions

```java
// Defined in common-hcm-client
@FeignClient(name = "hcm-notification", url = "${service.notification.url}")
public interface NotificationClient {
    @PostMapping("/api/notifications/send")
    Response<Void> send(@RequestBody NotificationRequest request);
}
```

Inject và sử dụng như dependency bình thường qua `@RequiredArgsConstructor`.

---

## 11. Testing Standards

### 11.1 Test Location & Naming

| Element            | Convention                                                        |
|--------------------|-------------------------------------------------------------------|
| **Test directory** | `src/test/java/com/evotek/{module}/domain/`                       |
| **Test class**     | `{DomainClass}Test.java` (e.g., `UserTest.java`, `RoleTest.java`) |
| **Test method**    | `test{MethodName}` hoặc descriptive (`@DisplayName`)              |
| **Framework**      | JUnit 5 (`jupiter`), Spring Boot Test                             |
| **Assertions**     | JUnit `assertEquals`, `assertTrue`, `assertAll`                   |

### 11.2 Testing Pyramid (Áp dụng cho HCM)

```
        /\          E2E (None currently)
       /  \         → Future: Playwright/Selenium
      /----\
     /      \       Integration (Minimal)
    /--------\      → @SpringBootTest for API tests
   /          \
  /------------\    Unit Tests (Primary target)
                    → Domain layer: aggregates, business rules
```

### 11.3 What to Test vs NOT Test

| ✅ Test                                      | ❌ Don't Test                       |
|---------------------------------------------|------------------------------------|
| Domain aggregate business methods           | Framework code (Spring, JPA)       |
| Constructor validation (command → domain)   | Simple getters/setters (Lombok)    |
| State transitions (approve, reject, cancel) | Third-party libraries              |
| Edge cases (null, empty, boundary)          | Infrastructure wiring              |
| Error throwing (ResponseException)          | MapStruct mappers (auto-generated) |
| Calculation methods (`calLeaveBalances`)    | OpenFeign client calls             |

### 11.4 AAA Pattern (Arrange-Act-Assert)

```java
@Test
@DisplayName("Should approve leave request when status is PENDING")
void testApproveLeaveRequest() {
    // Arrange — set up test data
    LeaveRequest leaveRequest = LeaveRequest.builder()
            .id(IdUtils.nextId())
            .status(Status.PENDING)
            .build();

    // Act — execute business method
    leaveRequest.approve();

    // Assert — verify outcome
    assertEquals(Status.APPROVED, leaveRequest.getStatus());
}

@Test
@DisplayName("Should throw when approving non-PENDING request")
void testApproveNonPendingThrows() {
    // Arrange
    LeaveRequest leaveRequest = LeaveRequest.builder()
            .status(Status.DRAFT)
            .build();

    // Act & Assert
    assertThrows(ResponseException.class, () -> leaveRequest.approve());
}
```

### 11.5 Test Data Best Practices

```java
// ✅ Use constants for fixed test values
private static final String USERNAME = "admin";
private static final String PASSWORD = "123456";
private static final UUID ROLE_ID = IdUtils.nextId();

// ✅ Use @BeforeAll / @BeforeEach for complex setup
@BeforeAll
static void init() {
    user = User.builder()
            .username(USERNAME)
            .build();
}

// ✅ Use Builder pattern (domain classes have @SuperBuilder)
User user = User.builder()
        .id(IdUtils.nextId())
        .username("testuser")
        .deleted(false)
        .build();
```

### 11.6 Mocking Conventions

| Mock                                  | Don't Mock                  |
|---------------------------------------|-----------------------------|
| OpenFeign clients (S2S calls)         | The domain class under test |
| Redis cache/pub-sub                   | Pure domain methods         |
| External services (MinIO, FCM)        | Simple value objects        |
| `DomainRepository` (in service tests) | In-memory data structures   |

```java
// Service-level test with mocks
@ExtendWith(MockitoExtension.class)
class UserCommandServiceImplTest {
    @Mock private UserDomainRepository userDomainRepository;
    @Mock private NotificationClient notificationClient;
    @InjectMocks private UserCommandServiceImpl service;

    @Test
    void testCreateUser() {
        when(userDomainRepository.findByUsername("admin")).thenReturn(Optional.empty());
        // ...
    }
}
```

### 11.7 Coverage Requirements

| Target              | Minimum | Ideal |
|---------------------|---------|-------|
| Domain layer        | **80%** | 95%   |
| Application service | **60%** | 80%   |
| Infrastructure      | 30%     | 50%   |
| Presentation        | 20%     | 40%   |

> ⚠️ **Thực trạng**: Hầu hết tests hiện tại đã bị **commented out** do domain model thay đổi. Priority: viết lại tests
> cho domain layer trước.

---

## 12. Git Conventions

### 12.1 Branch Naming

```
{type}/{ticket-id}-{short-description}

Ví dụ:
  feature/HCM-123-add-leave-balance
  fix/HCM-456-fix-timesheet-resync
  hotfix/HCM-789-fix-payroll-calc
  refactor/HCM-101-clean-god-class
  chore/upgrade-spring-boot
```

| Type        | Mục đích                              |
|-------------|---------------------------------------|
| `feature/`  | Tính năng mới                         |
| `fix/`      | Bug fix                               |
| `hotfix/`   | Critical production fix               |
| `refactor/` | Code improvement (no behavior change) |
| `chore/`    | Build, CI, dependency updates         |
| `docs/`     | Documentation only                    |

### 12.2 Commit Message (Conventional Commits)

```
{type}({scope}): {description}

[optional body]
[optional footer]
```

| Type       | Mô tả                                 |
|------------|---------------------------------------|
| `feat`     | New feature                           |
| `fix`      | Bug fix                               |
| `refactor` | Code restructure (no behavior change) |
| `docs`     | Documentation                         |
| `test`     | Adding/updating tests                 |
| `chore`    | Build, CI, dependencies               |
| `perf`     | Performance improvement               |
| `style`    | Code formatting (no logic change)     |

**Scope** = module name: `iam`, `time`, `payroll`, `pa`, `common`, `talent`, etc.

**Examples**:

```
feat(time): add leave balance carry-over calculation
fix(payroll): fix salary slip rounding error for OT hours
refactor(iam): extract password validation from AccountServiceImpl
test(time): add unit tests for WorkCalendar domain
docs: update system-architecture.md with C3 diagrams
chore(common): upgrade Spring Boot to 3.2.6
```

### 12.3 PR Guidelines

**PR Title**: Same format as commit message.

**PR Description should include**:

1. **What**: Mô tả thay đổi (WHAT, not HOW)
2. **Why**: Business context / ticket link
3. **Testing**: Tests added/updated, manual testing done
4. **Breaking changes**: API changes, DB migration, config changes

**Merge Strategy**: Squash merge (1 commit per PR on target branch).

### 12.4 Branch Protection

| Rule             | Value             |
|------------------|-------------------|
| Target branch    | `develop`, `main` |
| Required reviews | ≥ 1 approval      |
| CI must pass     | Build + SonarQube |
| Force push       | ❌ Disabled        |

---

## 13. Code Review Checklist

### 13.1 Correctness

- [ ] Code thực hiện đúng yêu cầu (requirement/ticket)
- [ ] Edge cases được xử lý (null, empty list, boundary values)
- [ ] Error handling đúng pattern (`ResponseException` + Error Enum)
- [ ] Không có obvious bugs (off-by-one, wrong comparison, missing null check)
- [ ] Soft delete: `deleted = true`, không xóa vật lý
- [ ] UUID: tạo bằng `IdUtils.nextId()`, không `UUID.randomUUID()`

### 13.2 Architecture & Design

- [ ] Business logic NẰM TRONG domain layer, không phải controller/service
- [ ] Layer boundaries respected (không import ngược layer)
- [ ] Cross-service data qua REST API (OpenFeign), không truy cập DB trực tiếp
- [ ] Worker data chỉ lưu `workerId`, enrich via `hcm-pa`
- [ ] Naming conventions tuân thủ (Section 1)
- [ ] URL pattern: `POST /{id}/update`, không dùng `PUT`/`DELETE`

### 13.3 Code Quality

- [ ] Tên biến/method rõ ràng, tự giải thích (không cần comment)
- [ ] Không có code duplication (DRY)
- [ ] Function ngắn (< 20 dòng lý tưởng, tối đa 50)
- [ ] Nesting tối đa 2 levels (dùng guard clauses / early return)
- [ ] Không có magic numbers (dùng named constants)
- [ ] Không có commented-out code trong production
- [ ] `@Setter(AccessLevel.PRIVATE)` trên domain classes
- [ ] Constructor injection (`@RequiredArgsConstructor`), không `@Autowired`

### 13.4 Performance

- [ ] Không có N+1 queries (batch load, `enrichList()` thay vì per-item)
- [ ] Pagination cho search APIs (`PagingResponse`)
- [ ] Redis cache cho lookup frequency (authority, config)
- [ ] Không load toàn bộ collection khi chỉ cần count/exists
- [ ] Lazy loading cho JPA associations nếu không cần eager
- [ ] `@Transactional(readOnly=true)` cho QueryService

### 13.5 Security

- [ ] `@PreAuthorize` trên mọi endpoint (trừ `/api/public/*`)
- [ ] `@ClientOnly` cho S2S-only endpoints
- [ ] Input validated: `@Valid` + Bean Validation trên Request DTOs
- [ ] Không log sensitive data (password, token, PII)
- [ ] Không hardcode secrets (dùng `ENC(...)` + Jasypt)
- [ ] `tenant_id` filter trong mọi query (multi-tenancy)
- [ ] SQL injection prevention: dùng parameterized queries, không concat SQL strings

### 13.6 Database & Migration

- [ ] Liquibase migration file cho schema changes
- [ ] Column names: snake_case
- [ ] UUID columns: `columnDefinition = "uuid"`
- [ ] Backward-compatible migration (add column nullable, NOT alter/drop)
- [ ] Index cho columns dùng trong WHERE/JOIN thường xuyên

### 13.7 Review Comment Prefixes

```
🔴 BLOCKING: Must fix before merge     → Security, data loss, broken API
🟡 SUGGESTION: Should fix              → Performance, code quality
🟢 NIT: Nice to have                   → Naming, formatting, style
❓ QUESTION: Need clarification         → Business logic, design intent
```

---

## 14. Anti-Patterns — Tuyệt Đối Tránh

```java
// ❌ SAI — PUT/PATCH/DELETE HTTP verbs
@PutMapping("/users/{id}")     → ✅ @PostMapping("/users/{id}/update")
@DeleteMapping("/users/{id}")  → ✅ @PostMapping("/users/{id}/delete")

// ❌ SAI — business logic trong Controller
@PostMapping("/users")
public Response<User> create(@RequestBody UserCreateRequest req) {
    if (userRepo.existsByEmail(req.getEmail())) { throw ...; } // ← WRONG
}
→ ✅ Validate trong Domain constructor hoặc Application service

// ❌ SAI — lưu worker data trong service khác
@Entity class TimesheetEntity {
    private String workerName;      // ← duplicate data
}
→ ✅ Chỉ lưu workerId, enrich khi query

// ❌ SAI — generic exception
throw new RuntimeException("User is invalid");
→ ✅ throw new ResponseException(BadRequestError.USER_INVALID);

// ❌ SAI — field injection
@Autowired private UserRepo repo;
→ ✅ @RequiredArgsConstructor + private final UserRepo repo;

// ❌ SAI — sử dụng Kafka/RabbitMQ (không có trong hệ thống)
→ ✅ Redis Pub/Sub cho async, OpenFeign cho sync

// ❌ SAI — DTO truyền trực tiếp vào domain
new User(userCreateRequest);
→ ✅ Map Request → Command: new User(userCreateCmd, roles);
```

---

## 15. Refactoring Guidelines

### Khi refactor domain method

```
1. Đọc test trong src/test/java/.../domain/ → hiểu behavior
2. Đọc Application service gọi method đó → hiểu context
3. Không thay đổi public method signature
4. Giữ nguyên throw exceptions (ExceptionHandleAdvice phụ thuộc)
5. Sau refactor → chạy unit tests
```

### Khi thêm field mới vào entity

```
1. Thêm @Column vào *Entity.java
2. Tạo Liquibase migration file
3. Thêm field vào domain class (với @Setter(PRIVATE))
4. Thêm field vào *Cmd nếu cần set qua command
5. Cập nhật mapper trong *DomainRepositoryImpl
6. Cập nhật DTO nếu cần expose ra API
```

### Khi thêm API endpoint mới

```
1. Thêm method vào *Resource.java interface (với @PreAuthorize)
2. Implement trong *ResourceImpl.java
3. Thêm method vào *CommandService / *QueryService interface
4. Implement trong *ServiceImpl.java
5. Không skip @PreAuthorize (trừ /api/public/)
```

### Nguyên tắc tuyệt đối

| Nguyên tắc                         | Mô tả                                              |
|------------------------------------|----------------------------------------------------|
| **Không vi phạm module boundary**  | Cross-service data qua REST API only               |
| **Không thay đổi API contract**    | `*Resource.java` = public API, không đổi signature |
| **Không di chuyển business logic** | Domain layer logic stays in domain layer           |
| **POST cho mutations**             | Convention chủ động, không phải bug                |
| **UUID everywhere**                | `IdUtils.nextId()`                                 |
| **Soft delete**                    | `deleted = true`, không xóa vật lý                 |

---

## 16. Code Quality Notes

### Violations hiện có (cần refactor dần)

| Issue                       | Location                         | Mô tả                                    |
|-----------------------------|----------------------------------|------------------------------------------|
| **God class**               | `hcm-iam — AccountServiceImpl`   | 17+ injected deps                        |
| **Commented-out code**      | `User.java`                      | Dead code, nên xóa                       |
| **Duplicate error codes**   | `BadRequestError.java` (hcm-iam) | Code trùng lặp                           |
| **Field injection**         | `ExceptionHandleAdvice`          | `@Autowired` (nên constructor injection) |
| **Disabled tests**          | All modules                      | Tests bị comment out toàn bộ             |
| **Constructor duplication** | `User.java`                      | 8 constructors gần giống nhau            |

---

*Coding Standards — HCM by Evotek. Tổng hợp từ source code thực tế + best practices. Cập nhật: 2026-03-19.*
