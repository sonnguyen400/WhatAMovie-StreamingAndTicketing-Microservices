package com.sonnguyen.common.client.impl;

import com.sonnguyen.common.client.BadRequestError;
import com.sonnguyen.common.client.IAMClient;
import com.sonnguyen.common.model.application.request.TrustedClientAuthRequest;
import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.common.model.application.response.TrustedClientAuthResponse;
import com.sonnguyen.common.model.application.security.ClientAuthority;
import com.sonnguyen.common.model.application.security.UserAuthority;
import com.sonnguyen.common.model.infrastructure.exception.ResponseException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class IAMClientFallbackFactory implements FallbackFactory<IAMClient> {
    @Override
    public IAMClient create(Throwable cause) {
        return new IAMFallbackHandler(cause);
    }

    public static class IAMFallbackHandler implements IAMClient {
        private Throwable cause;

        public IAMFallbackHandler(Throwable cause) {
            this.cause = cause;
        }

        @Override
        public Map<String, Object> getJwks() {
            cause.printStackTrace();
            throw new ResponseException(BadRequestError.IAM_SERVICE_NOT_AVAILABLE);
        }

        @Override
        public Response<UserAuthority> getUserAuthority(UUID userId) {
            cause.printStackTrace();
            throw new ResponseException(BadRequestError.IAM_SERVICE_NOT_AVAILABLE);
        }

        @Override
        public Response<ClientAuthority> getClientAuthority(String clientId) {
            cause.printStackTrace();
            throw new ResponseException(BadRequestError.IAM_SERVICE_NOT_AVAILABLE);
        }

        @Override
        public TrustedClientAuthResponse clientSecretLogin(TrustedClientAuthRequest request) {
            cause.printStackTrace();
            throw new ResponseException(BadRequestError.IAM_SERVICE_NOT_AVAILABLE);
        }
    }
}
