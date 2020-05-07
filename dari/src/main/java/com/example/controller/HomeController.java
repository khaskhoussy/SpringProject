package com.example.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.example.entity.User;
import com.example.service.UserService;

@Scope(value = "session")
@RequestScoped
@Controller(value = "HomeController")
@ELBeanName(value = "HomeController")
@Join(path = "/user/Home", to = "/pages/user/Home.jsf")
public class HomeController {
	
	@Autowired
	UserService userService;
	Logger logger = LoggerFactory.getLogger(Profile.class);
  public	static User connectedUser;
	
	
	public  void getConnectedUser()
	{		
		connectedUser =  userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());		
	}
	
	
}
