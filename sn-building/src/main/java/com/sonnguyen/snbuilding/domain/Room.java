package com.sonnguyen.snbuilding.domain;

import com.sonnguyen.common.model.domain.TenancyDomain;
import com.sonnguyen.snbuilding.infrastructure.support.enums.RoomStatus;
import com.sonnguyen.snbuilding.infrastructure.support.enums.ScreenType;
import com.sonnguyen.snbuilding.infrastructure.support.enums.SoundSystem;
import lombok.Getter;

import java.util.UUID;

/**
 * Domain for {@link com.sonnguyen.snbuilding.infrastructure.persistence.entity.RoomEntity}
 */
@Getter
public class Room extends TenancyDomain {
    private UUID id;
    private UUID theaterId;
    private String code;
    private ScreenType screenType;
    private SoundSystem soundSystem;
    private Integer capacity;
    private String seatLayoutTemplate;
    private RoomStatus status;
}
