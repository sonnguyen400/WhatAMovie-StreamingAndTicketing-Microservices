package com.sonnguyen.sniam.infrastructure.persistence.entity;

import com.sonnguyen.common.data.persistence.entity.AuditingEntity;
import com.sonnguyen.common.util.Validator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@Entity
@Table(name = "role_permission")
@EqualsAndHashCode(callSuper = true)
public class RolePermissionEntity extends AuditingEntity {
    @Id
    private UUID id;

    @Column(name = "role_id", length = Validator.Length.UUID_MAX_LENGTH)
    private UUID roleId;

    @Column(name = "permission_id", length = Validator.Length.UUID_MAX_LENGTH)
    private UUID permissionId;

    @Column(name = "deleted")
    private Boolean deleted;
}
