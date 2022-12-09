package com.lc.datasynch.websocket_netty;

import com.lc.datasynch.websocket_netty.service.PushMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class WebSocketKeepAlive {

    @Autowired
    PushMsgService pushMsgService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //5秒执行一次
    @Scheduled(fixedRate = 5000)
    public void heartbeat(){
        pushMsgService.pushMsgToAll("心跳 - " + dateFormat.format(new Date()));
        //Todo: 心跳超过指定次数关闭连接

    }

}
