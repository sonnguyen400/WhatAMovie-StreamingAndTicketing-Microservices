package com.sonnguyen.snpayment.infrastructure.adapter.impl;

import com.sonnguyen.snpayment.infrastructure.adapter.PaymentServiceAdapter;
import com.sonnguyen.snpayment.infrastructure.adapter.ZalopayAdapter;
import com.sonnguyen.snpayment.infrastructure.constant.PaymentGatewayType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaymentGatewayFactory {
    Map<PaymentGatewayType, PaymentServiceAdapter> providers;

    public PaymentGatewayFactory(
            ZalopayAdapter zalopayAdapter
    ) {
        Map<PaymentGatewayType, PaymentServiceAdapter> map = new HashMap<>();
        map.put(PaymentGatewayType.ZALOPAY, zalopayAdapter);
        this.providers = map;
    }

    public PaymentServiceAdapter getProvider(PaymentGatewayType gateway) {
        return providers.get(gateway);
    }
}
