//package com.chatapp.web;
//
//import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketMessagingAutoConfiguration;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//
//@Configuration
//@EnableWebSocketMessageBroker
//public class WebSocketsMessageConfig implements WebSocketMessageBrokerConfigurer {
//
//    @Override public void configureMessageBroker(final MessageBrokerRegistry registry) {
//        registry.setApplicationDestinationPrefixes("/");
//        registry.enableSimpleBroker("/topic");
//    }
//
//    @Override
//    public void registerStompEndpoints(final StompEndpointRegistry registry) {
//       registry.addEndpoint("chat-example").withSockJS();
//    }
//}
//
//@Configuration
//@EnableWebSocketMessageBroker
//public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry config){
//        config.enableSimpleBroker("/start");
//        config.setApplicationDestinationPrefixes("/current");
//    }
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry
//                .addEndpoint("/testchat")
//                .setAllowedOrigins("http://localhost:4200")
//                .withSockJS();
//    }
//}
