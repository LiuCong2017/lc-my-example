package com.lc.aop;

import com.lc.aop.service.Performance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author : [Administrator]
 * @version : [v1.0]
 * @description : []
 * @createTime : [2021/11/19 14:34]
 */
@Component
public class BootLoadListening implements ApplicationListener<ApplicationStartedEvent> {
    @Autowired
    Performance performance;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        performance.perform();
    }
}
