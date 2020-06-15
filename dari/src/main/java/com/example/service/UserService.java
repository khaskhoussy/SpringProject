package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.entity.User;
import com.example.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public  User  findUserByName(String userName)
	{
		User a = new User();
		a = userRepository.findByUserName(userName).get();
		return a;
	}
	public List<User> getAllUsers()
	{	List<User> to =new ArrayList<>();
	userRepository.findAll().forEach(to ::add);
		return to;
	}
	
	public void addUser(User user)
	{
		userRepository.save(user);
	}
	public void deleteUser(User user)
	{
		userRepository.delete(user);
	}
	public void modifyUser(String username,User user)
	{
		User modifieduser =getAllUsers().stream().filter(u->u.getUserName().equals(username)).findFirst().get();
		modifieduser = user;
		userRepository.save(modifieduser);		
	}
	
	
	    
	   
		public String getAlphaNumericString(int n) 
	    { 
	  
	        // chose a Character random from this String 
	        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	                                    + "0123456789"
	                                    + "abcdefghijklmnopqrstuvxyz"; 
	  
	        // create StringBuffer size of AlphaNumericString 
	        StringBuilder sb = new StringBuilder(n); 
	  
	        for (int i = 0; i < n; i++) { 
	  
	            // generate a random number between 
	            // 0 to AlphaNumericString variable length 
	            int index 
	                = (int)(AlphaNumericString.length() 
	                        * Math.random()); 
	  
	            // add Character one by one in end of sb 
	            sb.append(AlphaNumericString 
	                          .charAt(index)); 
	        } 
	  
	        return sb.toString(); 
	    } 
	  
	   
	} 
	

