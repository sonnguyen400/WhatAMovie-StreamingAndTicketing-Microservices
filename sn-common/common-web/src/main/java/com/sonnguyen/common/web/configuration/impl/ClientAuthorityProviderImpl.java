package com.sonnguyen.common.web.configuration.impl;

import com.sonnguyen.common.client.IAMClient;
import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.common.model.application.security.ClientAuthority;
import com.sonnguyen.common.model.infrastructure.exception.ResponseException;
import com.sonnguyen.common.web.configuration.ClientAuthorityProvider;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ConditionalOnMissingBean(ClientAuthorityProvider.class)
public class ClientAuthorityProviderImpl implements ClientAuthorityProvider {
    IAMClient iamClient;

    @Override
    public ClientAuthority getByClientId(String clientId) {
        Response<ClientAuthority> response = this.iamClient.getClientAuthority(clientId);
        if(response.isSuccess() && Objects.nonNull(response.getData())){
            return response.getData();
        }
        throw new ResponseException(response.getMessage());
    }
}
