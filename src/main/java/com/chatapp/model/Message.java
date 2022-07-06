package com.chatapp.model;

import com.chatapp.enums.MsgStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Message {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long senderId;
    private long receiverId;
    private String content;
    private String timestamp;
    private MsgStatus msgStatus;
    @JsonIgnore
    @ManyToMany(mappedBy = "msgList")
    private List<Chat> chatList = new ArrayList<>();


}
