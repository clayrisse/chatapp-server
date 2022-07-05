package com.chatapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

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
