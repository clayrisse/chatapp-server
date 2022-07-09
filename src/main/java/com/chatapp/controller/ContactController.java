package com.chatapp.controller;

import com.chatapp.model.Chat;
import com.chatapp.model.Contact;
import com.chatapp.repository.ContactRepository;
import com.chatapp.service.ContactService;
import com.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("chatter/contact")
public class ContactController {


    @Autowired    UserService userService;
    @Autowired    ContactRepository contactRepository;
    @Autowired    ContactService contactService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Contact createContact(@RequestBody Contact contact, @AuthenticationPrincipal UserDetails userDetails) {
        return contactService.createContact(contact, userDetails);
    }

//    @GetMapping("/id/{id}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Contact getContactById(@PathVariable long id, @AuthenticationPrincipal UserDetails userDetails) {
//        return contactService.getContactById (id, userDetails);
//    }
//
//    @GetMapping("/username/{username}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Contact getContactByUsername(@PathVariable String username, @AuthenticationPrincipal UserDetails userDetails) {
//        return contactService.getContactByUsername (username, userDetails);
//    }
//
//    @GetMapping("/name/{customName}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Contact getContactCustomName(@PathVariable String customName, @AuthenticationPrincipal UserDetails userDetails) {
//        return contactService.getContactCustomName (customName, userDetails);
//    }


}
