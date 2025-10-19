package com.sonnguyen.snnotification.domain.query;

import com.sonnguyen.common.model.infrastructure.constant.NotificationChanel;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class NotificationDeliverySearchQuery {
    private List<UUID> notificationIds;
    private NotificationChanel chanel;
    private List<UUID> userIds;
}
