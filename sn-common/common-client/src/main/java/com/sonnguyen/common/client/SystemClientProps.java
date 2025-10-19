package com.sonnguyen.common.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "application.client")
public class SystemClientProps {
    private String clientId;
    private String clientSecret;
}
