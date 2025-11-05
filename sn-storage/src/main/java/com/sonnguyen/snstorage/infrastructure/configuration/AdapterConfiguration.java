package com.sonnguyen.snstorage.infrastructure.configuration;

import io.minio.MinioClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AdapterConfiguration {
    MinIOConfiguration minIOConfiguration;

    @Bean
    public MinioClient minioClient(){
        return MinioClient.builder()
                .endpoint(minIOConfiguration.getUrl())
                .credentials(this.minIOConfiguration.getAccessName(), this.minIOConfiguration.getAccessSecret())
                .build();
    }
}
