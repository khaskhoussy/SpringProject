package com.example.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.Exceptions.FilesException;
import com.example.entity.Announce;
import com.example.entity.CommentsAnnonce;
import com.example.entity.Pictures;
import com.example.entity.User;
import com.example.repository.AnnonceRepository;
import com.example.repository.CommentRepository;
import com.example.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AnnounceServiceImpl implements AnnounceService {
	
	@Autowired
	UserRepository uR;
	@Autowired
	AnnonceRepository aR;
	@Autowired
	CommentRepository cR;
    @Autowired 
    private FileStorageService fileStorageService; 
    private static final Logger log = LoggerFactory.getLogger( AnnounceServiceImpl.class ); 
    ObjectMapper objectMapper = new ObjectMapper();
	
	
	
	
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
	@Override
	public Announce ajouterAd(String adJson, String username, List<MultipartFile> file) throws JsonMappingException, JsonProcessingException, IOException {
		Announce annId = objectMapper.readValue(adJson, Announce.class);
		User user = uR.findByUserName(username).get();
		annId.setUser(user);
		aR.save(annId);
		for (MultipartFile f : file)
    	{	    	
	 String targetLocation = fileStorageService.storeFile( f ); 
        log.debug( "targetLocation: " + targetLocation ); 

        if ( StringUtils.isEmpty( targetLocation.endsWith("/") ) )
        	
        	
        	
        throw new FilesException("Please choose a images"); 
        
        

		
           new Announce();
        
		

			String fileName = fileStorageService.storeFile(f);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/user/announce/downloadFile/").path(fileName).toUriString();
			Pictures image = new Pictures();
			image.setImage(fileDownloadUri);
			image.setAnnounce(annId);
			
			fileStorageService.saveImage(image);
    	
    	}
		
		return annId;
	}
	@Override
	public Announce getAddById(int annId) {
	return	aR.findById(annId).get();
	
		
	}
		
	


}
