package com.sonnguyen.snpayment.infrastructure.persistence.entity;

import com.sonnguyen.common.data.persistence.entity.AuditingEntity;
import com.sonnguyen.common.model.infrastructure.constant.DomainType;
import com.sonnguyen.common.util.Validator;
import com.sonnguyen.snpayment.infrastructure.constant.PaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "payment", indexes = {
        @Index(name = "payment_id_idx", columnList = "id"),
        @Index(name = "merchant_account_id_idx", columnList = "merchant_account_id"),
        @Index(name = "payment_customer_id_idx", columnList = "customer_id"),
        @Index(name = "payment_domain_id_idx", columnList = "domain_id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentEntity extends AuditingEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "merchant_account_id")
    private UUID merchantAccountId;

    @Column(name = "external_order_id")
    private String externalOrderId;

    @Column(name = "customer_id")
    private UUID customerId;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency;

    @Column(name = "amount", nullable = false, precision = 18, scale = 2)
    private BigDecimal amount;

    @Column(name = "status", length = Validator.Length.ENUM_MAX_LENGTH)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(name = "description", length = Validator.Length.DESCRIPTION_MAX_LENGTH)
    private String description;

    @Column(name = "metadata")
    private String metadata;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "code")
    private String code;

    @Column(name = "idempotency_key_id")
    private UUID idempotencyKeyId;

    @Column(name = "qr_code")
    private String qrCode;

    @Column(name = "redirect_link")
    private String redirectLink;

    @Column(name = "domain_id")
    private UUID domainId;

    @Column(name = "domain_type")
    @Enumerated(EnumType.STRING)
    private DomainType domainType;

    @Version
    private Long version;
}
