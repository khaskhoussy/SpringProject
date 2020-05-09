package com.example.controller;


import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;

import javax.servlet.http.Part;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Controller;

import com.example.entity.User;
import com.example.service.FileStorageService;
import com.example.service.UserService;


@Scope(value = "session")
@Controller(value ="ProfileController")
@ELBeanName(value = "ProfileController")
@Join(path = "/user/profile", to = "/pages/user/Profile.jsf")
public class Profile {
	
	 Logger logger = LoggerFactory.getLogger(Profile.class);
	
	 @Autowired
	 private UserService userService;
	 @Autowired
	 FileStorageService fileStorageService;
	 
	 private User connectedUser ;
	 private String newPassword;
	 private String currentPassword;
	 private String repeatdPassword;
	 private String passwordResult;
	 private Part   myImage;
	 private String imageUrl;
	 

	
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
	
	 public void addImage() throws IOException {	
	
		 myImage.write("C:\\Users\\aisce\\SpringProject\\dari\\src\\main\\webapp\\resources\\userImages\\"+myImage.getSubmittedFileName());		 		 
		File oldFile=new File("C:\\Users\\aisce\\SpringProject\\dari\\src\\main\\webapp\\resources\\userImages\\"+myImage.getSubmittedFileName());
		String AddedName=userService.getAlphaNumericString(7)+myImage.getSubmittedFileName();
		File newfile =new File("C:\\Users\\aisce\\SpringProject\\dari\\src\\main\\webapp\\resources\\userImages\\"+AddedName);
		oldFile.renameTo(newfile);		 
		 User userWithImage= new User();
		 userWithImage  = connectedUser;
		 userWithImage.setPhoto(AddedName);
		 userService.addUser(userWithImage);
		 //System.out.println(newfile.toURL().toString());
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

	public Part   getMyImage() {
		return myImage;
	}

	public void setMyImage(Part   myImage) {
		this.myImage = myImage;
	}

	
	
	
	

}
