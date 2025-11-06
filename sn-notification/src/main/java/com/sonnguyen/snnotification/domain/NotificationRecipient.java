package com.sonnguyen.snnotification.domain;

import com.sonnguyen.common.model.application.response.iam.UserDTO;
import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.model.infrastructure.support.enums.NotificationChanel;
import com.sonnguyen.common.model.infrastructure.support.enums.NotificationRecipientType;
import com.sonnguyen.common.util.IdUtils;
import com.sonnguyen.snnotification.domain.cmd.NotificationDeliveryCreateCmd;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class NotificationRecipient extends AuditingDomain {
    private UUID id;
    private UUID userId;
    private String address;
    private Map<String, Object> metadata;
    private NotificationRecipientType recipientType;

    //Enrich
    private List<NotificationDelivery> deliveries;

    public NotificationRecipient(UserDTO userDTO, Notification notification) {
        this.id = IdUtils.nextId();
        this.userId = userDTO.getId();
        this.recipientType = NotificationRecipientType.INTERNAL;
        this.distribute(userDTO, notification.getChanel(), notification);
    }

    public NotificationRecipient(String address, Notification notification, NotificationChanel chanel) {
        this.id = IdUtils.nextId();
        this.address = address;
        this.recipientType = NotificationRecipientType.EXTERNAL;
        this.distribute(address, chanel, notification);
    }

    private void distribute(UserDTO userDTO, List<NotificationChanel> channels, Notification notification) {
        this.deliveries = new ArrayList<>();
        if (channels.contains(NotificationChanel.SMS) && Objects.nonNull(userDTO.getPhoneNumber())) {
            NotificationDeliveryCreateCmd notificationCreateCmd = NotificationDeliveryCreateCmd.builder()
                    .recipientId(this.id)
                    .scheduledAt(notification.getScheduleAt())
                    .lastAttemptAt(notification.getScheduleAt())
                    .chanel(NotificationChanel.SMS)
                    .address(userDTO.getPhoneNumber())
                    .build();
            this.deliveries.add(new NotificationDelivery(notificationCreateCmd, notification, this));
        }
        if (channels.contains(NotificationChanel.EMAIL) && Objects.nonNull(userDTO.getEmail())) {
            NotificationDeliveryCreateCmd notificationCreateCmd = NotificationDeliveryCreateCmd.builder()
                    .recipientId(this.id)
                    .scheduledAt(notification.getScheduleAt())
                    .lastAttemptAt(notification.getScheduleAt())
                    .chanel(NotificationChanel.EMAIL)
                    .address(userDTO.getEmail())
                    .build();
            this.deliveries.add(new NotificationDelivery(notificationCreateCmd, notification, this));
        }
        if (channels.contains(NotificationChanel.PUSH) && Objects.nonNull(userDTO.getPhoneNumber())) {
            NotificationDeliveryCreateCmd notificationCreateCmd = NotificationDeliveryCreateCmd.builder()
                    .recipientId(this.id)
                    .scheduledAt(notification.getScheduleAt())
                    .lastAttemptAt(notification.getScheduleAt())
                    .chanel(NotificationChanel.SMS)
                    .address(userDTO.getPhoneNumber())
                    .build();
            this.deliveries.add(new NotificationDelivery(notificationCreateCmd, notification, this));
        }
    }

    private void distribute(String address, NotificationChanel chanel, Notification notification) {
        this.deliveries = new ArrayList<>();
        NotificationDeliveryCreateCmd notificationCreateCmd = NotificationDeliveryCreateCmd.builder()
                .recipientId(this.id)
                .scheduledAt(notification.getScheduleAt())
                .lastAttemptAt(notification.getScheduleAt())
                .chanel(chanel)
                .address(address)
                .build();
        this.deliveries.add(new NotificationDelivery(notificationCreateCmd, notification, this));
    }
}
