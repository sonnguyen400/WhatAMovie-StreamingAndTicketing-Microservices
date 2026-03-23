package com.sonnguyen.sniam.presentation.rest.impl;

import com.sonnguyen.sniam.application.dto.request.RoleCreateOrUpdateRequest;
import com.sonnguyen.sniam.application.dto.response.RoleDetailResponse;
import com.sonnguyen.sniam.application.service.RoleCommandService;
import com.sonnguyen.sniam.application.service.RoleQueryService;
import com.sonnguyen.sniam.domain.Role;
import com.sonnguyen.sniam.presentation.rest.RoleController;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleControllerImpl implements RoleController {
    RoleQueryService roleQueryService;
    RoleCommandService roleCommandService;

    @Override
    public RoleDetailResponse getById(UUID id) {
        return this.roleQueryService.findById(id);
    }

    @Override
    public Collection<Role> findAll() {
        return this.roleQueryService.findAll();
    }

    @Override
    public RoleDetailResponse updateById(UUID id, RoleCreateOrUpdateRequest request) {
        return this.roleCommandService.updateById(id, request);
    }
}
