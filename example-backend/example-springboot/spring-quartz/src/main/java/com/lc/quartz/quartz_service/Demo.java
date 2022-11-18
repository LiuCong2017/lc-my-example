package com.lc.quartz.quartz_service;

import cn.hutool.core.date.DateUtil;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * @author : [Administrator]
 * @version : [v1.0]
 * @description : []
 * @createTime : [2021/11/18 10:24]
 */
public class Demo {
    public static final String COUNT = "count";

    private static int num = 1;

    public static void main(String[] args) throws SchedulerException {
//        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();

        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity("jobName","jobGroupName")
                .usingJobData(COUNT,num)
                .build();

        // 各种builder 的基本使用
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInSeconds(3);
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("*/3 * * * * ?");
        CalendarIntervalScheduleBuilder calendarIntervalScheduleBuilder = CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withInterval(3, DateBuilder.IntervalUnit.SECOND);
        DailyTimeIntervalScheduleBuilder dailyTimeIntervalScheduleBuilder = DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule().withIntervalInSeconds(3);

        Trigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(simpleScheduleBuilder)
                .startNow()
                .build();

        scheduler.scheduleJob(job,trigger);
    }


    @PersistJobDataAfterExecution //保存在JobDataMap传递的参数
    @DisallowConcurrentExecution
    public static class HelloJob implements Job {

        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
            int count = jobDataMap.getInt(COUNT);
            System.out.println("=========="+ DateUtil.formatDateTime(new Date())+",count="+count);
            jobDataMap.put(COUNT,++num);
        }
    }

}



