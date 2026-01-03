package com.sonnguyen.sniam.infrastructure.persistence.entity;

import com.sonnguyen.common.data.persistence.entity.AuditingEntity;
import com.sonnguyen.sniam.infrastructure.support.enums.CustomerGroupType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "customer_group_entity")
public class CustomerGroupEntity extends AuditingEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "representative_customer_id")
    private UUID representativeCustomerId;

    @Column(name = "name")
    private String name;

    @Column(name = "rules")
    private String rules;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private CustomerGroupType type;
}
