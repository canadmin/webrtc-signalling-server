package com.dualchat.dualchat.service.social;

import com.dualchat.dualchat.domain.User;
import com.dualchat.dualchat.dtos.UserDto;

import java.util.List;

public interface SocialService {

    List<UserDto> findUser(String username);

    void sendFriendRequest(String senderId,String receiverId);

    List<UserDto> showFriendRequest(String userId);

    UserDto acceptFriendRequest(String myId,String senderId,int reqId);




}
