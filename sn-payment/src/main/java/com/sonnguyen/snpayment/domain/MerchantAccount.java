package com.sonnguyen.snpayment.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.snpayment.infrastructure.constant.PaymentGatewayType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class MerchantAccount extends AuditingDomain {
    private UUID id;
    private String name;
    private String config;
    private PaymentGatewayType paymentGatewayType;
    private Long version;
}
