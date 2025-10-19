package com.sonnguyen.common.scheduler;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class Scheduler {
    org.quartz.Scheduler scheduler;

    public void schedule(Job job, boolean replace) throws SchedulerException {
        this.scheduler.scheduleJob(job.getDetail(), job.triggers(), replace);
    }

    public void schedule(Job job) throws SchedulerException {
        this.schedule(job, true);
    }

    public void schedule(List<Job> jobs, boolean replace) throws SchedulerException {
        Map<JobDetail, Set<? extends Trigger>> jobDetailSetMap = jobs.stream().collect(Collectors.toMap(Job::getDetail, Job::triggers));
        this.scheduler.scheduleJobs(jobDetailSetMap, replace);
    }

    public void schedule(List<Job> jobs) throws SchedulerException {
        this.schedule(jobs, true);
    }

}
