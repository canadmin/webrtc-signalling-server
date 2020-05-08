package com.dualchat.dualchat.signalling.wsconfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Component
public class SocketHandler extends TextWebSocketHandler {

    private static final Logger LOG = LoggerFactory.getLogger(SocketHandler.class);
    private static final String LOGIN_TYPE = "login";

    private ObjectMapper objectMapper = new ObjectMapper();

    private Map<String, WebSocketSession> clients = new HashMap<>();
    private Map<String, String> clientIds = new HashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        LOG.debug("handleTextMessage : {}", message.getPayload());
        SignalMessage signalMessage = objectMapper.readValue(message.getPayload(), SignalMessage.class);

        if (LOGIN_TYPE.equalsIgnoreCase(signalMessage.getEvent())) {
            String username = (String) signalMessage.getData();

            WebSocketSession client = clients.get(username);

            if (client == null || !client.isOpen()) {
                LOG.debug("Login {} : OK", username);
                clients.put(username, session);
                clientIds.put(session.getId(), username);
                SignalMessage out = new SignalMessage();
                out.setEvent("call");
                out.setDest(clientIds.get(session.getId()));
                out.setData(signalMessage.getData());

                String strJsonMSG = objectMapper.writeValueAsString(out);
            } else {
                LOG.debug("Login {} : KO", username);
            }
        }else { // eğer bi çağrı isteği ise
            String destination = signalMessage.getDest(); // hedefi al
            WebSocketSession destinationSocket = clients.get(destination);

            if(destinationSocket != null && destinationSocket.isOpen()){
                SignalMessage out = new SignalMessage();
                out.setEvent(signalMessage.getEvent());
                out.setDest(clientIds.get(session.getId()));
                out.setData(signalMessage.getData());

                String strJsonMSG = objectMapper.writeValueAsString(out);

                destinationSocket.sendMessage(new TextMessage(strJsonMSG));
            }
        }


    }


}
