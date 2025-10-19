package com.sonnguyen.snpayment.domain.command;

import com.sonnguyen.snpayment.infrastructure.constant.TransactionDomainType;
import com.sonnguyen.snpayment.infrastructure.constant.TransactionStatus;
import com.sonnguyen.snpayment.infrastructure.constant.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class TransactionCreateCommand {
    private String code;
    private TransactionType type;
    private BigDecimal amount;
    private String currency;
    private TransactionStatus status;
    private String gateway;
    private String gatewayPayload;
    private UUID domainId;
    private TransactionDomainType domainType;
}
