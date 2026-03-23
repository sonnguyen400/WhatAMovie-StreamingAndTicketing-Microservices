package com.sonnguyen.snpayment.infrastructure.persistence.entity;

import com.sonnguyen.common.data.persistence.entity.AuditingEntity;
import com.sonnguyen.common.model.infrastructure.support.enums.CurrencyUnit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "payment_detail_entity")
public class PaymentDetailEntity extends AuditingEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "payment_id")
    private UUID paymentId;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "currency_unit")
    @Enumerated(EnumType.STRING)
    private CurrencyUnit currencyUnit;
}
