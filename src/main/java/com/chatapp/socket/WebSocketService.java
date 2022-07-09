package com.chatapp.socket;

import com.chatapp.dto.MsgDTO;
import com.chatapp.enums.MsgStatus;
import com.chatapp.model.Chat;
import com.chatapp.model.Message;
import com.chatapp.model.UChatter;
import com.chatapp.repository.ChatRepository;
import com.chatapp.repository.MessageRepository;
import com.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class WebSocketService {

    @Autowired    MessageRepository msgRepository;
    @Autowired    UserService userService;
    @Autowired    ChatRepository chatRepository;


    public Message sendSocketMessage(MsgDTO msgDTO) {

        System.err.println(msgDTO.toString());
        UChatter currentUser = userService.findChatterById(msgDTO.getSenderId());
        UChatter peerChatter = userService.findChatterById(msgDTO.getReceiverId());//

        Message msg = Message.builder().msgStatus(MsgStatus.SEND)
                .senderId(currentUser.getId())
                .receiverId(msgDTO.getReceiverId())
                .content(msgDTO.getContent())
                .timestamp(msgDTO.getTimestamp())
                .chatList(new ArrayList<>()).build();
        msgRepository.save(msg);

        //-----finds or creates chats and saves massage in current user
        Chat userChat = Chat.builder().peerId(msgDTO.getReceiverId())
                .senderId(currentUser.getId()).msgList(new ArrayList<>()).userOwner(currentUser).build();
        Optional<Chat> opUserChat = currentUser.getChatList().stream().filter(c->c.getPeerId()==msgDTO.getReceiverId()).findFirst();
        if(opUserChat.isPresent())  userChat = opUserChat.get();
        userChat.addMessage(msg);
        chatRepository.save(userChat);

        //-----finds or creates chats and saves massage in peer user
        Chat peerChat = Chat.builder().peerId(currentUser.getId())
                .senderId(peerChatter.getId()).msgList(new ArrayList<>()).userOwner(peerChatter).build();
        Optional<Chat> opPeerChat = peerChatter.getChatList().stream().filter(c->c.getPeerId()==currentUser.getId()).findFirst();
        if(opPeerChat.isPresent()) peerChat = opPeerChat.get();
        peerChat.addMessage(msg);
        chatRepository.save(peerChat);

        return msg;
    }
}
