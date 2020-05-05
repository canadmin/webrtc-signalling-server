package com.dualchat.dualchat.service;

import com.dualchat.dualchat.domain.User;
import com.dualchat.dualchat.dtos.UserDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {

   User signUp(UserDto user);

   String authenticate(UserDto userDto) throws Exception;
}
