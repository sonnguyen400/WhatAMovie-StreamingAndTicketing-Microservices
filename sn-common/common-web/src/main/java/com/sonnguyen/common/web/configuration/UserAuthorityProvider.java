package com.sonnguyen.common.web.configuration;

import com.sonnguyen.common.model.application.security.UserAuthority;

import java.util.UUID;

public interface UserAuthorityProvider {
    UserAuthority getById(UUID userId);
}
