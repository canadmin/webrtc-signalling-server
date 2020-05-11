package com.dualchat.dualchat.repository;

import com.dualchat.dualchat.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request,Integer> {

}
