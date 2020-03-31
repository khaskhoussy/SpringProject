package com.example.controller;
import com.example.controller.Home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.RelationService;
import com.example.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService us;
	@Autowired
	RelationService relationService;
	
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
	
}
