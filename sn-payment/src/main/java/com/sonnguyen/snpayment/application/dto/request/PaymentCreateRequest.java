package com.sonnguyen.snpayment.application.dto.request;

import com.sonnguyen.snpayment.infrastructure.constant.PaymentStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
public class PaymentCreateRequest {

    @NotNull(message = "CUSTOMER_REQUIRED")
    private UUID customerId;

    @NotNull(message = "CURRENCY_CODE_REQUIRED")
    private String currency;

    @NotNull(message = "AMOUNT_REQUIRED")
    @Min(value = 0, message = "AMOUNT_GREATER_EQUALS_ZERO")
    private BigDecimal amount;

    private PaymentStatus status;

    private String description;

    private String metadata;

    @NotNull(message = "MERCHANT_ACCOUNT_REQUIRED")
    private UUID merchantAccountId;

    @NotNull(message = "IDEMPOTENCY_KEY_REQUIRED")
    private String idempotencyKey;

    private Instant expiredAt;
}
