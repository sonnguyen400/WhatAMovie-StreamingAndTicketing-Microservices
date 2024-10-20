package com.whatamovie.payment.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.config.payment.zalo")
public record ZalopayConfig(int appid,String key1,String key2,String paymentEndpoint,String queryEndpoint) {
}
