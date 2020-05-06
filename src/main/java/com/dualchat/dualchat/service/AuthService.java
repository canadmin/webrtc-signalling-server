package com.dualchat.dualchat.service;

import com.dualchat.dualchat.domain.User;
import com.dualchat.dualchat.dtos.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AuthService {

   User signUp(UserDto user);

   Map authenticate(UserDto userDto) throws Exception;
}
