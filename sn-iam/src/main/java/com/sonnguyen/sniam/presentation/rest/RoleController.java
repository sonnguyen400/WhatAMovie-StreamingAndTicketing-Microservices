package com.sonnguyen.sniam.presentation.rest;

import com.sonnguyen.sniam.application.dto.request.RoleCreateOrUpdateRequest;
import com.sonnguyen.sniam.application.dto.response.RoleDetailResponse;
import com.sonnguyen.sniam.domain.Role;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.UUID;

@RequestMapping("/api")
public interface RoleController {
    RoleDetailResponse getById(UUID id);

    Collection<Role> findAll();

    RoleDetailResponse updateById(UUID id, RoleCreateOrUpdateRequest request);
}
