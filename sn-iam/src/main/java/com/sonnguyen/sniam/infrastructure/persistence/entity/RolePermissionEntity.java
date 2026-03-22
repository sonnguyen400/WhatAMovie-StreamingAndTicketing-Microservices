package com.sonnguyen.sniam.infrastructure.persistence.entity;

import com.sonnguyen.common.data.persistence.entity.AuditingEntity;
import com.sonnguyen.common.util.Validator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
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

    @Column(name = "permission_code")
    @Transient
    private String permissionCode;

    @Transient
    @Column(name = "permission_name")
    private String permissionName;


    public RolePermissionEntity(UUID id, UUID roleId, UUID permissionId, Boolean deleted, String permissionCode, String permissionName, String createdBy, String lastModifiedBy, Instant createdAt, Instant lastModifiedAt) {
        super(createdBy, lastModifiedBy, createdAt, lastModifiedAt);
        this.id = id;
        this.roleId = roleId;
        this.permissionId = permissionId;
        this.deleted = deleted;
        this.permissionCode = permissionCode;
        this.permissionName = permissionName;
    }

    public RolePermissionEntity() {
    }
}
