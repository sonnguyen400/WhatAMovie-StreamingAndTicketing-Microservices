package com.sonnguyen.sniam.application.service;

import com.nimbusds.jose.jwk.RSAKey;

public interface ClientService {
    RSAKey getKey();
}
