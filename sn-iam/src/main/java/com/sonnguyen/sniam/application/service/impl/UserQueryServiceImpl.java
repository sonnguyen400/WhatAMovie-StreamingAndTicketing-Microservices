package com.sonnguyen.sniam.application.service.impl;

import com.sonnguyen.common.model.application.security.UserAuthority;
import com.sonnguyen.common.util.CollectionUtils;
import com.sonnguyen.sniam.application.service.UserQueryService;
import com.sonnguyen.sniam.domain.User;
import com.sonnguyen.sniam.domain.repository.UserRepository;
import com.sonnguyen.sniam.domain.repository.UserRoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserQueryServiceImpl implements UserQueryService {
    UserRepository userRepository;
    UserRoleRepository userRoleRepository;

    @Override
    public UserAuthority getUserAuthority(UUID userId) {
        User user = this.userRepository.getById(userId);
        if (CollectionUtils.isNotEmpty(user.getRoles())) {
            this.userRoleRepository.enrichAll(user.getRoles());
        }
        return user.getAuthority();
    }
}
