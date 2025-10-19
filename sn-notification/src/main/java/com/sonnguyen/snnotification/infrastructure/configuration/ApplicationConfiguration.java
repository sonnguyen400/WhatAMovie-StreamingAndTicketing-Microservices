package com.sonnguyen.snnotification.infrastructure.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "application")
public class ApplicationConfiguration {
    private Adapter adapter;

    @Data
    public static class Adapter {
        private EmailService email;

        @Data
        public static class EmailService {
            private SendGrid sendGrid;

            @Data
            public static class SendGrid {
                private String apiKey;
                private String sourceEmail;
                private String sourceName;
            }
        }
    }
}
