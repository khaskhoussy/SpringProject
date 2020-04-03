package com.example.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;

@RestController
public class Home {
	@Autowired
	UserService us;
	@Autowired 
	UserRepository userRepository ;
	
	static User userConnected;
	
	 static  String connectedUser;
	@GetMapping("/")
	
	public String connectedUsername(Authentication authentication)
	{
		connectedUser = authentication.getName();
		userConnected = userRepository.findByUserName(connectedUser).get();
		return connectedUser ;
	}	
	
	@GetMapping("/admin")
	
	public String homeAdmin()
	{
	return ("welcome Admin");
	}
	
	@RequestMapping(method = RequestMethod.POST,value="/registration")
	
	public void registration(@RequestBody User user)
	{
				us.addUser(user);
	}
	
	

}
