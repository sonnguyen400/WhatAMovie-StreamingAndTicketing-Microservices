package com.sonnguyen.sniam.infrastructure.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Component
@ConfigurationProperties(prefix = "application")
@Data
public class ApplicationConfiguration {
    private String name;
    private Security security;

    @Data
    public static class Security{
        private String publicKey;
        private String privateKey;
        @DurationUnit(ChronoUnit.SECONDS)
        private Duration accessTokenExpireTime;
        @DurationUnit(ChronoUnit.SECONDS)
        private Duration refreshTokenExpireTime;
        private String jwtIssuer;
        @DurationUnit(ChronoUnit.SECONDS)
        private Duration clientAccessTokenExpireTime;
    }
}
