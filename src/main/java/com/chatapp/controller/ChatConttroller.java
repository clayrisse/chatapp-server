package com.chatapp.controller;

import com.chatapp.dto.UChatterDTO;
import com.chatapp.model.Chat;
import com.chatapp.model.UChatter;
import com.chatapp.repository.ChatRepository;
import com.chatapp.service.ChatService;
import com.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("chatter/chat/")
public class ChatConttroller {

    @Autowired    ChatRepository chatRepository;
    @Autowired    ChatService chatService;
    @Autowired    UserService userService;


    @GetMapping("open/{peerUsername}")
    @CrossOrigin()  @ResponseStatus(HttpStatus.OK)
    public Chat openOrCreateChat(@PathVariable String peerUsername, @AuthenticationPrincipal UserDetails userDetails) {
        return chatService.openOrCreateChat(peerUsername, userDetails);
    }


    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ checked, cleaned and tested code


    @DeleteMapping("{chatId}/delete")
    @CrossOrigin()  @ResponseStatus(HttpStatus.OK)
    public String deleteChat(@PathVariable long chatId, @AuthenticationPrincipal UserDetails userDetails) {
        System.err.println("entro a borrar");
        return chatService.deleteChat(chatId, userDetails);
    }

}
