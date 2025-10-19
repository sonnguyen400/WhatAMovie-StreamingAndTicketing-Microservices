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
@Table(name = "role")
@EqualsAndHashCode(callSuper = true)
public class RoleEntity extends AuditingEntity {
    @Id
    private UUID id;

    @Column(name = "name", length = Validator.Length.NAME_MAX_LENGTH)
    private String name;

    @Column(name = "code", length = Validator.Length.CODE_MAX_LENGTH)
    private String code;

    @Column(name = "description", length = Validator.Length.DESCRIPTION_MAX_LENGTH)
    private String description;

    @Column(name = "deleted")
    private Boolean deleted;
}
