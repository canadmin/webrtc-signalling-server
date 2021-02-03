package com.dualchat.dualchat.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

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
    private String senderUserName;
    private String uniqueKey;
    @OneToOne
    @JoinColumn(name = "request_user_id")
    @JsonBackReference
    private User user;

}
