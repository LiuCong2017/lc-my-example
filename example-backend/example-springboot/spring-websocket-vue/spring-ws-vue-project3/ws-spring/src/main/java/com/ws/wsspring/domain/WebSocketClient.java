package com.ws.wsspring.domain;

import lombok.Data;

import javax.websocket.Session;

@Data
public class WebSocketClient {
    private Session session;// 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private String uri;//连接的uri
}
