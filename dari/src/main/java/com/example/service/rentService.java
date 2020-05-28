package com.example.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Rent;



public interface rentService {

	public void updateRent(String username,String cin,String LetterOfCommitment ,String proofOfPayment,String garantor);
	public List<Rent> getAllRentList();
	void deleteRentById(int id);
	public void saveRent(String username, String cin, String LetterOfCommitment, String proofOfPayment, String garantor) throws Exception;
	public List<Rent> getmyRentList(String username);
	public String getAlphaNumericString(int n);
}
