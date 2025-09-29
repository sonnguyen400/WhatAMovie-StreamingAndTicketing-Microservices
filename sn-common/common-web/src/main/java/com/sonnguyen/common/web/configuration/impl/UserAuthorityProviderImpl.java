package com.sonnguyen.common.web.configuration.impl;

import com.sonnguyen.common.client.IAMClient;
import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.common.model.application.security.UserAuthority;
import com.sonnguyen.common.model.infrastructure.exception.ResponseException;
import com.sonnguyen.common.web.configuration.UserAuthorityProvider;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ConditionalOnMissingBean(UserAuthorityProvider.class)
public class UserAuthorityProviderImpl implements UserAuthorityProvider {
    private IAMClient iamClient;

    @Override
    public UserAuthority getById(UUID userId) {
        Response<UserAuthority> userAuthorityResponse = this.iamClient.getUserAuthority(userId);
        if (userAuthorityResponse.isSuccess() && Objects.nonNull(userAuthorityResponse.getData())) {
            return userAuthorityResponse.getData();
        }
        throw new ResponseException(userAuthorityResponse.getMessage());
    }
}
