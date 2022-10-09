package com.ws.springws04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class WebSocketScheduled {
    @Autowired
    private WebSocketService webSocketService;

    /**
     * 定时给连接到WebSocket的终端发送消息，超过指定次数未回应的终端视为离线，主动关闭连接。
     */
    @Scheduled(cron = "0/15 * * * * ?")
    public void heartbeat(){
        webSocketService.heartbeat();
    }
}
