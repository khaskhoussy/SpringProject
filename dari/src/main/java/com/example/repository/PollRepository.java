package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Poll;
import com.example.entity.User;



public interface PollRepository extends JpaRepository<Poll, Long> {

	
    List<Poll> findAllByUser(User user);

    public List<Poll> findAllByUserAndVisible(User user, boolean b);
}
