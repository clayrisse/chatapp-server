package com.chatapp.dto;

import com.chatapp.model.Contact;
import com.chatapp.model.Talk;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UChatterDTO {
    private String username;
    private String password;

    private String profileName;
    private String profileImg;

    public UChatterDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
