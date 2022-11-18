package com.ws.springws04.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.server.ServerEndpoint;

@Configuration
public class WebSocketConfig {

    /**
     * ServerEndpointExporter注入
     * 该Bean会自动注册使用@ServerEndpoint注解申明的WebSocket endpoint
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

}
