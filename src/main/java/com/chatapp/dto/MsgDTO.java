package com.chatapp.dto;

import lombok.Data;

@Data
public class MsgDTO {

//    private long senderId;
    private long receiverId;
    private String content;
    private String timestamp;

}
