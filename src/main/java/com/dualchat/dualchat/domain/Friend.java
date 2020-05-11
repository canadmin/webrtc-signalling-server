package com.dualchat.dualchat.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int friendId;

    private String username;

    @OneToOne
    @JoinColumn(name = "friend_user_id")
    @JsonBackReference
    private User user;

}
