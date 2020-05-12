package com.dualchat.dualchat.controllers;

import com.dualchat.dualchat.dtos.UserDto;
import com.dualchat.dualchat.service.AuthService;
import com.dualchat.dualchat.service.social.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class SocialController {

    @Autowired
    private SocialService socialService;
    @Autowired
    private AuthService authService;


    @CrossOrigin
    @GetMapping("/getuser")
    public ResponseEntity<List<UserDto>> showUsers(@RequestParam(value = "username", required = true) String username){
        return new ResponseEntity<>(socialService.findUser(username), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/sendRequest")
    public ResponseEntity<String> sendRequest(@RequestParam(value = "senderId", required = true) String senderId,
                                      @RequestParam(value = "receiverId", required = true) String receiverId){

        socialService.sendFriendRequest(senderId,receiverId);
        return  ResponseEntity.ok("istek gonderildi");
    }

    @CrossOrigin
    @PostMapping("/acceptRequest")
    public ResponseEntity<UserDto> acceptRequest(@RequestParam(value = "myId", required = true) String myId,
                                                 @RequestParam(value = "senderId", required = true) String senderId){
        return new ResponseEntity<>(socialService.acceptFriendRequest(myId,senderId),HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/getUserInfo")
    public ResponseEntity<UserDto> getUserInfo(@RequestParam(value = "userId", required = true) String userId){
        return new ResponseEntity<>(authService.getUserInfo(userId),HttpStatus.OK);
    }
}
