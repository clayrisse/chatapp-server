package com.chatapp.controller;

import com.chatapp.dto.UChatterDTO;
import com.chatapp.model.UChatter;
import com.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("chatter/")
public class UserController {

    @Autowired    UserService userService;

    @GetMapping("/check")
    @CrossOrigin()  @ResponseStatus(HttpStatus.OK)
    public String sayhi() {
        System.err.println("------checking connection");
        return "Hiiii, it is connected";
    }

    @PostMapping("/create/user")
    @CrossOrigin()  @ResponseStatus(HttpStatus.CREATED)
    public UChatter createUChatter(@RequestBody UChatterDTO chatterDTO) {
        return userService.addChatter(chatterDTO);
    }

    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ checked, cleaned and tested code

}
