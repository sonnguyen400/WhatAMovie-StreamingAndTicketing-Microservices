package com.sonnguyen.snticket.domain;

import com.sonnguyen.common.model.domain.TenancyDomain;
import com.sonnguyen.common.model.infrastructure.support.enums.CurrencyUnit;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class TicketSurCharge extends TenancyDomain {
    private UUID id;
    private UUID surPriceId;
    private UUID ticketId;
    private String description;
    private BigDecimal amount;
    private CurrencyUnit currencyUnit;
}
