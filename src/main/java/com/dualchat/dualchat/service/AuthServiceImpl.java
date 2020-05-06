package com.dualchat.dualchat.service;

import com.dualchat.dualchat.security.TokenManager;
import com.dualchat.dualchat.security.UserDetailsService;
import com.dualchat.dualchat.domain.User;
import com.dualchat.dualchat.dtos.UserDto;
import com.dualchat.dualchat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private TokenManager tokenManager;


    @Override
    public User signUp(UserDto user) {
        User nUser = new User();
        nUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        nUser.setUsername(user.getUsername());
        return userRepository.save(nUser);
    }

    @Override
    public Map authenticate(UserDto userDto) throws Exception {
        authenticate(userDto.getUsername(), userDto.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
        User user = user = userRepository.findByUsername(userDto.getUsername());
        Map<String,Object> authenticateMap = new HashMap<>();
        authenticateMap.put("Token",tokenManager.generateToken(userDto.getUsername()));
        authenticateMap.put("User",user);
        return authenticateMap;
    }
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
