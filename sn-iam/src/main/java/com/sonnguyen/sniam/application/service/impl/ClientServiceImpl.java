package com.sonnguyen.sniam.application.service.impl;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import com.sonnguyen.common.model.application.request.TrustedClientAuthRequest;
import com.sonnguyen.common.model.application.response.TrustedClientAuthResponse;
import com.sonnguyen.common.model.application.security.ClientAuthority;
import com.sonnguyen.common.model.infrastructure.constant.DefaultClaim;
import com.sonnguyen.common.model.infrastructure.exception.ResponseException;
import com.sonnguyen.common.web.configuration.SecretKeyProvider;
import com.sonnguyen.common.web.security.UserPasswordEncoder;
import com.sonnguyen.sniam.application.mapper.IamCommandMapper;
import com.sonnguyen.sniam.application.service.ClientService;
import com.sonnguyen.sniam.domain.Client;
import com.sonnguyen.sniam.domain.command.ClientLoginCmd;
import com.sonnguyen.sniam.domain.repository.ClientRepository;
import com.sonnguyen.sniam.infrastructure.configuration.ApplicationConfiguration;
import com.sonnguyen.sniam.infrastructure.configuration.JWTProvider;
import com.sonnguyen.sniam.infrastructure.error.NotFoundError;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientServiceImpl implements ClientService {
    SecretKeyProvider secretKeyProvider;
    ClientRepository clientRepository;
    IamCommandMapper mapper;
    UserPasswordEncoder passwordEncoder;
    JWTProvider jwtProvider;
    ApplicationConfiguration applicationConfiguration;

    @Override
    public RSAKey getKey() {
        return this.secretKeyProvider.getPublicKey();
    }

    @Override
    public TrustedClientAuthResponse secretBasedClientLogin(TrustedClientAuthRequest request) throws JOSEException {
        Client client = this.clientRepository.findByClientId(request.getClientId())
                .orElseThrow(() -> new ResponseException(NotFoundError.CLIENT_NOT_FOUND));
        ClientLoginCmd clientLoginCmd = this.mapper.from(request);
        client.login(clientLoginCmd, passwordEncoder);
        String token = this.jwtProvider.builder()
                .subject(client.getName())
                .addClaims(Map.of(DefaultClaim.CLIENT_ID, client.getClientId()))
                .expirationAfter(applicationConfiguration.getSecurity().getClientAccessTokenExpireTime())
                .build();
        return TrustedClientAuthResponse.builder()
                .accessToken(token)
                .build();
    }

    @Override
    public ClientAuthority getClientAuthorityByClientId(String clientId) {
        Client client = this.clientRepository.findByClientId(clientId)
                .orElseThrow(() -> new ResponseException(NotFoundError.CLIENT_NOT_FOUND));
        return ClientAuthority.builder()
                .clientId(clientId)
                .userName(client.getName())
                .enabled(client.getEnabled())
                .build();
    }
}
