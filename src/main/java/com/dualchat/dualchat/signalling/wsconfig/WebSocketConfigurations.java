package com.dualchat.dualchat.signalling.wsconfig;


import org.springframework.context.annotation.Configuration;

import org.springframework.web.socket.config.annotation.*;


@Configuration
@EnableWebSocket
public class WebSocketConfigurations  implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
            webSocketHandlerRegistry.addHandler(new SocketHandler(),"/socket")
                    .setAllowedOrigins("*");
    }

}
