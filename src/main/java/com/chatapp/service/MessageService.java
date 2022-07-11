package com.chatapp.service;

import com.chatapp.dto.MsgDTO;
import com.chatapp.enums.MsgStatus;
import com.chatapp.model.Chat;
import com.chatapp.model.Message;
import com.chatapp.model.UChatter;
import com.chatapp.repository.ChatRepository;
import com.chatapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired    MessageRepository msgRepository;
    @Autowired    UserService userService;
    @Autowired    ChatRepository chatRepository;


    public Message sendMessage(MsgDTO msgDTO, UserDetails userDetails) {
        UChatter currentUser = userService.findChatterByUsername(userDetails.getUsername());
        UChatter peerChatter = userService.findChatterById(msgDTO.getReceiverId());

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

    public void findChatRmvMsgAndSave(Message msg, int index ) {
        Chat chat = chatRepository.findById(msg.getChatList().get(index).getId()).get();
        chat.removeMessage(msg.getId());
        chatRepository.save(chat);
    }

    public boolean deleteMessage(long msgId, long time, UserDetails userDetails) {
        if (msgRepository.findById(msgId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message with id '" + msgId + "' not found");
        }
        Message msg = msgRepository.findById(msgId).get();
        UChatter currentUser = userService.findChatterByUsername(userDetails.getUsername());
    System.err.println(Long.valueOf(msg.getTimestamp()) + "  " + time);

        //alguien ha borrado antes
        if (msg.getChatList().size() == 1 && msg.getChatList().get(0).getUserOwner().getId() == currentUser.getId()) {
            findChatRmvMsgAndSave(msg, 0);
            msgRepository.deleteById(msgId); //clean msg from ddbb
            return true;
        }

        //nadie ha borrado es el sender
        if (msg.getChatList().size() == 2 && msg.getChatList().get(0).getUserOwner().getId() == currentUser.getId()) {
            findChatRmvMsgAndSave(msg, 0);
            System.err.println("ay papa" + Long.valueOf(msg.getTimestamp())+ time);
            if (time - Long.valueOf(msg.getTimestamp())  <= 50000) { // borra en 2 min si cometiste un error
                System.err.println("borro el peer");
                System.err.println(Long.valueOf(msg.getTimestamp())- time);
                findChatRmvMsgAndSave(msg, 0);
                msgRepository.deleteById(msgId);//clean msg from ddbb
            }
                return true;
        }
        //nadie ha borrado es el receiver
        if (msg.getChatList().size() == 2 && msg.getChatList().get(1).getUserOwner().getId() == currentUser.getId()) {
            findChatRmvMsgAndSave(msg, 1);
            return true;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This message is not in any of your chats");
    }


    public boolean clearMessageHistory(long chatId, UserDetails userDetails) {
        UChatter currentUser = userService.findChatterByUsername(userDetails.getUsername());
        Chat chat = chatRepository.findById(chatId).get();
        if (chat.getUserOwner().getId() != currentUser.getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This chat is not yours");
        }
        chat.setMsgList(new ArrayList<>());
        chatRepository.save(chat);
        return true;
    }

//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ checked, cleaned and tested code


}
