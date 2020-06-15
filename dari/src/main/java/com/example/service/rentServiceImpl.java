package com.example.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.Part;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.entity.Announce;
import com.example.entity.Rent;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.repository.rentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@Service("rentService")
public class rentServiceImpl implements rentService {
	 @Autowired
	 rentRepository rentRepository;
	 @Autowired
	 UserRepository userRepository;
	 @Autowired 
	 private FileStorageService fileStorageService; 

	 private static final Logger l=LogManager.getLogger(rentServiceImpl.class);
	 ObjectMapper objectMapper = new ObjectMapper();
	@Override
	public void saveRent(String username,String cin,String LetterOfCommitment ,String proofOfPayment,String garantor) throws Exception 
	{
		Rent rent = new Rent();
		User user =userRepository.findByUserName(username).get();
		Rent userid=rentRepository.userid(user.getId());
		LocalDateTime localDate = LocalDateTime.now();

		if(userid==null)
		{

		rent.setDaterent(localDate);
		rent.setUser(user);
		rent.setCin(cin);
		rent.setLetterOfCommitment(LetterOfCommitment);
		rent.setProofOfPayment(proofOfPayment);
		rent.setGarantor(garantor);
		rentRepository.save(rent);
		}
		else 
		{
			throw new Exception("Erreur ce user a deja des document dans la base ");	
		}
		}

	@Override
	public void updateRent(String username,String cin,String LetterOfCommitment ,String proofOfPayment,String garantor) {
		User u =userRepository.findByUserName(username).get();
		Rent rent = rentRepository.user(username);
		LocalDateTime localDate = LocalDateTime.now();
		 
		 rent.setDaterent(localDate);
		 rent.setUser(u);
		 rent.setCin(cin);
		 rent.setLetterOfCommitment(LetterOfCommitment);
		 rent.setProofOfPayment(proofOfPayment);
		 rent.setGarantor(garantor);
		 rentRepository.save(rent);
	}

	@Override
	public List<Rent> getAllRentList() {
		List<Rent> rent=(List<Rent>) rentRepository.findAll();
		for(Rent Rent:rent){
			l.info("Rent ++ :" +Rent);
		}
		return rent;
	}
	@Override
	public List<Rent> getmyRentList(String username) {
		List<Rent> rent=(List<Rent>) rentRepository.userdoc(username);
		for(Rent Rent:rent){
			l.info("Rent ++ :" +Rent);
		}
		return rent;
	}



	@Override
	public void deleteRentById(int id) {
		 rentRepository.deleteById(id);
	}
	
	@Override
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
