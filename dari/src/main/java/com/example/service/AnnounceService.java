package com.example.service;

import java.util.List;

import com.example.entity.Announce;
import com.example.entity.CommentsAnnonce;




public interface AnnounceService {

	
	public void ajouterAnnonce(Announce ad ,String username);
	public void deleteAdById(int id, String username);
	public List<Announce> findAll();
	public List<Announce> findAnnounceByUser(String username) ;
	public String ajouterComment(int idannounce,String username,CommentsAnnonce comment);
}
