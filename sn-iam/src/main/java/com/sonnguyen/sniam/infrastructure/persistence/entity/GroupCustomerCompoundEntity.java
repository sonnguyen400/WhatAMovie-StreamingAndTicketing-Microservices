package com.sonnguyen.sniam.infrastructure.persistence.entity;

import com.sonnguyen.common.data.persistence.entity.AuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "group_customer_compound_entity")
public class GroupCustomerCompoundEntity extends AuditingEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "customer_id")
    private UUID customerId;

    @Column(name = "group_id")
    private UUID groupId;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @Column(name = "is_corporation")
    private Boolean isCorporation;

    @Column(name = "rules")
    private String rules;
}
