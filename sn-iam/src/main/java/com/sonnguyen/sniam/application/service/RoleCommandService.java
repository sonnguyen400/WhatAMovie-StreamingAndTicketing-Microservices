package com.sonnguyen.sniam.application.service;

import com.sonnguyen.sniam.application.dto.request.RoleCreateOrUpdateRequest;
import com.sonnguyen.sniam.application.dto.response.RoleDetailResponse;

import java.util.UUID;

public interface RoleCommandService {
    RoleDetailResponse updateById(UUID id, RoleCreateOrUpdateRequest request);
}
