package com.dualchat.dualchat.service;

import com.dualchat.dualchat.dtos.UserDto;
import com.dualchat.dualchat.security.TokenManager;
import com.dualchat.dualchat.security.UserDetailsService;
import com.dualchat.dualchat.domain.User;
import com.dualchat.dualchat.dtos.AuthDto;
import com.dualchat.dualchat.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User signUp(AuthDto user) {
        User nUser = new User();
        nUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        nUser.setUsername(user.getUsername());
        return userRepository.save(nUser);
    }

    @Override
    public Map authenticate(AuthDto authDto) throws Exception {
        authenticate(authDto.getUsername(), authDto.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authDto.getUsername());
        User user = user = userRepository.findByUsername(authDto.getUsername());
        Map<String,Object> authenticateMap = new HashMap<>();
        authenticateMap.put("Token",tokenManager.generateToken(authDto.getUsername()));
        authenticateMap.put("User",modelMapper.map(user, UserDto.class));
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
