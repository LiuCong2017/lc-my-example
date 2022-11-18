package com.ws.wsspring.controller;

import com.ws.wsspring.service.WebSocketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/websocket")
public class WebSocketController {

    @GetMapping("/pushOne")
    public void pushOne(){
        WebSocketService.sendMessage("name1","Message from server");
    }

}
