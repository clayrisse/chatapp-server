package com.chatapp.controller;

import com.chatapp.dto.MsgDTO;
import com.chatapp.model.Message;
import com.chatapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("chatter/msg")
public class MessageController {

    @Autowired    MessageService msgService;

    @PostMapping("/")
    @CrossOrigin()
    @ResponseStatus(HttpStatus.CREATED)

    public Message sendMessage(@RequestBody MsgDTO msgDTO, @AuthenticationPrincipal UserDetails userDetails) {
        System.err.println("entreeee-------------------------"+ msgDTO.toString());
        return msgService.sendMessage(msgDTO, userDetails);
    }

    @DeleteMapping("/{msgId}")
    @CrossOrigin()  @ResponseStatus(HttpStatus.OK)
    public boolean deleteMessage(@PathVariable long msgId, @AuthenticationPrincipal UserDetails userDetails) {
        return msgService.deleteMessage(msgId, userDetails);
    }

    @DeleteMapping("/clear/chat/{chatId}")
    @CrossOrigin()  @ResponseStatus(HttpStatus.OK)
    public boolean clearMessageHistory(@PathVariable long chatId, @AuthenticationPrincipal UserDetails userDetails) {
        return msgService.clearMessageHistory(chatId, userDetails);
    }

    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ checked, cleaned and tested code
}



