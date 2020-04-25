package com.example.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Announce;
import com.example.entity.CommentsAnnonce;
import com.example.entity.User;
import com.example.repository.AnnonceRepository;
import com.example.repository.CommentRepository;
import com.example.repository.UserRepository;

@Service
public class AnnounceServiceImpl implements AnnounceService {
	
	@Autowired
	UserRepository uR;
	@Autowired
	AnnonceRepository aR;
	@Autowired
	CommentRepository cR;
	
	
	
	
	@Override
	public void ajouterAnnonce(Announce ad, String username) {
		
		User user = uR.findByUserName(username).get();
		ad.setUser(user);
		
		aR.save(ad);
	
	
		
	}
	@Override
	public void deleteAdById(int id, String username) {
	aR.deleteById(id);
		
	}
	@Override
	public List<Announce> findAll() {
		return aR.findAll();
	}
	@Override
	public List<Announce> findAnnounceByUser(String username) {
		
		return aR.findAnnounceByUser(username);
	}
	@Override
	public String ajouterComment(int idannounce, String username ,CommentsAnnonce comment) {
		
		User user = uR.findByUserName(username).get();
		Announce announce = aR.findById(idannounce).get();
		comment.setDate(new Date());
		comment.setAnnounce(announce);
		comment.setUser(user);
		
		 String[] strings = new String[] {"badwords", "badword"};
			String str1 = comment.getContenu();
			 for(String a : strings){
		            if (str1.contains(a)) {
		                System.out.println("The Keyword :example: is found in given string");
		                str1 = str1.replace((CharSequence) a,"****");

		            }


			  }
		        
		       
			cR.save(comment);
			return str1;
			 	
			 	}
		
	


}
