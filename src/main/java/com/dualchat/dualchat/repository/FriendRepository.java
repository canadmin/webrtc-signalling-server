package com.dualchat.dualchat.repository;

import com.dualchat.dualchat.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend,Integer> {
}
