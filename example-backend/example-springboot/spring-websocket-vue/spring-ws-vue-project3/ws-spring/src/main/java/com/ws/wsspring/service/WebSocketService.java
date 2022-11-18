package com.ws.wsspring.service;

import cn.hutool.core.util.StrUtil;
import com.ws.wsspring.domain.WebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/ws/{name}")
@Component
public class WebSocketService {

    private static final Logger log = LoggerFactory.getLogger(WebSocketService.class);

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
    private static ConcurrentHashMap<String, WebSocketClient> webSocketMap = new ConcurrentHashMap<>();

    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /**接收name*/
    private String name="";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("name")String name){
        if (!webSocketMap.containsKey(name)){
            addOnlineCount(); // 在线数 +1
        }
        this.session = session;
        this.name = name;
        WebSocketClient client = new WebSocketClient();
        client.setSession(session);
        client.setUri(session.getRequestURI().toString());
        webSocketMap.put(name,client);

        log.info("----------------------------------------------------------------------------");
        log.info("用户连接:"+name+",当前在线人数为:" + getOnlineCount());
        try {
            sendMessage("来自后台的反馈：连接成功");
        } catch (IOException e) {
            log.error("用户:"+name+",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        if (webSocketMap.containsKey(name)){
            webSocketMap.remove(name);
            if (webSocketMap.size()>0){
                subOnlineCount();//从set中删除
            }
        }
        log.info("----------------------------------------------------------------------------");
        log.info(name+"用户退出,当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     */
    @OnMessage
    public void onMessage(String msg,Session session){
        log.info("收到用户消息:"+name+",报文:"+msg);
        //可以群发消息
        //消息保存到数据库、redis
        if(StrUtil.isNotBlank(msg)){

        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:"+this.name+",原因:"+error.getMessage());
        error.printStackTrace();
    }

    /**
     * 连接服务器成功后主动推送
     */
    public void sendMessage(String msg) throws IOException {
        synchronized (session){
            this.session.getBasicRemote().sendText(msg);
        }
    }

    /**
     * 向指定客户端发送消息
     */
    public static void sendMessage(String name,String msg){
        try {
            WebSocketClient client = webSocketMap.get(name);
            if (client!=null){
                client.getSession().getBasicRemote().sendText(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketService.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketService.onlineCount--;
    }

    public static void setOnlineCount(int onlineCount) {
        WebSocketService.onlineCount = onlineCount;
    }

    public static ConcurrentHashMap<String, WebSocketClient> getWebSocketMap() {
        return webSocketMap;
    }

    public static void setWebSocketMap(ConcurrentHashMap<String, WebSocketClient> webSocketMap) {
        WebSocketService.webSocketMap = webSocketMap;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
