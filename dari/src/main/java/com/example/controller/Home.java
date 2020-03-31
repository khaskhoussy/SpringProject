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
import com.example.service.UserService;

@RestController
public class Home {
	@Autowired
	UserService us;
	
	 static  String connectedUser="";
	@GetMapping("/")
	
	public String home(Authentication authentication)
	{
		connectedUser = authentication.getName();
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = new Date();
		Date date = new Date();		
		int  result =(date.getYear()+1900)-(date2.getYear()+1900);
		int result2=date.getDate()-date2.getDate();
		int rsult3=date.getMonth()-date2.getMonth();
		System.out.println("defference months"+rsult3+"defference years"+result+"defference years"+result2);	
		return ("welcome");
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
