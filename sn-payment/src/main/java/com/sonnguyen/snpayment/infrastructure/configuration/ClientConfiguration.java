package com.sonnguyen.snpayment.infrastructure.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Component;

@Component
@EnableFeignClients(basePackages = {"com.sonnguyen.snpayment.infrastructure.client"})
public class ClientConfiguration {
}
