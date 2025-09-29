package com.sonnguyen.sniam.application.service;

import com.sonnguyen.common.model.application.security.UserAuthority;

import java.util.UUID;

public interface UserQueryService {
    UserAuthority getUserAuthority(UUID userId);
}
