package com.sonnguyen.snpayment.infrastructure.persistence.entity;

import com.sonnguyen.common.data.persistence.entity.AuditingEntity;
import com.sonnguyen.common.util.Validator;
import com.sonnguyen.snpayment.infrastructure.constant.AttemptStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "payment_attempt",
        indexes = {
                @Index(name = "payment_attempt_id_idx", columnList = "id"),
                @Index(name = "payment_id_attempts_idx", columnList = "payment_id"),
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentAttemptEntity extends AuditingEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "payment_id", nullable = false)
    private UUID paymentId;

    @Column(name = "gateway", nullable = false)
    private String gateway;

    @Column(name = "attempt_index")
    private Integer attemptIndex;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = Validator.Length.ENUM_MAX_LENGTH)
    private AttemptStatus status;

    @Column(columnDefinition = "jsonb")
    private String gatewayRequest;

    @Column(name = "gateway_response")
    private String gatewayResponse;

    @Column(name = "gateway_response_code")
    private String responseCode;

    @Column(name = "gateway_response_message")
    private String responseMessage;

    @Column(name = "amount")
    private BigDecimal amount;
}
