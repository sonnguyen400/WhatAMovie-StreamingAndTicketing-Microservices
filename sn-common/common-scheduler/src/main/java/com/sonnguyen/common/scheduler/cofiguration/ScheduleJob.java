package com.sonnguyen.common.scheduler.cofiguration;

import com.sonnguyen.common.scheduler.Job;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ScheduleJob {

    /**
     * Tên group của job
     */
    String group() default Job.JOB_GROUP_DEFAULT;

    /**
     * Biểu thức cron, nếu có
     */
    String cron() default "";

    /**
     * Số lần chạy lại (chỉ dùng khi không có cron)
     */
    int repeatCount() default 0;

    /**
     * Khoảng thời gian giữa các lần chạy (tính bằng giây, chỉ dùng khi không có cron)
     */
    int intervalInSeconds() default 1;
}
