package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.MessageBroker;



public interface MessageBrokerRepository extends JpaRepository<MessageBroker, Integer> {

}
