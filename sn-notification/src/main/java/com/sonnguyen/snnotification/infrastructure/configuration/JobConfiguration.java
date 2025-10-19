package com.sonnguyen.snnotification.infrastructure.configuration;

import com.github.kagkarlsson.scheduler.task.Task;
import com.github.kagkarlsson.scheduler.task.helper.Tasks;
import com.sonnguyen.snnotification.application.service.NotificationCommandService;
import com.sonnguyen.snnotification.infrastructure.constants.JobConstants;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JobConfiguration {
    NotificationCommandService notificationCommandService;

    @Bean
    Task<UUID> launchingNotification() {
        return Tasks.oneTime(JobConstants.LAUNCHING_NOTIFICATION_JOB, UUID.class)
                .execute((taskInstance, executionContext) -> {
                    UUID notificationId = taskInstance.getData();
                    this.notificationCommandService.launchingNotification(notificationId);
                });
    }
}
