package com.chatapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity//(name = "chatters")
@Data
@NoArgsConstructor
public class UChatter extends User {

    private String profileName;
    private String profileImg;
//    private LocalDateTime lastSeen;
//    @OneToMany(mappedBy = "user")
    private ArrayList<Contact> contactList;
//    @JsonIgnore
    @OneToMany(mappedBy = "userOwner")
//    private HashMap<String, Chat> chatList;
    private List<Chat> chatList;


    public UChatter(String username, String password) {
        super(username, password);
//        this.lastSeen = LocalDateTime.now();
//        this.chatList = new ArrayList<>();
        this.contactList = new ArrayList<>();
        this.profileImg = "https://www.pngkey.com/png/detail/349-3499617_person-placeholder-person-placeholder.png";
        this.profileName = username;
        super.setRole("CHATTER");
    }


    public void setProfileName(String profileName) {
        this.profileName = profileName.toUpperCase();
    }

    public Contact addContact(Contact contact) {
        this.contactList.add(contact);
        return contact;
    }

    public List<Chat> getChatList() {
        return chatList;
    }

    public void setChatList(List<Chat> chatList) {
        this.chatList = chatList;
    }
}
