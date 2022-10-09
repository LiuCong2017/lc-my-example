package com.ws.springws02.websocket;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@Service
@ServerEndpoint("/api/websocket/{sid}")
public class WebSocketServer {

    //当前在线连接数
    private static int onlineCount = 0;
    //存放每个客户端对应的MyWebSocket对象
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

    private Session session;

    //接收sid
    private String sid = "";

    /**
     * 连接建立成功
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid")String sid){
        this.session = session;
        webSocketSet.add(this);
        this.sid = sid;
        addOnlineCount(); //在线数+1
        try {
            sendMessage("连接成功");
            System.out.println("在线数："+getOnlineCount());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        subOnlineCount(); //在线数-1
        System.out.println(sid+"关闭,"+"在线数："+getOnlineCount());
    }

    /**
     * 收到客户端消息
     */
    @OnMessage
    public void onMessage(String message,Session session){
        System.out.println("客户端"+sid+"的消息:"+message);
        //群发消息
        for (WebSocketServer item:webSocketSet){
            try {
                item.sendMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String msg,@PathParam("sid") String sid){
        System.out.println("推送{"+msg+"}到"+sid);
        for (WebSocketServer item:webSocketSet){
            //为null则全部推送
            try {
                if (sid==null){
                    item.sendMessage(msg);
                }else if (item.sid.equals(sid)){
                    item.sendMessage(msg);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount(){
        return onlineCount;
    }

    public static synchronized void addOnlineCount(){
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount(){
        WebSocketServer.onlineCount--;
    }

    public static CopyOnWriteArraySet<WebSocketServer> getWebSocketSet(){
        return webSocketSet;
    }

}
