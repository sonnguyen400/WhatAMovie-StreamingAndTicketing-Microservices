package com.sonnguyen.snpayment.infrastructure.persistence.entity;

import com.sonnguyen.common.data.persistence.entity.AuditingEntity;
import com.sonnguyen.common.util.Validator;
import com.sonnguyen.snpayment.infrastructure.constant.TransactionDomainType;
import com.sonnguyen.snpayment.infrastructure.constant.TransactionStatus;
import com.sonnguyen.snpayment.infrastructure.constant.TransactionType;
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
@Table(name = "transaction_data", indexes = {
        @Index(name = "transaction_id_idx", columnList = "id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionEntity extends AuditingEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "domain_id")
    private UUID domainId;

    @Column(name = "domain_type")
    @Enumerated(EnumType.STRING)
    private TransactionDomainType domainType;

    @Column(name = "code")
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = Validator.Length.ENUM_MAX_LENGTH)
    private TransactionType type;

    @Column(name = "amount", nullable = false, precision = 18, scale = 9)
    private BigDecimal amount;

    @Column(name = "currency", nullable = false, length = Validator.Length.CURRENCY_MAX_LENGTH)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = Validator.Length.ENUM_MAX_LENGTH)
    private TransactionStatus status;

    @Column(name = "gateway")
    private String gateway;

    @Column(name = "gateway_payload", columnDefinition = "jsonb")
    private String gatewayPayload;

    @Column(name = "deleted")
    private Boolean deleted;

    @Version
    private Long version;
}
