package com.sonnguyen.snpayment.application.dto.request;

import com.sonnguyen.common.model.application.request.Request;
import com.sonnguyen.snpayment.infrastructure.constant.PaymentStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
public class PaymentCreateRequest extends Request {

    @NotNull(message = "CUSTOMER_REQUIRED")
    private UUID customerId;

    @NotNull(message = "CURRENCY_CODE_REQUIRED")
    private String currency;

    private PaymentStatus status;

    private String description;

    private String metadata;

    @NotNull(message = "MERCHANT_ACCOUNT_REQUIRED")
    private UUID merchantAccountId;

    @NotNull(message = "IDEMPOTENCY_KEY_REQUIRED")
    private String idempotencyKey;

    private Instant expiredAt;

    @Valid
    @NotEmpty(message = "PAYMENT_DETAILS_REQUIRED")
    private List<PaymentDetailCreateRequest> details;
}
