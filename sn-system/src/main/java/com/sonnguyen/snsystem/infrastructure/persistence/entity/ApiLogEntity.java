package com.sonnguyen.snsystem.infrastructure.persistence.entity;

import com.sonnguyen.common.data.persistence.entity.AuditingEntity;
import com.sonnguyen.snsystem.domain.AuditContext;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "api_log_entity")
public class ApiLogEntity extends AuditingEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Transient
    private AuditContext audit;

    @Column(name = "http_method")
    private String httpMethod;

    @Column(name = "path")
    private String path;

    @Column(name = "route")
    private String route;

    @Column(name = "status_code")
    private Integer statusCode;

    @Column(name = "request_size_bytes")
    private Long requestSizeBytes;

    @Column(name = "response_size_bytes")
    private Long responseSizeBytes;

    @Column(name = "latency_ms")
    private Long latencyMs;

    @Column(name = "client_app")
    private String clientApp;

    @Column(name = "sampling")
    private String sampling;

    @Column(name = "path_params")
    private String pathParams;

    @Column(name = "query_params")
    private String queryParams;

    @Column(name = "headers")
    private String headers;
}
