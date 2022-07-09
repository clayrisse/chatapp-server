package com.chatapp.service;

import com.chatapp.model.Contact;
import com.chatapp.model.UChatter;
import com.chatapp.repository.ContactRepository;
import com.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ContactService {

    @Autowired    UserRepository userRepository;
    @Autowired    UserService userService;
    @Autowired    ContactRepository contactRepository;

    public Contact createContact(Contact contact, UserDetails userDetails) {

        UChatter currentChatter = userService.findChatterByUsername(userDetails.getUsername());

//        currentChatter.addContact(Contact.builder().customName(contact.getCustomName())
        Contact newContact = Contact.builder().customName(contact.getCustomName())
                                                    .username(contact.getUsername())
                                                    .contactOwner(currentChatter)
                                                    .build();
//                userRepository.save(currentChatter);
//        return contact;
        return contactRepository.save(newContact);

    }

}
