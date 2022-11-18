package com.lc.quartz.quartz_service;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author : [Administrator]
 * @version : [v1.0]
 * @description : []
 * @createTime : [2021/11/18 10:26]
 */
public class QuartzMain {

    /**
     * 任务调度器
     */
    public static Scheduler createScheduler() throws SchedulerException {
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        return scheduler;
    }

    /**
     * 作业任务
     */
    public static JobDetail createJob(){
        JobBuilder builder = JobBuilder.newJob(MyJob.class);
        builder.withIdentity("jobName","myJob"); //withIdentity(String name, String group):配置Job名称与组名
        JobDetail detail = builder.build();
        return detail;
    }

    /**
     * 时间触发器
     */
    public static Trigger createTrigger(){
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("triggerName","myTrigger")
                .startNow() // 触发器立即生效
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                .build();

        return trigger;
    }


    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = QuartzMain.createScheduler();
        JobDetail job = QuartzMain.createJob();
        Trigger trigger = QuartzMain.createTrigger();

        scheduler.scheduleJob(job,trigger);
        scheduler.start();
    }

}
