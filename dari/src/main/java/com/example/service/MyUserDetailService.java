package com.example.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.configuration.MyUserDetail;
import com.example.entity.User;
import com.example.repository.UserRepository;;;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		 Optional<User> user = userRepository.findByUserName(username);
		 
		 user.orElseThrow(()->new UsernameNotFoundException("Not found :" + username));
		 
		 
		 return user.map(MyUserDetail::new).get();
		 
		 
	}

}
