package com.chatapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Talk {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String peerId;
    private ArrayList<Message> messageList = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "userOwnerId")
    private UChatter userOwner;

}
