package com.sonnguyen.sniam.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.sniam.infrastructure.constant.UserAction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class UserTrace extends AuditingDomain {
    private UUID id;
    private UUID userId;
    private UserAction action;
    private String description;
    private String payload;
    private String ipAddress;
    private String userAgent;
    private UUID deviceId;
    private String deviceCode;
    private String metaData;
    private Boolean loginSuccess;
}
