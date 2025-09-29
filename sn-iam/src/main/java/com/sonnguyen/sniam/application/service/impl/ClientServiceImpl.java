package com.sonnguyen.sniam.application.service.impl;

import com.nimbusds.jose.jwk.RSAKey;
import com.sonnguyen.common.web.configuration.SecretKeyProvider;
import com.sonnguyen.sniam.application.service.ClientService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientServiceImpl implements ClientService {
    SecretKeyProvider secretKeyProvider;

    @Override
    public RSAKey getKey() {
        return this.secretKeyProvider.getPublicKey();
    }
}
