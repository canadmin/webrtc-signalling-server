package com.dualchat.dualchat.service;

import com.dualchat.dualchat.domain.User;
import com.dualchat.dualchat.dtos.AuthDto;

import java.util.Map;

public interface AuthService {

   User signUp(AuthDto user);

   Map authenticate(AuthDto authDto) throws Exception;
}
