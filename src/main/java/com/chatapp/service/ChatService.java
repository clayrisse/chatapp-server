package com.chatapp.service;

import com.chatapp.model.Chat;
import com.chatapp.model.UChatter;
import com.chatapp.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Service
public class ChatService {

    @Autowired    ChatRepository chatRepository;
    @Autowired    UserService userService;


    public Chat openOrCreateChat(String peerUsername, UserDetails userDetails) {
        UChatter currentChatter = userService.findChatterByUsername(userDetails.getUsername());
        UChatter peerChatter = userService.findChatterByUsername(peerUsername);
        if (chatRepository.findByPeerId(peerChatter.getId()).isEmpty()) {
             return chatRepository.save(Chat.builder().peerId(peerChatter.getId())
                                                    .msgList(new ArrayList<>())
                                                    .userOwner(currentChatter).build());
        }
        return chatRepository.findByPeerId(peerChatter.getId()).get();
    }


    public String deleteChat(long chatId, UserDetails userDetails) {
        if (chatRepository.findById(chatId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Chat with id '" + chatId + "' not found");
        }
        Chat chat = chatRepository.findById(chatId).get();
        UChatter currentChatter = userService.findChatterByUsername(userDetails.getUsername());
        if(currentChatter.getId() != chat.getUserOwner().getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This chat doesn't belong to you naughty human");
        }
        chatRepository.deleteById(chat.getId());
        return "Chat deleted";
    }

    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ checked, cleaned and tested code


    public Chat getChatById(long chatId, UserDetails userDetails) {
        UChatter currentChatter = userService.findChatterByUsername(userDetails.getUsername());
        if (chatRepository.findById(chatId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Chat not found");
        }
        if (chatRepository.findById(chatId).get().getUserOwner().getId() != currentChatter.getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Chat doesn't belong to this user");
        }
        return chatRepository.findById(chatId).get();
    }

}
