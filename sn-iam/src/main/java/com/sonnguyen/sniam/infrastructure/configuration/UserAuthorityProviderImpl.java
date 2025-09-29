package com.sonnguyen.sniam.infrastructure.configuration;

import com.sonnguyen.common.model.application.security.UserAuthority;
import com.sonnguyen.common.web.configuration.UserAuthorityProvider;
import com.sonnguyen.sniam.application.service.UserQueryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserAuthorityProviderImpl implements UserAuthorityProvider {
    UserQueryService userQueryService;

    @Override
    public UserAuthority getById(UUID userId) {
        return this.userQueryService.getUserAuthority(userId);
    }
}
