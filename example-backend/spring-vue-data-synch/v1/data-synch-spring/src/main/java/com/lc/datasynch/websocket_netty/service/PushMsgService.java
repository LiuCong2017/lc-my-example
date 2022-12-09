package com.lc.datasynch.websocket_netty.service;

/**
 * 五、推送消息接口及实现类
 */
public interface PushMsgService {

    /**
     * 推送给指定用户
     */
    void pushMsgToOne(String userId,String msg);

    /**
     * 推送给所有用户
     */
    void pushMsgToAll(String msg);

}
