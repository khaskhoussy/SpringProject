package com.example.controller;
import com.example.controller.Home;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Demande;
import com.example.entity.Message;
import com.example.entity.MessageBroker;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.MessageService;
import com.example.service.RelationService;
import com.example.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService us;
	@Autowired
	RelationService relationService;
	@Autowired
	MessageService messageService;
	
	@RequestMapping(value = "/profile")
	@ResponseBody
    public User  currentUserName() {         
         return us.findUserByName(Home.connectedUser);                 
    }
	
	@RequestMapping(value="/Myfriends")
	public List<User> myFirends()
	{
		return relationService.myFriends(Home.connectedUser);
	}
	
	@RequestMapping(method = RequestMethod.PUT,value="/profile")
	public void editAccount(User user)
	{
		us.modifyUser(Home.connectedUser, user);
	}
	@RequestMapping(method = RequestMethod.POST,value ="/send")
	public void sendMessage(@RequestBody MessageBroker message)
	{
		message.setSendDate(new Date());
		message.setStatus(false);
		messageService.sendMessage(message);
	}
	
	/*@Scheduled(initialDelay=1000L,fixedDelayString= "PT10S")
	public MessageBroker checkForMessages()
	{
		messageService.sendedMessages().stream().filter(s->s.isStatus() == false).forEach(sended->
		{
			messageService.sendMessageToSpecificUser(sended);
			sended.setStatus(true);
			messageService.sendMessage(sended);
			
		});
		
	MessageBroker recived =messageService.sendedMessages().stream().filter(s->s.isStatus() == false)
		.filter(msg->msg.getUserReciver().equals(Home.connectedUser)).findFirst().get();
	if(recived !=null)
		return recived;
	else
		return null;
	//need to check if there is a method in the front that check fo responses evrey 5seconds for example ; 
	}
	@RequestMapping(value="/messages/{username}")
	public List<Message> messagesWithOther(@PathVariable String username)
	{	System.out.println("qsdqsdqsd"+Home.connectedUser);
		return messageService.ourMessages(Home.connectedUser, username);
	}
	*/
}
