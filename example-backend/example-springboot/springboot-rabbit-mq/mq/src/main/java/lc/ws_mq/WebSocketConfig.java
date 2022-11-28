package lc.ws_mq;

@Component
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
    @Resource
    private MyWebSocketHandler handler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(handler, "/wsMy").addInterceptors(new HandshakeInterceptor() {
            @Override
            public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
                String jspCode = ((ServletServerHttpRequest) request).getServletRequest().getParameter("jspCode");
                if (jspCode != null) {
                    map.put("jspCode", jspCode);
                } else {
                    return false;
                }

                return true;
            }

            @Override
            public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

            }
        });
    }
}

