package com.sonnguyen.snpayment.application.dto.request;

import com.sonnguyen.common.model.application.request.Request;
import com.sonnguyen.common.model.infrastructure.support.enums.CurrencyUnit;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class PaymentDetailCreateRequest extends Request {
    private String description;

    @NotNull(message = "AMOUNT_REQUIRED")
    private BigDecimal amount;

    @NotNull(message = "CURRENCY_UNIT_REQUIRED")
    private CurrencyUnit currencyUnit;
}
