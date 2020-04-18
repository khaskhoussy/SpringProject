package com.example.service;

import java.util.List;

import com.example.entity.Rent;



public interface rentService {

	public void updateRent(int id,int user_id,String cin,String LetterOfCommitment ,String proofOfPayment,String garantor);
	public List<Rent> getAllRentList();
	void deleteRentById(int id);
	public void saveRent(int user_id, String cin, String LetterOfCommitment, String proofOfPayment, String garantor) throws Exception;

}
