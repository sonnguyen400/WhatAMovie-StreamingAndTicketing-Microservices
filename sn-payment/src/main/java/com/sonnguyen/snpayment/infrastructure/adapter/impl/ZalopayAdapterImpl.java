package com.sonnguyen.snpayment.infrastructure.adapter.impl;

import com.sonnguyen.snpayment.domain.Payment;
import com.sonnguyen.snpayment.infrastructure.adapter.ZalopayAdapter;
import com.sonnguyen.snpayment.infrastructure.client.ZalopayClient;
import com.sonnguyen.snpayment.infrastructure.configuration.IntegrationProperties;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ZalopayAdapterImpl implements ZalopayAdapter {
    ZalopayClient zalopayClient;
    IntegrationProperties integrationProperties;

    @Override
    public Payment createPayment(Payment payment) {
        IntegrationProperties.ZalopayIntegrationProps zalopayIntegrationProps = integrationProperties.getZalopay();
//        zalopayClient.createOrder(
//                ZalopayOrderCreateRequest.builder()
//                        .appId(zalopayIntegrationProps.getAppId())
//                        .build()
//        );
        return null;
    }

    @Override
    public Payment refundPayment(Payment payment) {
        return null;
    }
}
