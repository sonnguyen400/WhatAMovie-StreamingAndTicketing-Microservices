package com.sonnguyen.sniam.infrastructure.domainrepository;

import com.sonnguyen.common.data.persistence.domain.repository.AbstractDomainRepository;
import com.sonnguyen.common.util.DataUtils;
import com.sonnguyen.sniam.domain.Role;
import com.sonnguyen.sniam.domain.RolePermission;
import com.sonnguyen.sniam.domain.repository.RoleRepository;
import com.sonnguyen.sniam.infrastructure.mapper.PermissionEntityMapper;
import com.sonnguyen.sniam.infrastructure.mapper.RoleEntityMapper;
import com.sonnguyen.sniam.infrastructure.mapper.RolePermissionEntityMapper;
import com.sonnguyen.sniam.infrastructure.persistence.entity.RoleEntity;
import com.sonnguyen.sniam.infrastructure.persistence.entity.RolePermissionEntity;
import com.sonnguyen.sniam.infrastructure.persistence.repository.PermissionEntityRepository;
import com.sonnguyen.sniam.infrastructure.persistence.repository.RoleEntityRepository;
import com.sonnguyen.sniam.infrastructure.persistence.repository.RolePermissionEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class RoleRepositoryImpl extends AbstractDomainRepository<Role, RoleEntity, UUID>
        implements RoleRepository {

    private final PermissionEntityRepository permissionEntityRepository;
    private final PermissionEntityMapper permissionEntityMapper;
    private final RolePermissionEntityRepository rolePermissionEntityRepository;
    private final RolePermissionEntityMapper rolePermissionEntityMapper;

    public RoleRepositoryImpl(RoleEntityRepository roleEntityRepository,
                              RoleEntityMapper roleEntityMapper,
                              PermissionEntityRepository permissionEntityRepository,
                              PermissionEntityMapper permissionEntityMapper,
                              RolePermissionEntityRepository rolePermissionEntityRepository,
                              RolePermissionEntityMapper rolePermissionEntityMapper) {
        super(roleEntityRepository, roleEntityMapper);
        this.permissionEntityRepository = permissionEntityRepository;
        this.permissionEntityMapper = permissionEntityMapper;
        this.rolePermissionEntityRepository = rolePermissionEntityRepository;
        this.rolePermissionEntityMapper = rolePermissionEntityMapper;
    }

    @Override
    @Transactional
    public Collection<Role> saveAll(Collection<Role> domains) {
        super.saveAll(domains);
        List<RolePermission> rolePermissions = domains.stream()
                .flatMap(role -> DataUtils.getOrDefault(role.getPermissions(), List.<RolePermission>of()).stream())
                .toList();
        List<RolePermissionEntity> rolePermissionEntities = this.rolePermissionEntityMapper.toEntity(rolePermissions);
        this.rolePermissionEntityRepository.saveAll(rolePermissionEntities);
        return domains;
    }

    @Override
    public Collection<Role> enrichAll(Collection<Role> domains) {
        List<UUID> roleIds = domains.stream().map(Role::getId).toList();

        Map<UUID, List<RolePermission>> rolePermissions = this.rolePermissionEntityMapper
                .toDomain(this.rolePermissionEntityRepository.findByRoleIdIn(roleIds)).stream()
                .collect(Collectors.groupingBy(RolePermission::getRoleId));
        for (Role domain : domains) {
            List<RolePermission> rolePermissionTemp = rolePermissions.getOrDefault(domain.getId(), List.of());
            domain.enrichRolePermissions(rolePermissionTemp);
        }
        return super.enrichAll(domains);
    }
}
