package com.chatapp.controller;

import com.chatapp.model.Chat;
import com.chatapp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("chatter/chat/")
public class ChatController {

    @Autowired    ChatService chatService;


    @GetMapping("open/{peerUsername}")
    @CrossOrigin()  @ResponseStatus(HttpStatus.OK)
    public Chat openOrCreateChat(@PathVariable String peerUsername, @AuthenticationPrincipal UserDetails userDetails) {
        return chatService.openOrCreateChat(peerUsername, userDetails);
    }


    @DeleteMapping("{chatId}/delete")
    @CrossOrigin()  @ResponseStatus(HttpStatus.OK)
    public String deleteChat(@PathVariable long chatId, @AuthenticationPrincipal UserDetails userDetails) {
        System.err.println("entro a borrar");
        return chatService.deleteChat(chatId, userDetails);
    }

    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ checked, cleaned and tested code

    @GetMapping("{chatId}")
    @CrossOrigin()  @ResponseStatus(HttpStatus.OK)
    public Chat getChatById(@PathVariable long chatId, @AuthenticationPrincipal UserDetails userDetails) {
        return chatService.getChatById(chatId, userDetails);
    }


}
