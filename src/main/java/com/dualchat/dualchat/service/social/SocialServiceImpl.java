package com.dualchat.dualchat.service.social;

import com.dualchat.dualchat.domain.Request;
import com.dualchat.dualchat.domain.User;
import com.dualchat.dualchat.dtos.UserDto;
import com.dualchat.dualchat.repository.RequestRepository;
import com.dualchat.dualchat.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SocialServiceImpl implements SocialService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestRepository requestRepository;
    @Override
    public List<UserDto> findUser(String username) {

        List<User> users = userRepository.findAllByUsernameStartingWith(username);
        return Arrays.asList(modelMapper.map(users,UserDto[].class));
    }

    @Override
    public void sendFriendRequest(String senderId,String receiverId) {

        Optional<User> receiverUserOpt = userRepository.findById(receiverId);
        List<Request> requests= receiverUserOpt.get().getRequests();
        requests.add(Request.builder().user(receiverUserOpt.get()).senderId(senderId).build());
        receiverUserOpt.get().setRequests(requests);
        userRepository.save(receiverUserOpt.get());
    }

    @Override
    public List<UserDto> showFriendRequest(String userId) {
        return null;
    }

    @Override
    public void acceptFriendRequest(String myId, String senderId) {


    }
}
