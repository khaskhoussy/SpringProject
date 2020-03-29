package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Relation;
import com.example.entity.User;
import com.example.repository.RelationRepository;
import com.example.repository.UserRepository;

@Service
public class RelationService {
	
	@Autowired
	RelationRepository relationRepository;
	@Autowired 
	UserRepository userRepository;
	@Autowired
	UserService userService;

	public void addRelation(Relation relation)
	{
		relationRepository.save(relation);
	}
	
	public List<Relation> allRelation()
	{
		List<Relation>relations = new ArrayList<>();
		
		relationRepository.findAll().forEach(relations::add);
		return relations;
		
	}
	
	public List<User> myFriends(String username )
	{
		User connected = userRepository.findByUserName(username).get();
		List<Relation> myRelations=allRelation().stream().filter(r->r.getIdUser1() == connected.getId() || r.getIdUser2() == connected.getId()).collect(Collectors.toList());
				List<User> MyFriends= new ArrayList<>();
		myRelations.forEach(r->{
			if(r.getIdUser1() == connected.getId())
			MyFriends.add(userService.getAllUsers().stream().filter(user->user.getId()== r.getIdUser2()).findFirst().get());
			else 
				MyFriends.add(userService.getAllUsers().stream().filter(user->user.getId()== r.getIdUser1()).findFirst().get());
				
		});
		return MyFriends;
	}
	
}
