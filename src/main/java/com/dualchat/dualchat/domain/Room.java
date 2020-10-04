package com.dualchat.dualchat.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roomId;

    private String roomName;

    private String owner;

    @OneToOne
    @JoinColumn(name = "room_user_id")
    @JsonBackReference
    private User user;

    private int maximumMember;
}
