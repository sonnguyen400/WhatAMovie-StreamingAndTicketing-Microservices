package com.sonnguyen.sniam.domain.command;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class RoleCreateOrUpdateCmd {
    private String name;
    private String code;
    private String description;
    private List<UUID> permissionIds;
}
