package com.lc.datasynch.websocket.handler;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class SpringSocketHandler implements WebSocketHandler {
    private final Logger logger = LoggerFactory.getLogger(SpringSocketHandler.class);

    private static final ConcurrentHashMap<String, WebSocketSession> CLIENTS = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, AtomicInteger> IDS= new ConcurrentHashMap<>();

    /**
     * 需要注入的Service声明为静态，让其属于类
     */
    private static SaMstrService saMstrService;

    /**
     * 注入的时候，给类的Service注入
     */
    @Autowired
    public void setSaMstrService(SaMstrService saMstrService) {
        SpringSocketHandler.saMstrService = saMstrService;
    }

    /**
     * 连接成功后调用
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("收到新连接: "+session.getId());
        IDS.put(session.getId(),new AtomicInteger(0));
        CLIENTS.put(session.getId(),session);
        //推送
        session.sendMessage(new TextMessage("Connection Successful"));
    }

    /**
     * 处理发送来的消息
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String msg = "连接: "+session.getId() + ", 已收到消息:"+message.getPayload();
        JSONObject jsonObject = JSON.parseObject((String) message.getPayload());
        System.out.println(msg);
        if ("心跳".equals(jsonObject.get("msg"))){
            //重置当前终端心跳次数
            IDS.get(session.getId()).set(0);
        }
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
        CLIENTS.remove(session.getId());
        IDS.remove(session.getId());
    }

    /**
     * 是否支持分片消息
     * @return
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 群发消息
     */
    public static void sendMessage(String message){
        CLIENTS.forEach((id,session)->{
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    /**
     * 心跳机制
     */
    public void sendMessage(String message, WebSocketSession session) {
        try {
//            session.getAsyncRemote().sendText(message);
            session.sendMessage(new TextMessage(JSON.toJSONString(message)));
            logger.info("推送成功：" + message);
        } catch (Exception e) {
            logger.error("推送异常：" + e);
        }
    }
    public void onClose(WebSocketSession session) {
        //判断当前连接是否在线
       if (session.isOpen()) {
           return;
       }
       try {
            session.close();
        } catch (IOException e) {
            logger.error("关闭连接异常：" + e);
        }
    }

    public void heartbeat(){
        //检查所有终端心跳次数
        for (String key:IDS.keySet()){
            //心跳5次及以上的主动断开
            if (IDS.get(key).intValue()>=5){
                logger.info("心跳超时，关闭连接：" + key);
                onClose(CLIENTS.get(key));
            }
        }

        for (String key:CLIENTS.keySet()){
            //记录当前终端心跳次数
            IDS.get(key).incrementAndGet();
            sendMessage("心跳", CLIENTS.get(key));
        }

    }


}
