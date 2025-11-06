package com.sonnguyen.snticket.domain;

import com.sonnguyen.common.model.domain.TenancyDomain;
import com.sonnguyen.snticket.infrastructure.support.enums.TicketType;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class Ticket extends TenancyDomain {
    private UUID id;
    private String code;
    private UUID showtimeId;
    private UUID seatId;
    private Boolean status;
    private String qrCode;
    private String transactionId;
    private TicketType ticketType;
    private UUID memberId;
    private UUID basePriceId;
    private BigDecimal amount;
    private BigDecimal currencyUnit;
}
