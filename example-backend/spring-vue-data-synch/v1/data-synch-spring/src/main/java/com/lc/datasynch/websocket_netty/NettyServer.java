package com.lc.datasynch.websocket_netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

/**
 * 一、Netty服务器
 */
@Component
public class NettyServer {

    static final Logger log = LoggerFactory.getLogger(NettyServer.class);

    /**
     * 端口号
     */
    @Value("${webSocket.netty.port:8888}")
    int port;

    EventLoopGroup bossGroup;
    EventLoopGroup workGroup;

    @Autowired
    ProjectInitializer nettyInitializer;

    @PostConstruct
    public void start(){
        new Thread(()->{
            bossGroup = new NioEventLoopGroup(); //处理连接请求
            workGroup = new NioEventLoopGroup(); //处理与客户端的读写操作
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workGroup);
            bootstrap.channel(NioServerSocketChannel.class); //设置Channel
            bootstrap.localAddress(new InetSocketAddress(port)); //设置监听端口
            bootstrap.childHandler(nettyInitializer); //设置管道

            ChannelFuture channelFuture = null;
            try {
                channelFuture = bootstrap.bind().sync();
                log.info("服务启动，监听:{}",channelFuture.channel().localAddress());
                channelFuture.channel().closeFuture().sync(); //监听关闭通道
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 释放资源
     */
    @PreDestroy
    public void destroy() throws InterruptedException {
        if (bossGroup!=null) bossGroup.shutdownGracefully().sync();
        if (workGroup!=null) workGroup.shutdownGracefully().sync();
    }

}
