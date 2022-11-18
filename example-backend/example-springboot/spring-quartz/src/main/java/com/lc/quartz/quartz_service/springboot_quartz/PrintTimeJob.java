package com.lc.quartz.quartz_service.springboot_quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @author : [Administrator]
 * @version : [v1.0]
 * @description : []
 * @createTime : [2021/11/18 15:49]
 */
@Component
@DisallowConcurrentExecution
public class PrintTimeJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("执行了定时任务");
    }
}
