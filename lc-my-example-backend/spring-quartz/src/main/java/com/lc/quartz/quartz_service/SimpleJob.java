package com.lc.quartz.quartz_service;

import org.quartz.*;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : [Administrator]
 * @version : [v1.0]
 * @description : []
 * @createTime : [2021/11/18 11:33]
 */
public class SimpleJob extends QuartzJobBean {

    private final static AtomicInteger counter = new AtomicInteger(1);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("sringboot quartz 定时任务");
    }

    public static JobDetail creatJob() {
        JobDetail simpleJob = JobBuilder.newJob(SimpleJob.class) //newJob()中传入的Job类必须是继承了QuartzJobBean的类
                .withIdentity("SimpleJob", "AnchorJobs") //(name, group)标识唯一一个JobDetail
                .storeDurably() //在没有Trigger关联的情况下保存该任务到调度器
                .build();
        return simpleJob;
    }

    public static Trigger createTrigger(){
//        SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(3)
//                .repeatForever();
//        SimpleTrigger trigger = TriggerBuilder.newTrigger()
//                .withIdentity("SimpleTrigger","AnchorTriggers")
//                .startNow()
//                .withSchedule(builder)
//                .build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");    //Cron表达式，每5秒执行一次
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("CronJob", "AnchorTriggers")    //(name, group)唯一标识一个Trigger
                .startNow()                         //调用scheduler.scheduleJob()后立即开始执行定时任务
                .withSchedule(scheduleBuilder)      //不同的scheduleBuilder
                .build();
        return cronTrigger;
    }

    @Resource
    private Scheduler scheduler;

    public void start() throws SchedulerException {
        scheduler.scheduleJob(SimpleJob.creatJob(),SimpleJob.createTrigger());
    }

}