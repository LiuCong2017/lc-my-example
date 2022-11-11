package com.lc.datasynch.websocket_netty;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 二、Netty配置
 * 管理全局Channel和用户对应的channel(推送消息)
 */
public class NettyConfig {

    private static volatile ChannelGroup channelGroup = null; //全局单例channel组，管理所有channel
    private static volatile ConcurrentHashMap<String, Channel> channelMap = null; //存放请求id与channel的映射

    //定义锁
    private static final  Object lock1 = new Object();
    private static final  Object lock2 = new Object();

    public static ChannelGroup getChannelGroup(){
        if (null==channelGroup){
            synchronized (lock1){
                if (null==channelGroup){
                    channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
                }
            }
        }
        return channelGroup;
    }

    public static ConcurrentHashMap<String, Channel> getChannelMap(){
        if (null==channelMap){
            synchronized (lock2){
                if (null==channelMap){
                    channelMap = new ConcurrentHashMap<>();
                }
            }
        }
        return channelMap;
    }

    public static Channel getChannel(String userId){
        if (null==channelMap){
            return getChannelMap().get(userId);
        }
        return channelMap.get(userId);
    }

}
