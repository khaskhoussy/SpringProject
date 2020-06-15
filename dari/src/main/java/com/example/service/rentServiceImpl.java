package com.example.service;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Rent;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.repository.rentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;



@Service("rentService")
public class rentServiceImpl implements rentService {
	 @Autowired
	 rentRepository rentRepository;
	 @Autowired
	 UserRepository userRepository;
	 @Autowired 
	 //private FileStorageService fileStorageService; 

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
		rent.setVerif(false);
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
		 rent.setVerif(false);
		 rent.setLetterOfCommitment(LetterOfCommitment);
		 rent.setProofOfPayment(proofOfPayment);
		 rent.setGarantor(garantor);
		 rentRepository.save(rent);
	}
	@Override
	public void updateRentc(String username,String cin) {
		User u =userRepository.findByUserName(username).get();
		Rent rent = rentRepository.user(username);
		LocalDateTime localDate = LocalDateTime.now();
		 
		 rent.setDaterent(localDate);
		 rent.setCin(cin);
		 rent.setVerif(false);
		 rentRepository.save(rent);
	}
	@Override
	public void updateRentl(String username,String LetterOfCommitment ) {
		User u =userRepository.findByUserName(username).get();
		Rent rent = rentRepository.user(username);
		LocalDateTime localDate = LocalDateTime.now();
		 
		 rent.setDaterent(localDate);
		 rent.setVerif(false);
		 rent.setLetterOfCommitment(LetterOfCommitment);
		 rentRepository.save(rent);
	}
	@Override
	public void updateRentp(String username,String proofOfPayment) {
		User u =userRepository.findByUserName(username).get();
		Rent rent = rentRepository.user(username);
		LocalDateTime localDate = LocalDateTime.now();
		 
		 rent.setDaterent(localDate);
		 rent.setVerif(false);
		 rent.setProofOfPayment(proofOfPayment);
		 rentRepository.save(rent);
	}
	@Override
	public void updateRentg(String username,String garantor) {
		User u =userRepository.findByUserName(username).get();
		Rent rent = rentRepository.user(username);
		LocalDateTime localDate = LocalDateTime.now();
		 
		 rent.setDaterent(localDate);
		 rent.setVerif(false);
		 rent.setGarantor(garantor);
		 rentRepository.save(rent);
	}
	@Override
	public void verif(String username) {
		User u =userRepository.findByUserName(username).get();
		Rent rent = rentRepository.user(username);
		 rent.setUser(u);
		 rent.setVerif(true);
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
