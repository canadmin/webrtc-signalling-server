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

    private String fullName;

    @JsonIgnore
    private String password;

    @OneToMany(cascade = {
            CascadeType.ALL
    },orphanRemoval = true)

    @JoinColumn(name = "friends_user_id")
    @JsonIgnore
    private List<Friend> friends = new ArrayList<>();

    @JoinColumn(name = "request_user_id")
    @OneToMany(cascade = {
            CascadeType.ALL
    })
    @JsonIgnore
    private List<Request> requests = new ArrayList<>();
}
