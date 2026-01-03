package com.sonnguyen.snbuilding.domain;

import com.sonnguyen.common.model.domain.TenancyDomain;
import com.sonnguyen.snbuilding.infrastructure.support.enums.ScreeningStatus;
import lombok.Getter;

import java.time.LocalTime;
import java.util.UUID;

/**
 * Domain for {@link com.sonnguyen.snbuilding.infrastructure.persistence.entity.ScreeningEntity}
 */
@Getter
public class Screening extends TenancyDomain {
    private UUID id;
    private UUID movieId;
    private UUID theaterId;
    private UUID roomId;
    private LocalTime startTime;
    private LocalTime endTime;
    private ScreeningStatus status;
}
