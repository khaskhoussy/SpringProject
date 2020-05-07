package com.example.controller;


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
@Controller(value ="ProfileController")
@ELBeanName(value = "ProfileController")
@Join(path = "/user/profile", to = "/pages/user/Profile.jsf")
public class Profile {
	
	 Logger logger = LoggerFactory.getLogger(Profile.class);
	
	 @Autowired
	 private UserService userService;
	 
	 private User connectedUser ;
	 private String newPassword;
	 private String currentPassword;
	 private String repeatdPassword;
	 private String passwordResult;
	 

	
	 public void  testwhoIsConnected()
	 {
		 connectedUser = HomeController.connectedUser; 
	 }

	 public void modify()
	 {
		 
		 logger.info("UserName :"+connectedUser.getUserName()+"\t First name : "+connectedUser.getName()+"\t LastName :"+connectedUser.getLastName()+"\t CIN : "+connectedUser.getNic() + "\t Phone Number :"+connectedUser.getPhoneNumber());
		 userService.modifyUser(connectedUser.getUserName(),connectedUser);
	 }
	 
	 public String passwordUpdate()
	 {
		 passwordResult ="Password Updated";
		if(!connectedUser.getPassword().equals(currentPassword))
			{passwordResult= "your Current Password is False please check again";
			 return passwordResult;
			}
		if(!newPassword.equals(repeatdPassword))
			{passwordResult = "your Repeatd Password is Flase please check again";
			return 	passwordResult;
			}
			connectedUser.setPassword(repeatdPassword);
			 userService.modifyUser(connectedUser.getUserName(),connectedUser);
			 currentPassword="changed";
			 return passwordResult;

		
	 }
	

	public User getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(User connectedUser) {
		this.connectedUser = connectedUser;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
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
