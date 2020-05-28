package com.example.controller;

import javax.faces.bean.RequestScoped;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.example.entity.User;
import com.example.service.UserService;

@Scope(value = "session")
@RequestScoped
@Controller(value = "RegistrationController")
@ELBeanName(value = "RegistrationController")
@Join(path = "/registration", to = "/pages/user/registration.jsf")
public class RegistrationController {

	@Autowired
	UserService userService;

	Logger logger = LoggerFactory.getLogger(Profile.class);

	private User newUser=new User();
	private String newPassword;
	private String repeatdPassword;
	private String passwordResult;

	public String addUser() {
		// System.out.println("repeatdPassword : "+repeatdPassword+"\n
		// newPassword : "+newPassword);
		if (repeatdPassword.equals(newPassword)) {
			newUser.setActive(true);
			newUser.setPhoto("index.png");
			newUser.setRoles("ROLE_USER");
			newUser.setPassword(newPassword);
			logger.info("userName :"+newUser.getUserName()+"\t name : "+newUser.getName()+"\t email : "+newUser.getMailAddress()+"\t newPassword : "+newPassword);
			userService.addUser(newUser);
			passwordResult = "your account has been added";
		} else
			passwordResult = "Please Check your password";

		
		return passwordResult;
	}

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRepeatdPassword() {
		return repeatdPassword;
	}

	public void setRepeatdPassword(String repeatdPassword) {
		this.repeatdPassword = repeatdPassword;
	}

	public String getPasswordResult() {
		return passwordResult;
	}

	public void setPasswordResult(String passwordResult) {
		this.passwordResult = passwordResult;
	}

}
