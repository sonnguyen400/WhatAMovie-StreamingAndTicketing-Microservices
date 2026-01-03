package com.sonnguyen.common.data.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import java.util.UUID;

@Setter
@Getter
@MappedSuperclass
@FilterDef(name = "tenantFilter", parameters = @ParamDef(name = "tenantId", type = UUID.class))
public abstract class TenancyEntity extends AuditingEntity {

    @Column(name = "tenant_id")
    @Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
    private UUID tenantId;
}
