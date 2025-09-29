package com.sonnguyen.sniam.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class Role extends AuditingDomain {
    private UUID id;
    private String name;
    private String code;
    private String description;
    private Boolean deleted;
    private List<RolePermission> permissions;
}
