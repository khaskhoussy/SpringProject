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
	
	public User  findUserByName(String userName)
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
}
