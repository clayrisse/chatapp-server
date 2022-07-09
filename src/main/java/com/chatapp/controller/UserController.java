package com.chatapp.controller;

import com.chatapp.dto.UChatterDTO;
import com.chatapp.model.UChatter;
import com.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    @PostMapping("/signup")
    @CrossOrigin()  @ResponseStatus(HttpStatus.CREATED)
    public UChatter createUChatter(@RequestBody UChatterDTO chatterDTO) {
        System.err.println("-------signing up");
        return userService.addChatter(chatterDTO);
    }

    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ checked, cleaned and tested code

//    @CrossOrigin
//    @RequestMapping(method = RequestMethod.POST, path = "/login")
    @CrossOrigin
    @GetMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public UChatter getLoggedUser(@AuthenticationPrincipal UserDetails userDetails) {
        System.err.println("-------Loging in");
        return userService.getLoggedUser(userDetails);
    }

    @GetMapping("/me")
    @CrossOrigin()  @ResponseStatus(HttpStatus.CREATED)
    public UChatter checkUserLogged(@AuthenticationPrincipal UserDetails userDetails) {
        System.err.println("-------this is me");
        return userService.checkUserLogged(userDetails);
    }

    @GetMapping("username/{id}")
    @CrossOrigin()  @ResponseStatus(HttpStatus.CREATED)
    public String getUsernameById(@PathVariable long id) {
        return userService. getUsernameById(id);
    }

    @GetMapping("id/{username}")
    @CrossOrigin()  @ResponseStatus(HttpStatus.OK)
    public long getIdByUsername(@PathVariable String username) {
        return userService. getIdByUsername(username);
    }

}
