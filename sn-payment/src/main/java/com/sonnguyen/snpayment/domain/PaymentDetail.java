package com.sonnguyen.snpayment.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.model.infrastructure.support.enums.CurrencyUnit;
import com.sonnguyen.common.util.IdUtils;
import com.sonnguyen.snpayment.domain.command.PaymentDetailCreateCmd;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO for {@link com.sonnguyen.snpayment.infrastructure.persistence.entity.PaymentDetailEntity}
 */
@Getter
@AllArgsConstructor
public class PaymentDetail extends AuditingDomain {
    private UUID id;
    private UUID paymentId;
    private String description;
    private BigDecimal amount;
    private CurrencyUnit currencyUnit;

    public PaymentDetail(Payment payment, PaymentDetailCreateCmd paymentDetailCreateCmd){
        this.id = IdUtils.nextId();
        this.paymentId = payment.getId();
        this.description = paymentDetailCreateCmd.getDescription();
        this.amount = paymentDetailCreateCmd.getAmount();
        this.currencyUnit = paymentDetailCreateCmd.getCurrencyUnit();
    }
}
