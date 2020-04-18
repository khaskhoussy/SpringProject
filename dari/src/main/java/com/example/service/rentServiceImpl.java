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
	public void saveRent(int user_id,String cin,String LetterOfCommitment ,String proofOfPayment,String garantor) throws Exception 
	{
		Rent rent =rentRepository.user(user_id);
		int rentid =rentRepository.user(user_id).getUser().getId();

		User user=userRepository.findById(user_id).get();
		if(user_id==rentid){
			throw new Exception("l itilisateur a deja ses documents ")	;
		}
		rent.setUser(user);
		rent.setCin(cin);
		rent.setLetterOfCommitment(LetterOfCommitment);
		rent.setProofOfPayment(proofOfPayment);
		rent.setGarantor(garantor);
		rentRepository.save(rent);
	}

	@Override
	public void updateRent(int id ,int user_id,String cin,String LetterOfCommitment ,String proofOfPayment,String garantor) {
		 User u =userRepository.findById(user_id).get();
		 Rent rent = rentRepository.update(id,user_id);
		 
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
