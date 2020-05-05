package com.dualchat.dualchat.repository;

import com.dualchat.dualchat.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
