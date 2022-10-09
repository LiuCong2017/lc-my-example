package com.ws.springws01.config;

import com.ws.springws01.handler.SpringSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class SpringSocketConfig implements WebSocketConfigurer {
    @Autowired
    private SpringSocketHandler socketHandler;

    /**
     * 把自定义 Handle 注册到 "/my-ws" 上面并设置了一下跨域，
     * 在整个配置类上还要打上@EnableWebSocket 注解，用于开启 WS 监听。
     * @param registry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketHandler,"/my-ws").setAllowedOrigins("*");
    }
}
