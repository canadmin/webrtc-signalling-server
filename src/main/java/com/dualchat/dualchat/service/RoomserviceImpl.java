package com.dualchat.dualchat.service;

import com.dualchat.dualchat.domain.Room;
import com.dualchat.dualchat.dtos.RoomDto;
import com.dualchat.dualchat.repository.RoomRepository;
import com.dualchat.dualchat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoomserviceImpl implements RoomService{

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Room createRoom(RoomDto room) {
        return roomRepository.save(Room.builder().user(userRepository.getOne(room.getOwnerId())).roomName(room.getRoomName()).owner(room.getOwner()).build());
    }
}
