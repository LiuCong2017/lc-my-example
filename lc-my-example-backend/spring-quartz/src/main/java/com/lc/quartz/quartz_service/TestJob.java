package com.lc.quartz.quartz_service;

import cn.hutool.core.date.DateUtil;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @author : [Administrator]
 * @version : [v1.0]
 * @description : []
 * @createTime : [2021/11/18 16:43]
 */
@DisallowConcurrentExecution//Job中的任务有可能并发执行，例如任务的执行时间过长，而每次触发的时间间隔太短，则会导致任务会被并发执行。如果是并发执行，就需要一个数据库锁去避免一个数据被多次处理。
public class TestJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.err.println(jobExecutionContext.getJobDetail().getJobDataMap().get("name"));
        System.err.println(jobExecutionContext.getJobDetail().getJobDataMap().get("age"));
        System.err.println(jobExecutionContext.getTrigger().getJobDataMap().get("orderNo"));
        System.err.println("定时任务执行，当前时间："+ DateUtil.formatDateTime(new Date()));
    }
}
