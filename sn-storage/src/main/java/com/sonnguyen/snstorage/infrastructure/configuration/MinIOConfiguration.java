package com.sonnguyen.snstorage.infrastructure.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "adapter.minio")
@Data
public class MinIOConfiguration {
    private String url;
    private String accessName;
    private String accessSecret;
    private String bucketName;
}
