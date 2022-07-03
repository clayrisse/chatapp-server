//package com.chatapp.controller;
//
//import com.chatapp.model.ChatMessage;
//import com.chatapp.model.MessageType;
//import org.apache.catalina.SessionEvent;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.event.EventListener;
//import org.springframework.messaging.simp.SimpMessageSendingOperations;
//import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.messaging.SessionDisconnectEvent;
//
//
//@Component
//public class WebSocketEventListener {
//    //things are missing here time 17:00
//    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketEventListener.class);
//
//    @Autowired
//    private SimpMessageSendingOperations sendingOperations;
//
//    @EventListener
//    public void  handleWebSocketConnectListener(final SessionEvent event) {
//        LOGGER.info("hello and wellcome to chatapp");
//    }
//
//    @EventListener
//    public void handleWebSocketDisconnectListener(final SessionDisconnectEvent event) {
//        final StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//        final String username = (String) headerAccessor.getSessionAttributes().get("username");
//        final ChatMessage chatMessage = ChatMessage.builder()
//                .type(MessageType.DISCONNECT)
//                .sender(username)
//                .build();
//        sendingOperations.convertAndSend("/topic/public", chatMessage);
//    }
//}
