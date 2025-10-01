package com.sonnguyen.common.web.configuration.impl;

import com.sonnguyen.common.model.infrastructure.constant.CachingValueConstant;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class CachingResourceManage {
    @Bean
    public CacheManager cacheManager() {
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();

        // TTL riêng cho token
        cacheConfigurations.put(CachingValueConstant.ClientTrustedToken,
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(10)));

        // TTL riêng cho users
        cacheConfigurations.put(CachingValueConstant.UserAuthority,
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)));

        // TTL riêng cho users
        cacheConfigurations.put(CachingValueConstant.ClientAuthority,
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(5)));

        return RedisCacheManager.builder()
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)))
                .withInitialCacheConfigurations(cacheConfigurations)
                .build();
    }

}
