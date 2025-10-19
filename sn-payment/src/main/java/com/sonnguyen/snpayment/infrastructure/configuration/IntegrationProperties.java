package com.sonnguyen.snpayment.infrastructure.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "integration")
public class IntegrationProperties {
    private ZalopayIntegrationProps zalopay;

    @Data
    public static class ZalopayIntegrationProps {
        private Integer appId;
    }
}
