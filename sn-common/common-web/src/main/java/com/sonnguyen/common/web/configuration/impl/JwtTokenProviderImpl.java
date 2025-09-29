package com.sonnguyen.common.web.configuration.impl;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.sonnguyen.common.client.BadRequestError;
import com.sonnguyen.common.model.infrastructure.exception.ResponseException;
import com.sonnguyen.common.web.configuration.JwtTokenProvider;
import com.sonnguyen.common.web.configuration.SecretKeyProvider;
import com.sonnguyen.common.web.configuration.exception.ExpiredTokenException;
import com.sonnguyen.common.web.configuration.exception.InvalidTokenException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtTokenProviderImpl implements JwtTokenProvider {
    SecretKeyProvider secretKeyProvider;

    @Override
    public JWTClaimsSet verify(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new RSASSAVerifier(this.secretKeyProvider.getPublicKey());
            boolean verified = signedJWT.verify(verifier);
            if (!verified) {
                throw new ResponseException(BadRequestError.INVALID_AUTHENTICATION);
            }
            Date now = Date.from(Instant.now());
            JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
            claimsSet.getClaims().forEach((k,v)->System.out.println(k+": "+v));
            if (Objects.nonNull(claimsSet.getExpirationTime()) && claimsSet.getExpirationTime().before(now)) {
                throw new ExpiredTokenException("Token has expired");
            }
            if (Objects.nonNull(claimsSet.getNotBeforeTime()) && claimsSet.getNotBeforeTime().after(now)) {
                throw new InvalidTokenException("Token can't be used right now");
            }
            return claimsSet;
        } catch (ParseException | JOSEException e) {
            throw new InvalidTokenException("Unverified token");
        }
    }
}
