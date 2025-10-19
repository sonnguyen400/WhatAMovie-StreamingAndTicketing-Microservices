package com.sonnguyen.common.client;

import com.sonnguyen.common.model.application.request.TrustedClientAuthRequest;
import com.sonnguyen.common.model.application.response.TrustedClientAuthResponse;
import com.sonnguyen.common.model.infrastructure.constant.CachingValueConstant;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ClientTokenProvider {
    IAMClient iamClient;
    SystemClientProps clientProps;

    @Cacheable(value = CachingValueConstant.ClientTrustedToken, key = "#root.target.clientConfiguration.client.clientId", condition = "#result.accessToken != null ")
    public TrustedClientAuthResponse getToken() {
        TrustedClientAuthRequest request = TrustedClientAuthRequest
                .builder()
                .clientId(this.clientProps.getClientId())
                .clientSecret(this.clientProps.getClientSecret())
                .build();
        return this.iamClient.clientSecretLogin(request);
    }
}
