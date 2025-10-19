package com.sonnguyen.common.web.configuration.impl;

import com.nimbusds.jose.jwk.RSAKey;
import com.sonnguyen.common.client.BadRequestError;
import com.sonnguyen.common.client.IAMClient;
import com.sonnguyen.common.model.infrastructure.exception.ResponseException;
import com.sonnguyen.common.web.configuration.SecretKeyProvider;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Map;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ConditionalOnMissingBean(SecretKeyProvider.class)
public class SecretKeyProviderImpl implements SecretKeyProvider {
    IAMClient iamClient;

    @Override
    public RSAKey getPublicKey() {
        Map<String, Object> jwks = this.iamClient.getJwks();
        try {
            return RSAKey.parse(jwks);
        } catch (ParseException e) {
            throw new ResponseException(BadRequestError.IAM_SERVICE_NOT_AVAILABLE);
        }
    }

    @Override
    public RSAKey getPrivateKey() {
        return null;
    }
}
