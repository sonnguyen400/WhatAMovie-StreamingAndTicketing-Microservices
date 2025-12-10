package com.sonnguyen.sniam.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.util.CollectionUtils;
import com.sonnguyen.common.util.DataUtils;
import com.sonnguyen.common.util.IdUtils;
import com.sonnguyen.sniam.domain.command.RoleCreateOrUpdateCmd;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@SuperBuilder(toBuilder = true)
public class Role extends AuditingDomain {
    private UUID id;
    private String name;
    private String code;
    private String description;
    private Boolean deleted;
    private List<RolePermission> permissions;

    public Role(RoleCreateOrUpdateCmd cmd, List<Permission> permissions) {
        this.id = IdUtils.nextId();
        this.name = cmd.getName();
        this.code = cmd.getCode();
        this.description = cmd.getDescription();
        this.deleted = false;
        this.updateRolePermissions(permissions);
    }

    public void update(RoleCreateOrUpdateCmd cmd, Collection<Permission> allExistingPermissions) {
        this.name = cmd.getName();
        this.code = cmd.getCode();
        this.description = cmd.getDescription();
        List<UUID> newPermissionIds = DataUtils.getOrDefault(cmd.getPermissionIds(), List.of());
        List<Permission> newPermissionList = allExistingPermissions.stream().filter(it -> newPermissionIds.contains(it.getId())).toList();
        this.updateRolePermissions(newPermissionList);
    }

    public void updateRolePermissions(List<Permission> permissions) {
        if (CollectionUtils.isEmpty(this.permissions)) {
            this.permissions = new ArrayList<>();
        }
        this.permissions.forEach(RolePermission::delete);
        for (Permission permission : permissions) {
            RolePermission rolePermission = this.permissions.stream()
                    .filter(rp -> rp.getPermissionId().equals(permission.getId()))
                    .findFirst()
                    .orElse(null);
            if (rolePermission != null) {
                rolePermission.unDelete();
                rolePermission.enrichPermission(permission);
            } else {
                this.permissions.add(new RolePermission(this, permission));
            }
        }
    }

    public void enrichRolePermissions(List<RolePermission> rolePermission) {
        this.permissions = rolePermission;
    }

    public List<RolePermission> getPermissions() {
        return DataUtils.getOrDefault(this.permissions, List.of());
    }
}
