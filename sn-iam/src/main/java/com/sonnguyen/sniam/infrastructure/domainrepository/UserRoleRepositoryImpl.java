package com.sonnguyen.sniam.infrastructure.domainrepository;

import com.sonnguyen.common.data.persistence.domain.repository.AbstractDomainRepository;
import com.sonnguyen.sniam.domain.Permission;
import com.sonnguyen.sniam.domain.Role;
import com.sonnguyen.sniam.domain.RolePermission;
import com.sonnguyen.sniam.domain.UserRole;
import com.sonnguyen.sniam.domain.repository.UserRoleRepository;
import com.sonnguyen.sniam.infrastructure.mapper.PermissionEntityMapper;
import com.sonnguyen.sniam.infrastructure.mapper.RoleEntityMapper;
import com.sonnguyen.sniam.infrastructure.mapper.RolePermissionEntityMapper;
import com.sonnguyen.sniam.infrastructure.mapper.UserRoleEntityMapper;
import com.sonnguyen.sniam.infrastructure.persistence.entity.UserRoleEntity;
import com.sonnguyen.sniam.infrastructure.persistence.repository.PermissionEntityRepository;
import com.sonnguyen.sniam.infrastructure.persistence.repository.RoleEntityRepository;
import com.sonnguyen.sniam.infrastructure.persistence.repository.RolePermissionEntityRepository;
import com.sonnguyen.sniam.infrastructure.persistence.repository.UserRoleEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class UserRoleRepositoryImpl extends AbstractDomainRepository<UserRole, UserRoleEntity, UUID>
        implements UserRoleRepository {
    private UserRoleEntityRepository userRoleEntityRepository;
    private UserRoleEntityMapper userRoleEntityMapper;
    private RoleEntityRepository roleEntityRepository;
    private RoleEntityMapper roleEntityMapper;
    private RolePermissionEntityRepository rolePermissionEntityRepository;
    private RolePermissionEntityMapper rolePermissionEntityMapper;
    private PermissionEntityRepository permissionEntityRepository;
    private PermissionEntityMapper permissionEntityMapper;

    public UserRoleRepositoryImpl(UserRoleEntityRepository userRoleEntityRepository, UserRoleEntityMapper userRoleEntityMapper) {
        super(userRoleEntityRepository, userRoleEntityMapper);
    }

    @Override
    public Collection<UserRole> enrichAll(Collection<UserRole> domains) {
        List<UUID> roleIds = domains.stream().map(UserRole::getRoleId).toList();
        Map<UUID, Role> roles = this.roleEntityMapper
                .toDomain(this.roleEntityRepository.findByIds(roleIds))
                .stream()
                .collect(Collectors.toMap(Role::getId, Function.identity()));
        Collection<RolePermission> rolePermissions = this.rolePermissionEntityMapper.toDomain(this.rolePermissionEntityRepository.findByRoleIds(roleIds));
        //Enrich permission
        List<UUID> permissionIds = rolePermissions.stream().map(RolePermission::getPermissionId).toList();
        Map<UUID, Permission> permissions = this.permissionEntityMapper
                .toDomain(this.permissionEntityRepository.findByIds(permissionIds))
                .stream()
                .collect(Collectors.toMap(Permission::getId, Function.identity()));
        //Enrich role permission
        rolePermissions.forEach(rolePermission -> {
            Permission permission = permissions.getOrDefault(rolePermission.getPermissionId(), null);
            Optional.ofNullable(permission)
                    .ifPresent(rolePermission::enrichPermission);
        });
        //Enrich User Role
        domains.forEach(userRole -> {
            Role role = roles.getOrDefault(userRole.getRoleId(), null);
            Optional.ofNullable(role)
                    .ifPresent(userRole::enrichRole);
            List<RolePermission> rolePermissionList = rolePermissions.stream()
                    .filter(it -> Objects.equals(it.getRoleId(), userRole.getRoleId()))
                    .toList();
            userRole.enrichPermissions(rolePermissionList);
        });
        return super.enrichAll(domains);
    }
}
