package com.dualchat.dualchat.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int requestId;

    private String senderId;

    @OneToOne
    @JoinColumn(name = "request_receiver_id")
    @JsonBackReference
    private User user;

}
