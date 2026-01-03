package com.sonnguyen.snticket.domain;

import com.sonnguyen.common.model.domain.TenancyDomain;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class BusinessPlanBuildingContract extends TenancyDomain {
    private UUID id;
    private UUID theaterId;
    private LocalDate startDate;
    private LocalDate expiredDate;
    private UUID surPriceId;
}
