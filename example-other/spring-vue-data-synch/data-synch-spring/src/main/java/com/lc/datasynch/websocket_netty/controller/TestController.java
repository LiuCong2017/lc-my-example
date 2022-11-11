package com.lc.datasynch.websocket_netty.controller;

import com.lc.datasynch.websocket_netty.service.PushMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/push")
public class TestController {
    @Autowired
    PushMsgService pushMsgService;

    @GetMapping("/{uid}")
    public void pushOne(@PathVariable String uid){
        pushMsgService.pushMsgToOne(uid,"hello");
    }

    @GetMapping("/all")
    public void pushAll(){
        pushMsgService.pushMsgToAll("群发消息");
    }
}
