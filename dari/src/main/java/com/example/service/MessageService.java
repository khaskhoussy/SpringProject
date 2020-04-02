package com.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.entity.MessageBroker;
import com.example.repository.MessageBrokerRepository;
import com.example.repository.MessageRepository;
import com.example.repository.UserRepository;

@Service
public class MessageService {

	@Autowired
	MessageRepository messageRepository;
	@Autowired
	MessageBrokerRepository messageBrokerRepository;
	@Autowired
	UserRepository userRepository;
	
	public List<MessageBroker> sendedMessages(){
		List <MessageBroker> listOfSendedMessages = new ArrayList<>();
		 messageBrokerRepository.findAll().forEach(listOfSendedMessages::add);
		return listOfSendedMessages;
	}
	
	public void sendMessage(MessageBroker message)
	{
		
		messageBrokerRepository.save(message);
		
	}
	public void sendMessageToSpecificUser(MessageBroker messageBroker)
	{
		Message message = new Message(new Date(),messageBroker.getMessageContent(),messageBroker.getUserSender(),
				userRepository.findByUserName(messageBroker.getUserReciver()).get());
		messageRepository.save(message);
	}
	

}
