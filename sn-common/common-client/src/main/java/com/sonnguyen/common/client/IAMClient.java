package com.sonnguyen.common.client;

import com.sonnguyen.common.client.impl.IAMClientFallbackFactory;
import com.sonnguyen.common.model.application.request.TrustedClientAuthRequest;
import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.common.model.application.response.TrustedClientAuthResponse;
import com.sonnguyen.common.model.application.security.ClientAuthority;
import com.sonnguyen.common.model.application.security.UserAuthority;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.UUID;

@FeignClient(name = "iam-service", fallbackFactory = IAMClientFallbackFactory.class)
public interface IAMClient {
    @GetMapping("/api/v1/authentication/.well-known/jwks.json")
    Map<String, Object> getJwks();

    @GetMapping("/api/v1/authorities/{userId}")
    Response<UserAuthority> getUserAuthority(@PathVariable("userId") UUID userId);

    @GetMapping("/api/v1/client/{clientId}/authorities")
    Response<ClientAuthority> getClientAuthority(@PathVariable("clientId") String clientId);

    @PostMapping("/api/v1/client/secret-login")
    TrustedClientAuthResponse clientSecretLogin(@RequestBody TrustedClientAuthRequest request);
}
