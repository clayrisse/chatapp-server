package com.chatapp.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;

@Entity
@Data
@NoArgsConstructor
public class UAdmin extends User{

    @Builder
    public UAdmin(long id, String username, String password) {
        super(id, username, password, new HashSet<Role>());
    }

}


