package com.chatapp.service;

import com.chatapp.model.Contact;
import com.chatapp.model.UChatter;
import com.chatapp.repository.ContactRepository;
import com.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ContactService {

    @Autowired    UserRepository userRepository;
    @Autowired    UserService userService;
    @Autowired    ContactRepository contactRepository;

    public Contact createContact(Contact contact, UserDetails userDetails) {
        UChatter currentChatter = userService.findChatterByUsername(userDetails.getUsername());
        Contact newContact  =  Contact.builder().customName(contact.getCustomName())
                                                .username(contact.getUsername())
                                                .contactOwner(currentChatter).build();
        return contactRepository.save(newContact);
    }


    public Contact getContactById(long id, UserDetails userDetails) {
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contact with id '" + id + "' not found");
        }
        if (contact.get().getContactOwner().getUsername().equals(userDetails.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This is not your contact");
        }
        return contact.get();
    }


    public Contact getContactByUsername(String username, UserDetails userDetails) {
        UChatter currentChatter = userService.findChatterByUsername(userDetails.getUsername());
        Contact contact = currentChatter.getContactList().stream().filter(c -> c.getUsername().equals(username)).findFirst().orElse(null);
         if (contact == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contact not found");
        }
        return contactRepository.findById(contact.getId()).get();
    }


    public Contact getContactCustomName(String customName, UserDetails userDetails) {
        UChatter currentChatter = userService.findChatterByUsername(userDetails.getUsername());
        Contact contact = currentChatter.getContactList().stream().filter(c -> c.getCustomName().equals(customName)).findFirst().orElse(null);
        if (contact == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contact not found");
        }
        return contactRepository.findById(contact.getId()).get();
    }


    public void deleteContact(long id, UserDetails userDetails) {
        if (contactRepository.findById(id).get().getContactOwner().getUsername().equals(userDetails.getUsername())) {
            contactRepository.deleteById(id);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This contact is not yours to delete");
    }


}
