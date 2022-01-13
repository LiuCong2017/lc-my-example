package com.lc.quartz.quartz_service.springboot_quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author : [Administrator]
 * @version : [v1.0]
 * @description : [监听器，启动定时任务]
 * @createTime : [2021/11/18 15:43]
 */
@Slf4j
@Component
public class QuartzConfig implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private QuartzUtil quartzUtil;
    @Autowired
    private Scheduler scheduler;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("监听程序启动完成");
        String jobName = "printTimeJob";
        String cronTriggerName = "printTimeCronTrigger";
        String cronTriggerGroupName = "printTimeCronTriggerGroup";

        //创建定时任务，每3秒执行一次
        JobDetail cronScheduleJob = quartzUtil.createCronScheduleJob(jobName,PrintTimeJob.class);
        Trigger trigger = quartzUtil.createTrigger(cronTriggerName,cronTriggerGroupName,"0/2 * * * * ?");

        JobKey jobKey = new JobKey(jobName);

        try {
            scheduler.pauseJob(jobKey);

            if (scheduler.checkExists(jobKey)){
                scheduler.deleteJob(jobKey);
            }
            scheduler.scheduleJob(cronScheduleJob,trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

//        try {
//            // 暂停触发器的计时
//            scheduler.pauseTrigger(trigger.getKey());
//            // 移除触发器中的任务
//            scheduler.unscheduleJob(trigger.getKey());
//            // 删除任务
//            scheduler.deleteJob(jobDetail.getKey());
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
    }


}
