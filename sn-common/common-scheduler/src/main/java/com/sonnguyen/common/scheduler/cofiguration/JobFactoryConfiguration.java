//package com.sonnguyen.common.scheduler.cofiguration;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//import org.springframework.scheduling.quartz.SpringBeanJobFactory;
//
//@Configuration
//public class JobFactoryConfiguration {
//    @Bean
//    public SpringBeanJobFactory springBeanJobFactory(ApplicationContext applicationContext) {
//        SpringBeanJobFactory jobFactory = new SpringBeanJobFactory();
//        jobFactory.setApplicationContext(applicationContext);
//        jobFactory.setSchedulerContext();
//        return jobFactory;
//    }
//
//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean(SpringBeanJobFactory jobFactory) {
//        SchedulerFactoryBean factory = new SchedulerFactoryBean();
//        factory.setJobFactory(jobFactory);
//        return factory;
//    }
//
//}
