package com.sonnguyen.common.web.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Component;

@Component
@EnableFeignClients(basePackages = {"com.sonnguyen.common.client"})
public class ApplicationConfiguration {
}
