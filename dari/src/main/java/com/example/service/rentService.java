package com.example.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Rent;



public interface rentService {

	public void updateRent(String username,String cin,String LetterOfCommitment ,String proofOfPayment,String garantor);
	public List<Rent> getAllRentList();
	public void deleteRentById(int id);
	public void saveRent(String username, String cin, String LetterOfCommitment, String proofOfPayment, String garantor) throws Exception;
	public List<Rent> getmyRentList(String username);
	public String getAlphaNumericString(int n);
	public void verif(String username);
	public void updateRentc(String username, String cin);
	public void updateRentl(String username, String LetterOfCommitment);
	public void updateRentp(String username, String proofOfPayment);
	public void updateRentg(String username, String garantor);
}
