package com.chatapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String username;
    private String password;
//    private String profileName;
//    private String profileImg;
//    private LocalDateTime lastSeen;
//
//    private ArrayList<Contact> contactList = new ArrayList<>();
//    private ArrayList<Talk> talkList = new ArrayList<>();
//
//    public User(String profileName, String username) {
//        this.profileName = profileName;
//        this.username = username;
//    }
//
//    public void setName(String profileName) {
//        this.profileName = profileName.toUpperCase();
//    }
//
////    public Contact addContact(Contact contact) {
////        this.contactList.add(contact);
////        return contact;
////    }
//
//
//    //-------------------regarding security
//    public Set<Role> getRoles() { return roles; }
//    public void setRoles(Set<Role> roles) { this.roles = roles; }
}
