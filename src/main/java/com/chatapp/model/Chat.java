package com.chatapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chat {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long peerId;
    private ArrayList<Message> messageList = new ArrayList<>();
    @ManyToOne //@JsonIgnore
    @JoinColumn(name = "userOwnerId")
    private UChatter userOwner;

}
