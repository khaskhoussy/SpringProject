package com.example.service;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Rent;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.repository.rentRepository;



@Service("rentService")
public class rentServiceImpl implements rentService {
	 @Autowired
	 rentRepository rentRepository;
	 @Autowired
	 UserRepository userRepository;

	 private static final Logger l=LogManager.getLogger(rentServiceImpl.class);

	@Override
	public void saveRent(String username,String cin,String LetterOfCommitment ,String proofOfPayment,String garantor) throws Exception 
	{
		Rent rent = new Rent();
		User user =userRepository.findByUserName(username).get();
		//User user =userRepository.findById(1).get();
		rent.setUser(user);
		rent.setCin(cin);
		rent.setLetterOfCommitment(LetterOfCommitment);
		rent.setProofOfPayment(proofOfPayment);
		rent.setGarantor(garantor);
		rentRepository.save(rent);
	}

	@Override
	public void updateRent(String username,String cin,String LetterOfCommitment ,String proofOfPayment,String garantor) {
		User u =userRepository.findByUserName(username).get();
		Rent rent = rentRepository.user(username);
		 
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
	public void deleteRentById(int id) {
		 rentRepository.deleteById(id);
	}

	

}
