package com.sonnguyen.snticket.domain;

import com.sonnguyen.common.model.domain.TenancyDomain;

import java.time.LocalDate;
import java.util.UUID;

public class BusinessPlan extends TenancyDomain {
    private UUID id;
    private LocalDate startDate;
    private LocalDate expiredDate;
    private UUID basePriceId;
}
