package com.example.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Scope(value = "session")
@Controller(value ="UserController")
@ELBeanName(value = "UserController")
@Join(path = "/user/test", to = "/pages/user/test.jsf")
public class testPage {
	public void testUser()
	{
		System.out.println("active");
	}

}
