package com.sonnguyen.sniam.application.dto.response;

import com.sonnguyen.common.model.infrastructure.support.enums.ResourceCode;
import com.sonnguyen.common.model.infrastructure.support.enums.ResourceScope;
import com.sonnguyen.sniam.domain.Permission;
import com.sonnguyen.sniam.domain.Role;
import com.sonnguyen.sniam.domain.RolePermission;
import lombok.Data;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class RoleDetailResponse {
    private final UUID id;
    private final String name;
    private final String code;
    private final String description;
    private final List<PermissionResponse> permissions;


    public RoleDetailResponse(Role role, List<RolePermission> assignedPermissions, Collection<? extends Permission> allExistingPermissions) {
        this.id = role.getId();
        this.name = role.getName();
        this.code = role.getCode();
        this.description = role.getDescription();
        Set<UUID> assignedPermissionIds = assignedPermissions.stream().map(RolePermission::getPermissionId)
                .collect(Collectors.toSet());
        this.permissions = allExistingPermissions.stream()
                .map(it -> new PermissionResponse(it, assignedPermissionIds.contains(it.getId())))
                .toList();
    }

    @Data
    public static class PermissionResponse {
        private final UUID id;
        private final String name;
        private final String code;
        private final ResourceCode resourceCode;
        private final ResourceScope scope;
        private final String groupName;
        private final String groupCode;
        private final boolean assigned;

        public PermissionResponse(Permission permission, boolean assigned) {
            this.id = permission.getId();
            this.name = permission.getName();
            this.code = permission.getCode();
            this.resourceCode = permission.getResourceCode();
            this.scope = permission.getScope();
            this.groupName = permission.getGroupName();
            this.groupCode = permission.getGroupCode();
            this.assigned = assigned;
        }
    }
}
