package com.sonnguyen.snnotification.application.service.impl;

import com.sonnguyen.common.model.application.request.notification.NotificationCreateRequest;
import com.sonnguyen.common.model.infrastructure.support.enums.NotificationChanel;
import com.sonnguyen.common.model.infrastructure.exception.ResponseException;
import com.sonnguyen.common.scheduler.Job;
import com.sonnguyen.common.scheduler.Scheduler;
import com.sonnguyen.common.util.IdUtils;
import com.sonnguyen.snnotification.application.job.SendNotificationJob;
import com.sonnguyen.snnotification.application.mapper.NotificationDTOMapper;
import com.sonnguyen.snnotification.application.service.NotificationCommandService;
import com.sonnguyen.snnotification.domain.Notification;
import com.sonnguyen.snnotification.domain.cmd.NotificationCreateCmd;
import com.sonnguyen.snnotification.domain.repository.NotificationDeliveryRepository;
import com.sonnguyen.snnotification.domain.repository.NotificationRepository;
import com.sonnguyen.snnotification.infrastructure.apdapter.EmailServiceAdapter;
import com.sonnguyen.snnotification.infrastructure.constants.error.BadRequestError;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class NotificationCommandServiceImpl implements NotificationCommandService {
    NotificationRepository notificationRepository;
    NotificationDeliveryRepository notificationDeliveryRepository;
    NotificationDTOMapper notificationDTOMapper;
    EmailServiceAdapter emailServiceAdapter;
    Scheduler scheduler;

    @Override
    public void createNotification(NotificationCreateRequest request) {
        NotificationCreateCmd cmd = this.notificationDTOMapper.from(request);
        Notification notification = new Notification(cmd);
        this.notificationRepository.save(notification);
        this.scheduleLaunchingNotification(notification);
    }

    private void scheduleLaunchingNotification(Notification notification) {
        Job job = Job.builder(IdUtils.nextStrId(), SendNotificationJob.class)
                .data(notification.getId())
                .build();
        try {
            this.scheduler.schedule(job);
        } catch (SchedulerException e) {
            throw new ResponseException(BadRequestError.SCHEDULED_SEND_NOTIFICATION_ERROR, e);
        }
    }

    @Override
    public void launchingNotification(UUID notificationId) {
        Notification notification = this.notificationRepository.getById(notificationId);
        if (Objects.isNull(notification.getChanel())) return;
        if (notification.getChanel().contains(NotificationChanel.SMS)) {
        }
    }
}
