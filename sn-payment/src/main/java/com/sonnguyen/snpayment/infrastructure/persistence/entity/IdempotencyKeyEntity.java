package com.sonnguyen.snpayment.infrastructure.persistence.entity;

import com.sonnguyen.common.data.persistence.entity.AuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "idempotency_key", indexes = {
        @Index(name = "idempotency_id_idx", columnList = "id"),
        @Index(name = "idempotency_key_idx", columnList = "key"),
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdempotencyKeyEntity extends AuditingEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true, nullable = false)
    private String key;

    @Column(name = "owner_service")
    private String ownerService;

    @Column(name = "expires_at")
    private Instant expiredAt;

    @Column(name = "response")
    private String response;

    @Column(name = "deleted")
    private Boolean deleted;
}
