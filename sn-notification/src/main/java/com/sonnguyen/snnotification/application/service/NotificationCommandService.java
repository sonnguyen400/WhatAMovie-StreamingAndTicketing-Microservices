package com.sonnguyen.snnotification.application.service;

import com.sonnguyen.common.model.application.request.notification.NotificationCreateRequest;

import java.util.UUID;

public interface NotificationCommandService {
    void createNotification(NotificationCreateRequest request);

    void launchingNotification(UUID notificationId);
}
