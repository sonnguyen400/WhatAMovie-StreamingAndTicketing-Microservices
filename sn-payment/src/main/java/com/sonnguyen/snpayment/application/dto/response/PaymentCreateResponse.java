package com.sonnguyen.snpayment.application.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class PaymentCreateResponse {
    private UUID id;
    private UUID merchantAccountId;
    private String externalOrderId;
    private UUID customerId;
    private String currency;
    private BigDecimal amount;
    private String description;
    private String metadata;
    private String code;
    private String qrCode;
    private String redirectLink;
}
