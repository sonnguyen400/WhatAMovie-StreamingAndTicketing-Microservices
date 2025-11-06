package com.sonnguyen.snpayment.domain.command;

import com.sonnguyen.common.model.infrastructure.support.enums.CurrencyUnit;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDetailCreateCmd {
    private String description;
    private BigDecimal amount;
    private CurrencyUnit currencyUnit;
}
