package com.sonnguyen.sniam.application.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import com.sonnguyen.common.model.application.request.TrustedClientAuthRequest;
import com.sonnguyen.common.model.application.response.TrustedClientAuthResponse;
import com.sonnguyen.common.model.application.security.ClientAuthority;

public interface ClientService {
    RSAKey getKey();

    TrustedClientAuthResponse secretBasedClientLogin(TrustedClientAuthRequest request) throws JOSEException;

    ClientAuthority getClientAuthorityByClientId(String clientId);
}
