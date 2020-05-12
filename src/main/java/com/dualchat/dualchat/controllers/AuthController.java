package com.dualchat.dualchat.controllers;

import com.dualchat.dualchat.dtos.UserDto;
import com.dualchat.dualchat.security.TokenManager;
import com.dualchat.dualchat.domain.User;
import com.dualchat.dualchat.dtos.AuthDto;
import com.dualchat.dualchat.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;


@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private AuthService authService;

    @CrossOrigin
    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody AuthDto authDto) throws Exception {
        return ResponseEntity.ok(authService.authenticate(authDto));
    }

    @CrossOrigin
    @PostMapping(path = "/signup")
    public ResponseEntity<User> signUp(@RequestBody AuthDto user) {
        return new ResponseEntity<User>(authService.signUp(user), HttpStatus.OK);
    }

}
