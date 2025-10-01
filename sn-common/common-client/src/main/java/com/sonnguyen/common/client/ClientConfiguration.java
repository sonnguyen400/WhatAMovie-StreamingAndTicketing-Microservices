package com.sonnguyen.common.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application")
@Data
public class ClientConfiguration {
    private Client client;

    @Data
    public static class Client {
        private String clientId;
        private String clientSecret;
    }
}
