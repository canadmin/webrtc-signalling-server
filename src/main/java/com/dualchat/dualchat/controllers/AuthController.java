package com.dualchat.dualchat.controllers;

import com.dualchat.dualchat.auth.TokenManager;
import com.dualchat.dualchat.domain.User;
import com.dualchat.dualchat.dtos.UserDto;
import com.dualchat.dualchat.request.LoginRequest;
import com.dualchat.dualchat.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private AuthService authService;
    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto) throws Exception {
        return ResponseEntity.ok(authService.authenticate(userDto));
    }

//    @PostMapping(path = "/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
//        try{
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
//        return ResponseEntity.ok(tokenManager.generateToken(loginRequest.getUsername()));
//        }catch (Exception e){
//            throw e;
//        }
//    }

    @PostMapping(path = "/signup")
    public ResponseEntity<User> signUp(@RequestBody UserDto user){
        return new ResponseEntity<User>(authService.signUp(user), HttpStatus.OK);
    }
}
