package com.chatapp.model;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chat {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long peerId;
    private ArrayList<Long> msgIdList;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE } )
    @JoinTable( name = "chatMsg",
            joinColumns = {@JoinColumn(name = "chatId")},
            inverseJoinColumns = {@JoinColumn(name = "msgId")})
    private List<Message> msgList;


    @ManyToOne @JsonIgnore
    @JoinColumn(name = "userOwnerId")
    private UChatter userOwner;

    public void addMessage(Message msg) {

        System.err.println("adding msg to list");
        this.msgIdList.add(msg.getId());
        this.msgList.add(msg);
        msg.getChatList().add(this);
    }

    public void removeMessage(long msgId) {
        System.err.println("removing msg from " + this.getUserOwner().getUsername() + " list");
        Message msg = this.msgList.stream().filter(p -> p.getId() == msgId).findFirst().orElse(null);
        if (msg != null) {
            this.msgList.remove(msg);
            msg.getChatList().remove(this);
        }
    }

//    public void deleteAllMessages() {
//        System.err.println("entre lll");
//        for ( Message msg : this.msgList) {
//            this.msgList.remove(msg);
//            msg.getChatList().remove(this);
//            System.err.println("chaters left in msgObj: " + msg.getChatList().size());
//        }
//        this.setMsgList(new ArrayList<>());
//        this.setMsgIdList(new ArrayList<>());
//    }

}
