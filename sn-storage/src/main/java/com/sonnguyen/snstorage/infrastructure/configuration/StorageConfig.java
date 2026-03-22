package com.sonnguyen.snstorage.infrastructure.configuration;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

import io.minio.MinioClient;

@Configuration
@RefreshScope
public class StorageConfig {
    private StorageProperties storageProperties;

    @Bean
    public Cloudinary cloudinaryConfig(){
        return new Cloudinary(this.storageProperties.getCloudinary().getUrl());
    }

    @Bean
    public MinioClient minioClient(){
        return MinioClient.builder()
                .endpoint(this.storageProperties.getMinio().getUrl())
                .credentials(this.storageProperties.getMinio().getAccessName(), this.storageProperties.getMinio().getAccessSecret())
                .build();
    }
}
