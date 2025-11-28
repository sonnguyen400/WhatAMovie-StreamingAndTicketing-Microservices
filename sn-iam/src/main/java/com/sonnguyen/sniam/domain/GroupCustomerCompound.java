package com.sonnguyen.sniam.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

/**
 * DTO for {@link com.sonnguyen.sniam.infrastructure.persistence.entity.GroupCustomerCompoundEntity}
 */
@Getter
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class GroupCustomerCompound extends AuditingDomain {
    private UUID id;
    private UUID customerId;
    private UUID groupId;
    private Boolean isAdmin;
    private Boolean isCorporation;
    private String rules;
}
