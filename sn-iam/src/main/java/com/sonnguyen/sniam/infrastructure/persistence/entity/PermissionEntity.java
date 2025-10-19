package com.sonnguyen.sniam.infrastructure.persistence.entity;

import com.sonnguyen.common.data.persistence.entity.AuditingEntity;
import com.sonnguyen.common.util.Validator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "permission")
public class PermissionEntity extends AuditingEntity {
    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "code", length = Validator.Length.CODE_MAX_LENGTH)
    private String code;

    @Column(name = "scope")
    private String scope;

    @Column(name = "deleted")
    private Boolean deleted;
}
