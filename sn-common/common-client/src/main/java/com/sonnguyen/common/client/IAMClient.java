package com.sonnguyen.common.client;

import com.sonnguyen.common.client.impl.IAMClientFallbackFactory;
import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.common.model.application.security.UserAuthority;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;
import java.util.UUID;

@FeignClient(name = "iam-service", fallbackFactory = IAMClientFallbackFactory.class)
public interface IAMClient {
    @GetMapping("/api/v1/authentication/.well-known/jwks.json")
    Map<String, Object> getJwks();

    @GetMapping("/api/v1/authorities/{userId}")
    Response<UserAuthority> getUserAuthority(@PathVariable("userId") UUID userId);
}
