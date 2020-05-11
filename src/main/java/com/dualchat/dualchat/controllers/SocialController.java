package com.dualchat.dualchat.controllers;

import com.dualchat.dualchat.dtos.UserDto;
import com.dualchat.dualchat.service.social.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
public class SocialController {

    @Autowired
    private SocialService socialService;


    @CrossOrigin
    @GetMapping(path = "/getuser")
    public ResponseEntity<List<UserDto>> showUsers(@RequestParam(value = "username", required = true) String username){
        return new ResponseEntity<>(socialService.findUser(username), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(path = "/sendRequest")
    public ResponseEntity<String> sendRequest(@RequestParam(value = "senderId", required = true) String senderId,
                                      @RequestParam(value = "receiverId", required = true) String receiverId){

        socialService.sendFriendRequest(senderId,receiverId);
        return  ResponseEntity.ok("istek gonderildi");
    }

    @CrossOrigin
    @PostMapping(path = "/acceptRequest")
    public ResponseEntity<UserDto> acceptRequest(@RequestParam(value = "myId", required = true) String myId,
                                                 @RequestParam(value = "senderId", required = true) String senderId){
        return new ResponseEntity<>(socialService.acceptFriendRequest(myId,senderId),HttpStatus.OK);
    }
}
