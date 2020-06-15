package com.example.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.entity.User;

//@Scope(value = "session")
//@Controller(value ="LoginController")
//@ELBeanName(value = "LoginController")
//@Join(path = "/login", to = "/login.jsf")
@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}

}
