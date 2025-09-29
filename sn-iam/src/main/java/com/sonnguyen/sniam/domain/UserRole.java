package com.sonnguyen.sniam.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.util.IdUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class UserRole extends AuditingDomain {
    private UUID id;
    private UUID userId;
    private UUID roleId;
    private String roleName;
    private String roleCode;
    private Boolean deleted;
    private List<RolePermission> permissions;

    public UserRole(User user, Role role) {
        this.id = IdUtils.nextId();
        this.userId = user.getId();
        this.roleId = role.getId();
        this.roleName = role.getName();
        this.deleted = false;
    }

    public void delete() {
        this.deleted = true;
    }

    public void unDelete() {
        this.deleted = false;
    }

    public void enrichRole(Role role) {
        this.roleCode = role.getCode();
        this.roleName = role.getName();
    }

    public void enrichPermissions(List<RolePermission> rolePermissionList) {
        this.permissions = rolePermissionList;
    }
}
