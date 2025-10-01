package com.sonnguyen.common.web.configuration.impl;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Component;

@Component
@EnableFeignClients(basePackages = {"com.sonnguyen.common.client"})
@EnableCaching
public class ApplicationConfiguration {
}
