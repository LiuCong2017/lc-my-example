package com.ws.springws04.controller;

import com.ws.springws04.service.WebSocketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wsc")
public class TestController {

    @GetMapping("/test")
    public String test() throws InterruptedException {
//        Thread.sleep(100);
        WebSocketService ws = new WebSocketService();
        ws.sendMessage("mis_id","服务器主动发送了消息");
        return "hello";
    }
}
