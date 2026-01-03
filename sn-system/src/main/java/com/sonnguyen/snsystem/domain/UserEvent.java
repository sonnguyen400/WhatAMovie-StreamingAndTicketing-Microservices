package com.sonnguyen.snsystem.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEvent {
    private UUID id;                    // uuid event
    private AuditContext audit;           // chứa userId/sessionId/traceId
    private String eventName;             // VIEW_PRODUCT, ADD_TO_CART, PURCHASE, SEARCH
    private Instant timestamp;
    private UUID apiLogId;              // optional: id của ApiLog nếu event phát sinh từ request
    private UUID taskLogId;             // optional: nếu event do job tạo
    private UUID mqLogId;               // optional: nếu event từ message
    private String sessionId;
    private String device;
    private String platform;              // web/android/ios
    private String channel;
}
