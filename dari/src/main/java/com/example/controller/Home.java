package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {
	
	@GetMapping("/")
	
	public String home()
	{
	return ("welcome");
	}
	
	@GetMapping("/user")
	
	public String homeUser()
	{
	return ("welcome user");
	}
	
	@GetMapping("/admin")
	
	public String homeAdmin()
	{
	return ("welcome Admin");
	}
	

}
