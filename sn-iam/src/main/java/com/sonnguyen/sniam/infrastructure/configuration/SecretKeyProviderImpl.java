package com.sonnguyen.sniam.infrastructure.configuration;

import com.nimbusds.jose.jwk.RSAKey;
import com.sonnguyen.common.model.infrastructure.exception.ResponseException;
import com.sonnguyen.common.web.configuration.SecretKeyProvider;
import com.sonnguyen.sniam.infrastructure.error.BadRequestError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@Slf4j
public class SecretKeyProviderImpl implements SecretKeyProvider {
    @Override
    public RSAKey getPublicKey() {
        try (InputStream is = new ClassPathResource("keys/public.pem").getInputStream()) {
            String key = new String(is.readAllBytes());
            return RSAKey.parseFromPEMEncodedObjects(key).toRSAKey();
        } catch (Exception e) {
            log.info("Public key could not be parsed");
            e.printStackTrace();
            throw new ResponseException(BadRequestError.INVALID_AUTHENTICATION);
        }
    }

    @Override
    public RSAKey getPrivateKey() {
        try (InputStream is = new ClassPathResource("keys/private.pem").getInputStream()) {
            String key = new String(is.readAllBytes());
            return RSAKey.parseFromPEMEncodedObjects(key).toRSAKey();
        } catch (Exception e) {
            log.info("Private key could not be parsed");
            throw new ResponseException(BadRequestError.INVALID_AUTHENTICATION);
        }
    }
}
