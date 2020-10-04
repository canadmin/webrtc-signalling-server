package com.dualchat.dualchat.controllers;

import com.dualchat.dualchat.domain.Room;
import com.dualchat.dualchat.dtos.AuthDto;
import com.dualchat.dualchat.dtos.RoomDto;
import com.dualchat.dualchat.dtos.UserDto;
import com.dualchat.dualchat.service.AuthService;
import com.dualchat.dualchat.service.RoomService;
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
    @Autowired
    private RoomService roomService;


    @CrossOrigin
    @GetMapping("/getuser")
    public ResponseEntity<List<UserDto>> showUsers(@RequestParam(value = "username", required = true) String username) {
        return new ResponseEntity<>(socialService.findUser(username), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/sendRequest")
    public ResponseEntity<String> sendRequest(@RequestParam(value = "senderId", required = true) String senderId,
                                              @RequestParam(value = "receiverId", required = true) String receiverId) {

        socialService.sendFriendRequest(senderId, receiverId);
        return ResponseEntity.ok("istek gonderildi");
    }

    @CrossOrigin
    @PostMapping("/acceptRequest")
    public ResponseEntity<UserDto> acceptRequest(@RequestParam(value = "myId", required = true) String myId,
                                                 @RequestParam(value = "senderId", required = true) String senderId,
                                                 @RequestParam(value = "reqId", required = true) int reqId) {
        return new ResponseEntity<>(socialService.acceptFriendRequest(myId, senderId,reqId), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/getUserInfo")
    public ResponseEntity<UserDto> getUserInfo(@RequestParam(value = "userId", required = true) String userId) {
        return new ResponseEntity<>(authService.getUserInfo(userId), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/createRoom")
    public ResponseEntity<Room> createRoom(@RequestBody RoomDto room){
        return new ResponseEntity<>(roomService.createRoom(room),HttpStatus.OK);
    }
}
