package com.sonnguyen.sniam.application.service.impl;

import com.sonnguyen.sniam.application.dto.request.RoleCreateOrUpdateRequest;
import com.sonnguyen.sniam.application.dto.response.RoleDetailResponse;
import com.sonnguyen.sniam.application.mapper.IamCommandMapper;
import com.sonnguyen.sniam.application.service.RoleCommandService;
import com.sonnguyen.sniam.domain.Permission;
import com.sonnguyen.sniam.domain.Role;
import com.sonnguyen.sniam.domain.command.RoleCreateOrUpdateCmd;
import com.sonnguyen.sniam.domain.repository.PermissionRepository;
import com.sonnguyen.sniam.domain.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class RoleCommandServiceImpl implements RoleCommandService {
    RoleRepository roleRepository;
    IamCommandMapper iamCommandMapper;
    PermissionRepository permissionRepository;

    @Override
    public RoleDetailResponse updateById(UUID id, RoleCreateOrUpdateRequest request) {
        Role role = this.roleRepository.getById(id);
        RoleCreateOrUpdateCmd cmd = this.iamCommandMapper.from(request);
        Collection<Permission> allExistingPermissions = this.permissionRepository.findAll();
        role.update(cmd, allExistingPermissions);
        this.roleRepository.save(role);
        return new RoleDetailResponse(role, role.getPermissions(), allExistingPermissions);
    }
}
