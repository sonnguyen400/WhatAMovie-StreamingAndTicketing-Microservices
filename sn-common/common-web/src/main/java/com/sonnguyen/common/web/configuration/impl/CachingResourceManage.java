//package com.sonnguyen.common.web.configuration.impl;
//
//import com.sonnguyen.common.model.infrastructure.constant.CachingValueConstant;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.cache.RedisCacheWriter;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.Map;
//
//@EnableCaching
//public class CachingResourceManage {
//    @Value("spring.data.redis.host")
//    private String host;
//
//    @Value("spring.data.redis.port")
//    private String port;
//
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//
//        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
//
//        // TTL riêng cho token
//        cacheConfigurations.put(CachingValueConstant.ClientTrustedToken,
//                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(10)));
//
//        // TTL riêng cho users
//        cacheConfigurations.put(CachingValueConstant.UserAuthority,
//                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)));
//
//        // TTL riêng cho users
//        cacheConfigurations.put(CachingValueConstant.ClientAuthority,
//                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(5)));
//
//        return RedisCacheManager.builder()
//                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)))
//                .cacheWriter(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory))
//                .withInitialCacheConfigurations(cacheConfigurations)
//                .build();
//    }
//
//}
