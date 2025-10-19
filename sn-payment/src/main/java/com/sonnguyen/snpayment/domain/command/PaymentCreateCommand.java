package com.sonnguyen.snpayment.domain.command;

import com.sonnguyen.snpayment.infrastructure.constant.PaymentStatus;
import com.sonnguyen.snpayment.infrastructure.constant.TransactionStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class PaymentCreateCommand {
    private String externalOrderId;
    private UUID customerId;
    private String currency;
    private BigDecimal amount;
    private PaymentStatus status;
    private String description;
    private String metadata;
    private String code;
    private TransactionStatus transactionStatus;
    private UUID merchantAccountId;
    private String idempotencyKey;
    private Instant expiredAt;
}
