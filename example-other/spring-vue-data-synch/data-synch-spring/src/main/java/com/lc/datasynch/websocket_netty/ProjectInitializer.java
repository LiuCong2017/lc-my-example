package com.lc.datasynch.websocket_netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 三、管道配置
 */
@Component
public class ProjectInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * websocket协议名
     */
    static final String WEBSOCKET_PROTOCOL = "webSocket";

    /**
     * websocket路径
     */
    @Value("${webSocket.netty.path:/webSocket}")
    String webSocketPath;

    @Autowired
    WebSocketHandler webSocketHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline(); //设置管道
        //流水线管理通道中的处理程序(Handler),用来处理业务,ws协议基于http协议，此处使用http编解码器
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ObjectEncoder());
        //以块的方式来写的处理器
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpObjectAggregator(8192));
        pipeline.addLast(new WebSocketServerProtocolHandler(webSocketPath,WEBSOCKET_PROTOCOL,true,65536*10));
        //自定义的Handler,处理业务逻辑
        pipeline.addLast(webSocketHandler);
    }
}
