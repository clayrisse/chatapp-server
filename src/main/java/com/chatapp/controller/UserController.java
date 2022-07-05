package com.chatapp.controller;

import com.chatapp.dto.UChatterDTO;
import com.chatapp.model.UChatter;
import com.chatapp.repository.UAdminRepository;
import com.chatapp.repository.UChatterRepository;
import com.chatapp.repository.UserRepository;
import com.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("chatter/")
public class UserController {
//
//    @Autowired    UserRepository userRepository;
//    @Autowired    UChatterRepository uChatterRepository;
//    @Autowired    UAdminRepository uAdminRepository;
    @Autowired    UserService userService;

    @GetMapping("/create-account")
    @CrossOrigin()  @ResponseStatus(HttpStatus.OK)
    public String sayhi() {
        System.err.println("------hi 1");
        return "Hiiii";
//        return userService.addUser(chatterDTO);
    }

    @PostMapping("/create-account")
    @CrossOrigin()  @ResponseStatus(HttpStatus.CREATED)
    public UChatter createUChatter(@RequestBody UChatterDTO chatterDTO) {
        System.err.println("------1");
        return userService.addChatter(chatterDTO);
    }


}
