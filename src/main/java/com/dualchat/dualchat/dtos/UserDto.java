package com.dualchat.dualchat.dtos;

import com.dualchat.dualchat.domain.Friend;
import com.dualchat.dualchat.domain.Request;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private String userId;

    private String username;
    private List<Friend> friends;
    private List<Request> requests;


}
