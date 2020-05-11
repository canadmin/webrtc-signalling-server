package com.dualchat.dualchat.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String userId;

    private String username;

    @JsonIgnore
    private String password;

    @OneToMany(cascade = {
            CascadeType.ALL
    },orphanRemoval = true)

    @JoinColumn(name = "friends_user_id")
    @JsonManagedReference
    private List<Friend> friends = new ArrayList<>();

    @OneToMany(cascade = {
            CascadeType.ALL
    })
    @JsonManagedReference
    private List<Request> requests = new ArrayList<>();
}
