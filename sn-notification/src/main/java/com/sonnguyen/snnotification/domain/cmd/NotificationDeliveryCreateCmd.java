package com.sonnguyen.snnotification.domain.cmd;

import com.sonnguyen.common.model.infrastructure.constant.NotificationChanel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
public class NotificationDeliveryCreateCmd {
    private NotificationChanel chanel;
    private String address;
    private Instant scheduledAt;
    private Instant lastAttemptAt;
    private UUID recipientId;
}
