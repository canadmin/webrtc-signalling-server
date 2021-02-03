package com.dualchat.dualchat.service.social;

import com.dualchat.dualchat.domain.Friend;
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
        Request request = requestRepository.findByUniqueKey(senderId + receiverId);
        if(requestRepository.findByUniqueKey(senderId + receiverId) == null
        && requestRepository.findByUniqueKey(receiverId + senderId) == null){
            Optional<User> receiverUserOpt = userRepository.findById(receiverId);
            User senderUser = userRepository.getOne(senderId);
            List<Request> requests= receiverUserOpt.get().getRequests();
            requests.add(Request.builder().user(receiverUserOpt.get()).senderUserName(senderUser.getUsername()).senderId(senderId)
                    .uniqueKey(senderId+receiverId).build());
            receiverUserOpt.get().setRequests(requests);
            userRepository.save(receiverUserOpt.get());
        }
    }

    @Override
    public List<UserDto> showFriendRequest(String userId) {
        return null;
    }

    @Override
    public UserDto acceptFriendRequest(String myId, String senderId,int reqId) {
        Optional<User> answeringUser = userRepository.findById(myId);
        Optional<User> waitingUser = userRepository.findById(senderId);

        List<Friend> answeringFriends = answeringUser.get().getFriends();
        List<Friend> waitingUserFriends = waitingUser.get().getFriends();

        answeringFriends.add(Friend.builder().username(waitingUser.get().getUsername()).user(answeringUser.get()).build());
        waitingUserFriends.add(Friend.builder().username(answeringUser.get().getUsername()).user(answeringUser.get()).build());

        answeringUser.get().setFriends(answeringFriends);
        waitingUser.get().setFriends(waitingUserFriends);

        userRepository.save(answeringUser.get());
        userRepository.save(waitingUser.get());
        requestRepository.deleteById(reqId);
        return modelMapper.map(answeringUser.get(),UserDto.class);
    }
}
