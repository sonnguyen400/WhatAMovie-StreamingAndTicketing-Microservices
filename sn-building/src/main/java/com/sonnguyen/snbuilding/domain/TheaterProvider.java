package com.sonnguyen.snbuilding.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class TheaterProvider extends AuditingDomain {
    private UUID id;
    private UUID tenantId;
    private String code;
    private String name;
    private String email;
    private String phoneNumber;
    private List<Theater> theaters;
}
