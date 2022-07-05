package com.chatapp.model;

import com.chatapp.enums.MsgStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.JOINED)
public class Message {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long senderId;
    private long receiverId;
    private String content;
    private String timestamp;
    private MsgStatus msgStatus;




}
