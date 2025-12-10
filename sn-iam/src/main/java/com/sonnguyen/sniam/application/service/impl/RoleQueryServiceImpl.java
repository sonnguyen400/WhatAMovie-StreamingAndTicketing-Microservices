package com.sonnguyen.sniam.application.service.impl;

import com.sonnguyen.sniam.application.dto.response.RoleDetailResponse;
import com.sonnguyen.sniam.application.service.RoleQueryService;
import com.sonnguyen.sniam.domain.Permission;
import com.sonnguyen.sniam.domain.Role;
import com.sonnguyen.sniam.domain.repository.PermissionRepository;
import com.sonnguyen.sniam.domain.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class RoleQueryServiceImpl implements RoleQueryService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;

    @Override
    public RoleDetailResponse findById(UUID id){
        Role role = this.roleRepository.getById(id);
        Collection<Permission> permissions = this.permissionRepository.findAll();
        return new RoleDetailResponse(role, role.getPermissions(), permissions.stream().toList());
    }

    @Override
    public Collection<Role> findAll() {
        return this.roleRepository.findAll();
    }
}
