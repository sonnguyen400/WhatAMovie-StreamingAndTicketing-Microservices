package com.sonnguyen.snsystem.domain;

import com.sonnguyen.common.model.infrastructure.support.enums.ResourceCode;
import com.sonnguyen.common.model.infrastructure.support.enums.ResourceScope;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class AuditContext {
    private UUID traceId;            // trace id liên kết trace/log/trace system (opentelemetry)
    private UUID spanId;             // span id nếu có
    private UUID requestId;         // request id do client/gateway phát sinh
    private UUID userId;            // user id (nullable nếu guest)
    private UUID sessionId;         // session id
    private UUID tenantId;          // tenant/organization id
    private UUID clientId;
    private UUID client;
    private String username;          // tên user hoặc email
    private String serviceName;       // tên service/gateway/微service
    private String instanceId;        // instance id (container/pod id)
    private String ip;                // client IP
    private String userAgent;         // raw user agent
    private String device;            // parsed device info (mobile/desktop)
    private String locale;            // ngôn ngữ
    private Instant timestamp;        // thời điểm log (nên luôn set)
    private String result;                // "SUCCESS", "ERROR", "VALIDATION_ERROR"
    private String errorMessage;          // nếu có
    private String errorStacktrace;       // nếu có (ngắn gọn)
    private ResourceCode resourceCode;    // mã định danh resource (nếu có)
    private ResourceScope resourceScope;  // scope của resource (nếu có)
    private String metadata; // extensible: e.g., featureFlags
}
