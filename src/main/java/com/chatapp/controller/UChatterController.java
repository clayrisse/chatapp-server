package com.chatapp.controller;

import com.chatapp.dto.UChatterDTO;
import com.chatapp.model.UChatter;
import com.chatapp.repository.UAdminRepository;
import com.chatapp.repository.UChatterRepository;
import com.chatapp.repository.UserRepository;
import com.chatapp.service.UChatterService;
import com.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("chatter/")
public class UChatterController {

    @Autowired    UChatterRepository uChatterRepository;
    @Autowired UserService userService;


    //=================================================================== check list


    @GetMapping("{id}")
    @CrossOrigin()  @ResponseStatus(HttpStatus.OK)
    public UChatter getChatterById(@PathVariable long id, @AuthenticationPrincipal UserDetails userDetails) {
        return userService.getChatterById(id, userDetails);
    }

    @GetMapping("user/{username}")
    @CrossOrigin()  @ResponseStatus(HttpStatus.OK)
    public UChatter  getChatterByUsername(@PathVariable String username, @AuthenticationPrincipal UserDetails userDetails) {
        return userService. getChatterByUsername(username, userDetails);
    }

    @PutMapping("{id}/update")
    public UChatter updateChatter(@PathVariable long id, @RequestBody UChatterDTO chatterDTO) {
        System.err.println("------1");
        return userService.updateChatter(id, chatterDTO);
    }




}
