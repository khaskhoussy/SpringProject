package com.example.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Announce;
import com.example.entity.CommentsAnnonce;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;




public interface AnnounceService {

	
	public void ajouterAnnonce(Announce ad ,String username);
	public void deleteAdById(int id, String username);
	public List<Announce> findAll();
	public List<Announce> findAnnounceByUser(String username) ;
	public String ajouterComment(int idannounce,String username,CommentsAnnonce comment);
	public Announce ajouterAd (String adJson, String username,List <MultipartFile> file) throws JsonMappingException, JsonProcessingException ,  IOException;
	public Announce getAddById(int annId);

}
