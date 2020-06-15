package com.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.entity.MessageBroker;
import com.example.entity.User;
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
	@Autowired
	UserService userService;
	
	public List<Message> AllMessages()
	{
		List<Message> listOfMessages = new ArrayList<>();
		messageRepository.findAll().forEach(listOfMessages::add);
		return listOfMessages;
	}
	
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
		public List<Message> ourMessages(String myUsername,String username)
		{
			
		List<Message> myMessages =	AllMessages().stream().filter(message-> 
				((message.getSender().equals(myUsername) && message.getUserReciver().getUserName().equals(username))
														||
				( message.getUserReciver().getUserName().equals(myUsername) && message.getSender().equals(username))))
				.collect(Collectors.toList());
		System.out.println("azezaeaze"+myMessages);
		
		return myMessages;
					
		}
		public void sendMessageJsf(String sender ,String reciver,String message)
		{
			User userReciver =userService.findUserByName(reciver);
			Message newMessage = new Message(new Date(), message,sender,userReciver);
			newMessage.setNotification(false);
			messageRepository.save(newMessage);
			
		}
		
	

}
