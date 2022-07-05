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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This username is already in use");
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Chatter with " + id + " not found");
        }
        return uChatterRepository.findById(id).get();
    }

//------------------------------------------------------------------------------------for controlers

    public UChatter addChatter(UChatterDTO chatterDTO) {
        checkIfUsernameIsTaken(chatterDTO.getUsername()); System.err.println("------addChatter");
        return uChatterRepository.save(new UChatter(chatterDTO.getUsername(), chatterDTO.getPassword()));
    }

    public UChatter getChatterById(long id, UserDetails userDetails) {
        System.err.println("------by id");
        return findChatterById(id);
    }

    public UChatter getChatterByUsername(String username, UserDetails userDetails) {
        System.err.println("------by username");
        return findChatterByUsername(username);
    }


//================================================================================== check line




    public UChatter updateChatter(long id, UChatterDTO chatterDTO) {
        System.err.println("------updateChatter1");
        UChatter chatter = findChatterById(id);
        System.err.println("/////0" );
        if (chatterDTO.getUsername()!= null && chatterDTO.getUsername()!= "") {
        System.err.println("/////1");
            checkIfUsernameIsTaken(chatterDTO.getUsername());
        }
        System.err.println("/////2");
        if (chatterDTO.getPassword()!= null && chatterDTO.getPassword()!= "") {
            chatter.setPassword(chatterDTO.getPassword());
        }
        System.err.println("/////3");
        if (chatterDTO.getProfileImg() != null && chatterDTO.getProfileImg() != "") chatter.setProfileImg(chatterDTO.getProfileImg());
        System.err.println("/////4");
        if (chatterDTO.getProfileName() != null) chatter.setProfileName(chatterDTO.getProfileName());
        System.err.println("/////5");
        System.err.println("------updateChatter2");
        return uChatterRepository.save(chatter);

    }




}
