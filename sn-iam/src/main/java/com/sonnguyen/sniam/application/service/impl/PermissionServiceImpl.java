package com.sonnguyen.sniam.application.service.impl;

import com.sonnguyen.sniam.application.service.PermissionService;
import com.sonnguyen.sniam.domain.Permission;
import com.sonnguyen.sniam.domain.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionServiceImpl implements PermissionService {
    PermissionRepository permissionRepository;

    public Collection<Permission> findAllPermissions() {
        return this.permissionRepository.findAll();
    }
}
