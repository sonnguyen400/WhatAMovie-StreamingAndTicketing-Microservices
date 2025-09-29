package com.sonnguyen.common.web.configuration;

import com.nimbusds.jwt.JWTClaimsSet;

public interface JwtTokenProvider {
    JWTClaimsSet verify(String token);
}
