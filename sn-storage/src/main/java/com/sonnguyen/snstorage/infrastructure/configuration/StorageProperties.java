package com.sonnguyen.snstorage.infrastructure.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.sonnguyen.snstorage.infrastructure.support.enums.StorageProvider;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "adapter")
public class StorageProperties {
    private StorageProvider defaultProvider;
    private MinIOProperties minio;
    private CloudinaryProperties cloudinary;

    @Data
    public static class MinIOProperties {
        private String url;
        private String accessName;
        private String accessSecret;
        private String bucketName;
    }

    @Data
    public static class CloudinaryProperties {
        private String key;
        private String secret;
        private String url;
    }
}
