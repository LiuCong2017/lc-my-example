package com.ws.springws01.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

@Component
public class SpringSocketHandler implements WebSocketHandler {

    /**
     * 连接成功后调用
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("收到新连接: "+session.getId());
    }

    /**
     * 处理发送来的消息
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String msg = "连接: "+session.getId() + ", 已收到消息:"+message;
        System.out.println(msg);
        session.sendMessage(new TextMessage("服务器已收到消息啦"));
    }

    /**
     * 连接出错时调用
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("ws 连接发生错误");
    }

    /**
     * 连接关闭后调用
     * @param session
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("ws 关闭连接");
    }

    /**
     * 是否支持分片消息
     * @return
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public void sendMessage(){

    }


}
