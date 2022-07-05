package com.chatapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity//(name = "admins")
@Data
@NoArgsConstructor
//@PrimaryKeyJoinColumn(name = "userId")
public class UAdmin extends User{

    @Builder
    public UAdmin(long id, String username, String password) {
        super(id, username, password, new HashSet<Role>());
    }

}


