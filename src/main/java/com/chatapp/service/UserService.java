package com.chatapp.service;

import com.chatapp.dto.UChatterDTO;
import com.chatapp.model.UChatter;
import com.chatapp.repository.UAdminRepository;
import com.chatapp.repository.UChatterRepository;
import com.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

//    @Autowired    UserRepository userRepository;
    @Autowired    UChatterRepository uChatterRepository;
//    @Autowired    UAdminRepository uAdminRepository;
//    @Autowired    UChatterService uChatterService;


    public boolean checkIfUsernameIsTaken(String username){
        if (uChatterRepository.findByUsername(username).isPresent()) {
            System.err.println("This username '" + username + "' is taken");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This username: '" + username + "', is already in use");
        }
        return true;
    }

    public UChatter findChatterByUsername(String username){
        if (uChatterRepository.findByUsername(username).isEmpty()) {
            System.err.println("Chatter with " + username + " not found");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Chatter with id " + username + " not found");
        }
        return uChatterRepository.findByUsername(username).get();
    }

    public UChatter findChatterById(long id){
        if (uChatterRepository.findById(id).isEmpty()) {
            System.err.println("Chatter with " + id + " not found");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Chatter with id '" + id + "' not found");
        }
        return uChatterRepository.findById(id).get();
    }

    public boolean checkRequestIdUsernameMatchesLoggedUser(long id, UserDetails userDetails) {
        if (!(findChatterById(id).getUsername().equals(userDetails.getUsername()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "What are you doing? You are not the owner of this account");
        }
        return true;
    }

//------------------------------------------------------------------------------------for controlers

    public UChatter addChatter(UChatterDTO chatterDTO) {
        checkIfUsernameIsTaken(chatterDTO.getUsername()); System.err.println("------addChatter");
        return uChatterRepository.save(new UChatter(chatterDTO.getUsername(), chatterDTO.getPassword()));
    }

    public UChatter getChatterById(long id, UserDetails userDetails) {
        return findChatterById(id);
    }

    public UChatter getChatterByUsername(String username, UserDetails userDetails) {
        return findChatterByUsername(username);
    }

    public UChatter updateChatter(long id, UserDetails userDetails, UChatterDTO chatterDTO) {
        checkRequestIdUsernameMatchesLoggedUser(id, userDetails);
        if (userDetails.getUsername().equals(chatterDTO.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'" + chatterDTO.getUsername() + "' is already your username");
        }
        UChatter chatter = findChatterById(id);
        if (chatterDTO.getUsername()!= null && chatterDTO.getUsername()!= "") {
            checkIfUsernameIsTaken(chatterDTO.getUsername());
            chatter.setUsername(chatterDTO.getUsername());
        }
        if (chatterDTO.getPassword()!= null && chatterDTO.getPassword()!= "") chatter.setPassword(chatterDTO.getPassword());
        if (chatterDTO.getProfileImg() != null && chatterDTO.getProfileImg() != "") chatter.setProfileImg(chatterDTO.getProfileImg());
        if (chatterDTO.getProfileName() != null) chatter.setProfileName(chatterDTO.getProfileName());

        return uChatterRepository.save(chatter);
    }

    public String deleteChatter(long id, UserDetails userDetails) {
        checkRequestIdUsernameMatchesLoggedUser(id, userDetails);
        uChatterRepository.deleteById(id);
        return "User account deleted";
    }

//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ checked, cleaned and tested code

    public UChatter getLoggedUser(UserDetails userDetails) {
        return findChatterByUsername(userDetails.getUsername());
    }

    public UChatter checkUserLogged(UserDetails userDetails) {
        return  findChatterByUsername(userDetails.getUsername());
    }

    public String getUsernameById(long id) {
        System.err.println(findChatterById(id).getUsername());

        return findChatterById(id).getUsername();
    }

    public long getIdByUsername(String username) {
        return findChatterByUsername(username).getId();
    }


}
