package com.example.controller;

import javax.faces.bean.RequestScoped;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
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

	private String userName;
	private String email;
	private int cin;
	private int phoneNumber;
	private String name;
	private String lastName;
	private String newPassword;
	private String repeatdPassword;
	private String passwordResult;

	public String addUser() {
		// System.out.println("repeatdPassword : "+repeatdPassword+"\n
		// newPassword : "+newPassword);
		if (repeatdPassword.equals(newPassword)) 
		{
			
		userService.addUser(new User( userName,email,name,lastName, phoneNumber,cin,newPassword,"ROLE_USER " ,true));
		passwordResult = "your account has been added";
		} else
			passwordResult = "Please Check your password";

		System.out.println("azezaeazeazea" + passwordResult);
		return passwordResult;
	}
	
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCin() {
		return cin;
	}

	public void setCin(int cin) {
		this.cin = cin;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
