package com.dualchat.dualchat.service;

import com.dualchat.dualchat.domain.Room;
import com.dualchat.dualchat.dtos.RoomDto;

public interface RoomService {


    Room createRoom(RoomDto room);

}
