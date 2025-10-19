package com.sonnguyen.snnotification.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.model.infrastructure.constant.NotificationChanel;
import com.sonnguyen.common.model.infrastructure.constant.NotificationDeliveryStatus;
import com.sonnguyen.snnotification.domain.cmd.NotificationDeliveryCreateCmd;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class NotificationDelivery extends AuditingDomain {
    private UUID id;
    private NotificationChanel chanel;
    private Boolean delivered;
    private NotificationDeliveryStatus status;
    private Instant scheduledAt;
    private Instant lastAttemptAt;
    private String address;
    private Integer attempts;
    private UUID recipientId;
    private UUID notificationId;
    private NotificationRecipient recipient;
    //Enrich
    private Notification notification;

    public NotificationDelivery(NotificationDeliveryCreateCmd cmd, Notification notification, NotificationRecipient recipient) {
        this.chanel = cmd.getChanel();
        this.delivered = false;
        this.status = NotificationDeliveryStatus.PENDING;
        this.scheduledAt = cmd.getScheduledAt();
        this.address = cmd.getAddress();
        this.attempts = 0;
        this.recipient = recipient;
        this.recipientId = recipient.getId();
        this.notificationId = notification.getId();
    }

    public void enrichNotification(Notification notification) {
        this.notification = notification;
    }

    public void delivered() {
        if (Objects.isNull(this.attempts)) {
            this.attempts = 0;
        }
        this.lastAttemptAt = Instant.now();
        this.attempts += 1;
        this.status = NotificationDeliveryStatus.DELIVERED;
    }

    public void failed() {
        if (Objects.isNull(this.attempts)) {
            this.attempts = 0;
        }
        this.lastAttemptAt = Instant.now();
        this.attempts += 1;
        this.status = NotificationDeliveryStatus.FAILED;
    }
}
