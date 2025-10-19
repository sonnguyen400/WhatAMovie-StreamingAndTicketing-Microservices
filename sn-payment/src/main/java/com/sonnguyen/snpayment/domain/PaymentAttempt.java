package com.sonnguyen.snpayment.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.snpayment.infrastructure.constant.AttemptStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class PaymentAttempt extends AuditingDomain {
    private UUID id;
    private UUID paymentId;
    private String gateway;
    private Integer attemptIndex;
    private AttemptStatus status;
    private String gatewayRequest;
    private String gatewayResponse;
    private String responseCode;
    private String responseMessage;
    private BigDecimal amount;
}
