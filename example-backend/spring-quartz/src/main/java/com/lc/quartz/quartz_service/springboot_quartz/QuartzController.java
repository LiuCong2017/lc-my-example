package com.lc.quartz.quartz_service.springboot_quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : [Administrator]
 * @version : [v1.0]
 * @description : []
 * @createTime : [2021/11/18 15:59]
 */
@Slf4j
@RestController
@RequestMapping("/qz")
public class QuartzController {

    @Autowired
    QuartzUtil quartzUtil;

    @PostMapping(value = "/update",produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean sendMsg(@RequestParam String triggerName, @RequestParam String groupName, @RequestParam String newCronTime){
        try {
            return quartzUtil.updateCronScheduleJob(triggerName,groupName,newCronTime);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return false;
    }

}
