package com.sonnguyen.snticket.domain;

import com.sonnguyen.common.model.domain.TenancyDomain;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder
public class BusinessPlanBuilding extends TenancyDomain {
    private UUID id;
    private UUID businessPlanId;
    private UUID theaterId;
}
