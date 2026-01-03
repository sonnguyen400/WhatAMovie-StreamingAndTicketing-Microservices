package com.sonnguyen.sniam.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.sniam.infrastructure.support.enums.CustomerGroupType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

/**
 * DTO for {@link com.sonnguyen.sniam.infrastructure.persistence.entity.CustomerGroupEntity}
 */
@Getter
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class CustomerGroup extends AuditingDomain {
    private UUID id;
    private UUID representativeCustomerId;
    private String name;
    private String rules;
    private CustomerGroupType type;
}
