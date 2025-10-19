package com.sonnguyen.sniam.infrastructure.configuration;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.sonnguyen.common.util.DataUtils;
import com.sonnguyen.common.util.IdUtils;
import com.sonnguyen.common.web.configuration.SecretKeyProvider;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@Lazy
public class JWTProvider {
    private final SecretKeyProvider secretKeyProvider;

    public JWTProvider(SecretKeyProvider secretKeyProvider) throws JOSEException {
        this.secretKeyProvider = secretKeyProvider;
    }

    public JWTBuilder builder() throws JOSEException {
        JWSSigner jweEncrypter = new RSASSASigner(this.secretKeyProvider.getPrivateKey());
        return new JWTBuilder(jweEncrypter);
    }

    public static class JWTBuilder {
        private String issuer;
        private String audience;
        private String subject;
        private Date issueTime;
        private Map<String, Object> claims;
        private JWSSigner signer;
        private JWSAlgorithm algorithm;
        private Date expirationTime;
        private Date notBeforeTime;

        private JWTBuilder(JWSSigner signer) {
            this.signer = signer;
        }

        /**
         * Generated token will expire after duration
         * Token can be used from release date (now)
         *
         * @param duration Token will expire after duration
         */
        public JWTBuilder expirationAfter(Duration duration) {
            Instant now = Instant.now();
            this.issueTime = Date.from(now);
            this.notBeforeTime = this.issueTime;
            this.expirationTime = Date.from(now.plusSeconds(duration.getSeconds()));
            return this;
        }

        public JWTBuilder algorithm(JWSAlgorithm algorithm) {
            this.algorithm = algorithm;
            return this;
        }

        public JWTBuilder issuer(String issuer) {
            this.issuer = issuer;
            return this;
        }

        public JWTBuilder audience(String audience) {
            this.audience = audience;
            return this;
        }

        public JWTBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public JWTBuilder issueTime(Date issueTime) {
            this.issueTime = issueTime;
            return this;
        }

        public JWTBuilder expirationTime(Date expirationTime) {
            this.expirationTime = expirationTime;
            return this;
        }

        public JWTBuilder claims(Map<String, Object> claims) {
            this.claims = claims;
            return this;
        }

        public JWTBuilder addClaims(String key, Object value) {
            if (Objects.isNull(this.claims)) {
                this.claims = new HashMap<>();
            }
            this.claims.put(key, value);
            return this;
        }

        public JWTBuilder addClaims(Map<String, Object> claims) {
            if (Objects.isNull(this.claims)) {
                this.claims = new HashMap<>();
            }
            claims = DataUtils.getOrDefault(claims, new HashMap<>());
            this.claims.putAll(claims);
            return this;
        }

        public String build() throws JOSEException {
            JWSHeader jwsHeader = new JWSHeader.Builder(DataUtils.getOrDefault(this.algorithm, JWSAlgorithm.RS256))
                    .type(JOSEObjectType.JWT)
                    .build();
            JWTClaimsSet.Builder payload = new JWTClaimsSet.Builder()
                    .subject(this.subject)
                    .audience(this.audience)
                    .issuer(this.issuer)
                    .issueTime(this.issueTime)
                    .expirationTime(this.expirationTime)
                    .notBeforeTime(this.notBeforeTime)
                    .jwtID(IdUtils.nextId().toString());
            this.claims.forEach(payload::claim);
            JWTClaimsSet jwtClaimsSet = payload.build();
            SignedJWT signedJWT = new SignedJWT(jwsHeader, jwtClaimsSet);
            signedJWT.sign(this.signer);
            return signedJWT.serialize();
        }
    }
}
