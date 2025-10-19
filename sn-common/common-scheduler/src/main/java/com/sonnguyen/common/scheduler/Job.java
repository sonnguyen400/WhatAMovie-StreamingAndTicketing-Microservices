package com.sonnguyen.common.scheduler;

import com.sonnguyen.common.scheduler.cofiguration.ScheduleJob;
import jakarta.validation.constraints.NotNull;
import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;


public class Job {
    public static final String DATA = "data";
    public static final String JOB_GROUP_DEFAULT = "default-group";

    private final Class<? extends org.quartz.Job> jobClass;
    private final String jobId;
    private JobDataMap dataMap;
    private String group;
    private String description;
    private Boolean shouldRecovery;
    private Boolean storeDurably;
    private Integer priority;
    private Instant startAt;
    private Instant endAt;
    private Set<Trigger> triggers;
    private String cron;
    private Integer repeatCount;
    private Integer repeatIntervalInSecond;
    private ScheduleBuilder<? extends Trigger> scheduleBuilder;

    public Job(@NotNull String jobId, @NotNull Class<? extends org.quartz.Job> jobClass) {
        this.jobId = jobId;
        this.jobClass = jobClass;
    }

    /**
     * The cron expression takes priority. You cannot use a cron expression together with other repeating properties
     * (repeatCount, repeatInterval).
     * <p>
     * If you define neither a cron expression nor repeating properties, the job will be executed only once.
     * <p>
     * Note that if you have already defined scheduling properties (e.g., repeat count, cron expression, etc.)
     * through annotations such as {@code @ScheduleJob}, those will be overwritten if you redefine them in the builder.
     */
    public static JobBuilder builder(@NotNull String jobId, @NotNull Class<? extends org.quartz.Job> jobClass) {
        return new JobBuilder(jobId, jobClass);
    }

    public JobDetail getDetail() {
        return org.quartz.JobBuilder.newJob()
                .ofType(this.jobClass)
                .usingJobData(this.dataMap)
                .withIdentity(this.jobId, this.group)
                .withDescription(this.description)
                .requestRecovery(Boolean.TRUE.equals(this.shouldRecovery))
                .storeDurably(Boolean.TRUE.equals(this.storeDurably))
                .build();

    }

    private Trigger trigger() {
        Date startAt = Optional.ofNullable(this.startAt)
                .map(Date::from)
                .orElse(new Date());
        Date endAt = Optional.ofNullable(this.endAt)
                .map(Date::from)
                .orElse(null);

        ScheduleBuilder<? extends Trigger> schedulerBuilder = this.scheduleBuilder;

        if (Objects.isNull(this.scheduleBuilder)
                && Objects.nonNull(this.cron)
                && !this.cron.isBlank()
                && CronExpression.isValidExpression(this.cron)
        ) {
            schedulerBuilder = CronScheduleBuilder.cronSchedule(this.cron);
        }

        if (Objects.isNull(schedulerBuilder)) {
            schedulerBuilder = SimpleScheduleBuilder.simpleSchedule()
                    .withRepeatCount(Optional.ofNullable(this.repeatCount).orElse(0))
                    .withIntervalInSeconds(Optional.ofNullable(this.repeatIntervalInSecond).orElse(0));
        }

        return TriggerBuilder.newTrigger()
                .usingJobData(this.dataMap)
                .withIdentity(this.jobId, this.group)
                .withDescription(this.description)
                .forJob(this.jobId, this.group)
                .withPriority(Optional.ofNullable(this.priority).orElse(1))
                .startAt(startAt)
                .endAt(endAt)
                .withSchedule(this.scheduleBuilder)
                .build();
    }

    public Set<Trigger> triggers() {
        return Optional.ofNullable(this.triggers).orElseGet(() -> Collections.singleton(this.trigger()));
    }

    public static class JobBuilder {
        private final Class<? extends org.quartz.Job> jobClass;
        private final String jobId;
        private JobDataMap dataMap = new JobDataMap();
        private String group;
        private String description;
        private Boolean shouldRecovery;
        private Boolean storeDurably;
        private Integer priority;
        private Instant startAt;
        private Instant endAt;
        private Set<Trigger> triggers;
        private String cron;
        private Integer repeatCount;
        private Integer repeatIntervalInSecond;
        private ScheduleBuilder<? extends Trigger> scheduleBuilder;

        public JobBuilder(@NotNull String jobId, @NotNull Class<? extends org.quartz.Job> jobClass) {
            this.jobId = jobId;
            this.jobClass = jobClass;
        }

        public <T> JobBuilder data(T data) {
            this.dataMap.put(Job.DATA, data);
            return this;
        }


        public JobBuilder data(JobDataMap dataMap) {
            if (this.dataMap.isEmpty()) this.dataMap = dataMap;
            else this.dataMap.putAll(dataMap);
            return this;
        }

        public JobBuilder group(String group) {
            this.group = group;
            return this;
        }

        public JobBuilder description(String description) {
            this.description = description;
            return this;
        }

        public JobBuilder shouldRecovery(Boolean shouldRecovery) {
            this.shouldRecovery = shouldRecovery;
            return this;
        }

        public JobBuilder storeDurably(Boolean storeDurably) {
            this.storeDurably = storeDurably;
            return this;
        }

        public JobBuilder priority(Integer priority) {
            this.priority = priority;
            return this;
        }

        public JobBuilder startAt(Instant startAt) {
            this.startAt = startAt;
            return this;
        }

        public JobBuilder endAt(Instant endAt) {
            this.endAt = endAt;
            return this;
        }

        public JobBuilder withTriggers(Set<Trigger> triggers) {
            this.triggers = triggers;
            return this;
        }

        public JobBuilder repeat(String cron) {
            this.cron = cron;
            return this;
        }


        public JobBuilder repeat(int repeatCount, int repeatIntervalInSecond) {
            this.repeatCount = repeatCount;
            this.repeatIntervalInSecond = repeatIntervalInSecond;
            return this;
        }

        public JobBuilder withSchedule(ScheduleBuilder<? extends Trigger> scheduler) {
            this.scheduleBuilder = scheduler;
            return this;
        }

        public Job build() {
            Job job = new Job(this.jobId, this.jobClass);
            job.dataMap = this.dataMap;
            job.group = this.group;
            job.description = this.description;
            job.shouldRecovery = this.shouldRecovery;
            job.storeDurably = this.storeDurably;
            job.priority = this.priority;
            job.startAt = this.startAt;
            job.endAt = this.endAt;
            job.triggers = this.triggers;
            job.cron = this.cron;
            job.repeatCount = this.repeatCount;
            job.repeatIntervalInSecond = this.repeatIntervalInSecond;
            job.scheduleBuilder = this.scheduleBuilder;
            this.parseAnnotationForJobSchedule();
            return job;
        }

        private void parseAnnotationForJobSchedule() {
            if (Objects.isNull(this.jobClass)) return;
            ScheduleJob jobScheduleAnnotation = this.jobClass.getAnnotation(ScheduleJob.class);
            this.cron = jobScheduleAnnotation.cron();
            this.repeatCount = jobScheduleAnnotation.repeatCount();
            this.repeatIntervalInSecond = jobScheduleAnnotation.intervalInSeconds();
        }
    }

}
