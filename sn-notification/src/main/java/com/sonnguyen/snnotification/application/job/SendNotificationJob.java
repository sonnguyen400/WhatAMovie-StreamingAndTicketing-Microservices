package com.sonnguyen.snnotification.application.job;

import com.sonnguyen.common.model.infrastructure.constant.NotificationChanel;
import com.sonnguyen.common.scheduler.cofiguration.ScheduleJob;
import com.sonnguyen.snnotification.infrastructure.constants.JobParamConstants;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@ScheduleJob(group = "NOTIFICATION-JOB")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SendNotificationJob extends QuartzJobBean {
    JobLauncher jobLauncher;
    Job launchNotificationJob;

    @Override
    protected void executeInternal(@NonNull JobExecutionContext context) throws JobExecutionException {
        List<UUID> notificationId = (List<UUID>) context.getJobDetail().getJobDataMap().get(JobParamConstants.NOTIFICATiON_IDS);
        NotificationChanel chanel = (NotificationChanel) context.getJobDetail().getJobDataMap().get(JobParamConstants.NOTIFICATiON_CHANEL);
        JobParameters jobParameters = new JobParametersBuilder()
                .addJobParameter(JobParamConstants.NOTIFICATiON_IDS, notificationId, List.class)
                .addJobParameter(JobParamConstants.NOTIFICATiON_CHANEL, chanel, NotificationChanel.class)// unique param mỗi lần chạy
                .toJobParameters();
        try {
            this.jobLauncher.run(this.launchNotificationJob, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
    }
}
