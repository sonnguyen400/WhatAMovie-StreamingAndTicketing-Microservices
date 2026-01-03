package com.sonnguyen.snbuilding.domain;

import com.sonnguyen.common.model.domain.TenancyDomain;
import com.sonnguyen.snbuilding.infrastructure.support.enums.TheaterStatus;
import lombok.Getter;

import java.util.UUID;

/**
 * Domain for {@link com.sonnguyen.snbuilding.infrastructure.persistence.entity.TheaterEntity}
 */
@Getter
public class Theater extends TenancyDomain {
    private UUID id;
    private String name;
    private String code;
    private String address;
    private String phone;
    private String email;
    private TheaterStatus status;
    private Integer openTime;
    private Integer closeTime;
    private String tenant;
    private UUID providerId;
}
