package com.sonnguyen.snsystem.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

/**
 * DTO for {@link com.sonnguyen.snsystem.infrastructure.persistence.entity.ApiLogEntity}
 */
@Getter
@Setter(value = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiLog {
    private UUID id;                    // UUID hoặc string id
    private AuditContext audit;           // audit context
    private String httpMethod;            // GET, POST, ...
    private String path;                  // /api/products/{id}
    private String route;                 // tên route hoặc controller#method
    private String pathParams;
    private String queryParams;
    private String headers;  // chỉ lưu header cần thiết (Authorization => omit)
    private Integer statusCode;               // response status
    private Long requestSizeBytes;        // nếu muốn
    private Long responseSizeBytes;
    private Long latencyMs;               // thời gian xử lý
    private String clientApp;             // mobile/web/app version (nếu có)
    private String sampling;              // sampled / not-sampled
}
