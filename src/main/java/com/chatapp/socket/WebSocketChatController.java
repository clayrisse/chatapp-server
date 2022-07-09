package com.chatapp.socket;

import com.chatapp.dto.MsgDTO;
import com.chatapp.model.Message;
import com.chatapp.service.ChatService;
import com.chatapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
public class WebSocketChatController {

    @Autowired private SimpMessagingTemplate messagingTemplate;
//    @Autowired private ChatMessageService chatMessageService;
//    @Autowired private ChatRoomService chatRoomService;

    @Autowired    ChatService chatService;
    @Autowired    MessageService msgService;
    @Autowired    WebSocketService webSocketService;

    @MessageMapping("/chat")
    @CrossOrigin
    public void processMessage(@Payload MsgDTO msgDTO) {
        Message msg = webSocketService.sendSocketMessage(msgDTO );
        messagingTemplate.convertAndSendToUser(String.valueOf(msg.getReceiverId()),"/queue/messages", msg);

//                    new ChatNotification(
//                            saved.getId(),
//                            saved.getSenderId(),
//                            saved.getSenderName()));
    }
}


