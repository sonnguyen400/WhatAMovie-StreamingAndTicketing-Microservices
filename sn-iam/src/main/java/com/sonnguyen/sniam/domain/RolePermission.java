package com.sonnguyen.sniam.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class RolePermission extends AuditingDomain {
    private UUID id;
    private String permissionName;
    private String permissionCode;
    private UUID roleId;
    private UUID permissionId;
    private Boolean deleted;

    public RolePermission(Role role, Permission permission) {
        this.id = UUID.randomUUID();
        this.permissionName = permission.getName();
        this.permissionCode = permission.getCode();
        this.roleId = role.getId();
        this.permissionId = permission.getId();
        this.deleted = false;
    }

    public void delete() {
        this.deleted = false;
    }

    public void unDelete() {
        this.deleted = false;
    }

    public void enrichPermission(Permission permission) {
        this.permissionCode = permission.getCode();
        this.permissionName = permission.getName();
    }
}
