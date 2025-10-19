package com.sonnguyen.snpayment.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.util.IdUtils;
import com.sonnguyen.snpayment.domain.command.TransactionCreateCommand;
import com.sonnguyen.snpayment.infrastructure.constant.TransactionDomainType;
import com.sonnguyen.snpayment.infrastructure.constant.TransactionStatus;
import com.sonnguyen.snpayment.infrastructure.constant.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class Transaction extends AuditingDomain {
    private UUID id;
    private UUID domainId;
    private TransactionDomainType domainType;
    private String code;
    private TransactionType type;
    private BigDecimal amount;
    private String currency;
    private TransactionStatus status;
    private String gateway;
    private String gatewayPayload;
    private Boolean deleted;
    private Long version;

    public Transaction(TransactionCreateCommand cmd) {
        this.id = IdUtils.nextId();
        this.domainId = cmd.getDomainId();
        this.domainType = cmd.getDomainType();
        this.code = cmd.getCode();
        this.type = cmd.getType();
        this.amount = cmd.getAmount();
        this.currency = cmd.getCurrency();
        this.status = cmd.getStatus();
        this.gateway = cmd.getGateway();
        this.gatewayPayload = cmd.getGatewayPayload();
        this.deleted = false;
    }
}
