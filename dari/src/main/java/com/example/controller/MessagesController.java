package com.example.controller;
import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.example.entity.Message;
import com.example.entity.MessageBroker;
import com.example.entity.User;
import com.example.service.MessageService;
import com.example.service.RelationService;
import com.example.service.UserService;

@Scope(value = "session")
@Controller(value = "MessagesController")
@ELBeanName(value = "MessagesController")
@Join(path = "/user/messages", to = "/pages/user/messages.jsf")

public class MessagesController {
	@Autowired
	private UserService userService;
	@Autowired
	private RelationService relationService;
	@Autowired
	private MessageService messageService;

	Logger logger = LoggerFactory.getLogger(Profile.class);

	private List<User> myFriends;
	private List<Message> messages;
	private String selectedPerson;
	private String message;

	public void init() {
		myFriends = relationService.myFriends(HomeController.connectedUser.getUserName());

	}

	public void desplayMessages(String userName) {

		
		selectedPerson = userName;
		messages = messageService.ourMessages(HomeController.connectedUser.getUserName(), userName);

	}

	public void sendTheMessage() {
		
		
		messageService.sendMessageJsf(HomeController.connectedUser.getUserName(),selectedPerson, message);

	}

	public List<User> getMyFriends() {
		return myFriends;
	}

	public void setMyFriends(List<User> myFriends) {
		this.myFriends = myFriends;
	}

	public String getSelectedPerson() {
		return selectedPerson;
	}

	public void setSelectedPerson(String selectedPerson) {
		this.selectedPerson = selectedPerson;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
