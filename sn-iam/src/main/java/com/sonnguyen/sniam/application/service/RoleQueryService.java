package com.sonnguyen.sniam.application.service;

import com.sonnguyen.sniam.application.dto.response.RoleDetailResponse;
import com.sonnguyen.sniam.domain.Role;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface RoleQueryService {
    RoleDetailResponse findById(UUID id);

    Collection<Role> findAll();
}
