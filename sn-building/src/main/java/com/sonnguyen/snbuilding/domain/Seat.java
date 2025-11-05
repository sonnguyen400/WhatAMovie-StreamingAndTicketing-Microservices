package com.sonnguyen.snbuilding.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.snbuilding.infrastructure.support.enums.SeatStatus;
import com.sonnguyen.snbuilding.infrastructure.support.enums.SeatType;
import lombok.Getter;

import java.util.UUID;

/**
 * DTO for {@link com.sonnguyen.snbuilding.infrastructure.persistence.entity.SeatEntity}
 */
@Getter
public class Seat extends AuditingDomain {
    private UUID id;
    private UUID roomId;
    private String rowLabel;
    private String seatNumber;
    private SeatType type;
    private SeatStatus status;
    private Integer xPosition;
    private Integer yPosition;
}
