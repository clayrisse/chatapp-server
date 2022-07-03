package com.chatapp.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;


@Entity
@Data
@Builder
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "userId")
public class UChatter implements User {


//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private int id;
//    private long id;
//    @Column(unique = true)
//    private String username;
//    private String password;
    private String profileName;
    private String profileImg;
    private LocalDateTime lastSeen;

    private ArrayList<Contact> contactList = new ArrayList<>();
    private ArrayList<Talk> talkList = new ArrayList<>();

    public UChatter(String username, String profileName) {
        super (username);
        this.profileName = profileName;
//        this.username = username;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName.toUpperCase();
    }

//    public Contact addContact(Contact contact) {
//        this.contactList.add(contact);
//        return contact;
//    }


    //-------------------regarding security
    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }
}



}
