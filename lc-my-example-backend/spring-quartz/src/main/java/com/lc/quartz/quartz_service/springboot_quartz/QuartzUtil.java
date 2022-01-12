package com.lc.quartz.quartz_service.springboot_quartz;

import cn.hutool.core.util.ObjectUtil;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author : [Administrator]
 * @version : [v1.0]
 * @description : []
 * @createTime : [2021/11/18 15:28]
 */
@Component
public class QuartzUtil {

    @Autowired
    private Scheduler scheduler;


//    CronTrigger  trigger = TriggerBuilder.newTrigger()
//            /**给当前JobDetail添加参数，K V形式，链式调用，可以传入多个参数，在Job实现类中，可以通过jobExecutionContext.getTrigger().getJobDataMap().get("orderNo")获取值*/
//            .usingJobData("orderNo", "123456")
//            /**添加认证信息，有3种重写的方法，我这里是其中一种，可以查看源码看其余2种*/
//            .withIdentity("我是name","我是group")
//            /**立即生效*/
////      .startNow()
//            /**开始执行时间*/
//            .startAt(start)
//            /**结束执行时间,不写永久执行*/
//            .endAt(start)
//            /**添加执行规则，SimpleTrigger、CronTrigger的区别主要就在这里,我这里是demo，写了个每2分钟执行一次*/
//            .withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 * * * ?"))
//            .build();//执行

    public CronTrigger createTrigger(String name,String groupName,String cronExp){
        CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(cronExp);
        return TriggerBuilder.newTrigger().withIdentity(name,groupName).withSchedule(builder).build();
    }

    /**通过JobBuilder.newJob()方法获取到当前Job的具体实现(以下均为链式调用)
     * 这里是固定Job创建，所以代码写死XXX.class
     * 如果是动态的，根据不同的类来创建Job，则 ((Job)Class.forName("com.zy.job.TestJob").newInstance()).getClass()
     * 即是 JobBuilder.newJob(((Job)Class.forName("com.zy.job.TestJob").newInstance()).getClass())
     * */
//    JobDetail jobDetail = JobBuilder.newJob(TestJob.class)
//            /**给当前JobDetail添加参数，K V形式*/
//            .usingJobData("name","zy")
//            /**给当前JobDetail添加参数，K V形式，链式调用，可以传入多个参数，在Job实现类中，可以通过jobExecutionContext.getJobDetail().getJobDataMap().get("age")获取值*/
//            .usingJobData("age",23)
//            /**添加认证信息，有3种重写的方法，我这里是其中一种，可以查看源码看其余2种*/
//            .withIdentity("我是name","我是group")
//            .build();//执行

    public JobDetail createCronScheduleJob(String jobName,Class<? extends QuartzJobBean> jobClass){
        return JobBuilder.newJob(jobClass).withIdentity(jobName).build();
    }

    /**
     * 修改cron任务的执行间隔
     */
    public boolean updateCronScheduleJob(String triggerName,String triggerGroupName,String newCronTime) throws SchedulerException {
        Date date;
        TriggerKey key = new TriggerKey(triggerName,triggerGroupName);
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(key);
        if (ObjectUtil.isEmpty(trigger)){
            System.out.println("获取的Trigger为null");
            return false;
        }
        String oldTime = trigger.getCronExpression();
        if (!oldTime.equalsIgnoreCase(newCronTime)){
            CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(newCronTime);
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerName,triggerGroupName).withSchedule(builder).build();
            date = scheduler.rescheduleJob(key,cronTrigger);
            return true;
        }else {
            System.out.println("oldTime等于newCronTime，修改结束");
            return false;
        }
    }


}
