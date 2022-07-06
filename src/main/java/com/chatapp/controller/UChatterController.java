package com.chatapp.controller;

import com.chatapp.dto.UChatterDTO;
import com.chatapp.model.UChatter;
import com.chatapp.repository.UChatterRepository;
import com.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("chatter/")
public class UChatterController {

    @Autowired UserService userService;

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
    @CrossOrigin()  @ResponseStatus(HttpStatus.OK)
    public UChatter updateChatter(@PathVariable long id, @AuthenticationPrincipal UserDetails userDetails, @RequestBody UChatterDTO chatterDTO) {
        System.err.println("------1");
        return userService.updateChatter(id, userDetails, chatterDTO);
    }

    @DeleteMapping("{id}/delete")
    @CrossOrigin()  @ResponseStatus(HttpStatus.OK)
    public String deleteChatter(@PathVariable long id, @AuthenticationPrincipal UserDetails userDetails) {
        System.err.println("------1");
        return userService.deleteChatter(id, userDetails);
    }

    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ checked, cleaned and tested code

}
