package com.sonnguyen.common.web.configuration;

import com.nimbusds.jose.jwk.RSAKey;

public interface SecretKeyProvider {
    RSAKey getPublicKey();

    RSAKey getPrivateKey();
}
