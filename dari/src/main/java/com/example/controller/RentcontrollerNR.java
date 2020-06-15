package com.example.controller;


import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.Part;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.example.entity.Rent;
import com.example.entity.User;
import com.example.repository.rentRepository;
import com.example.service.FileStorageService;
import com.example.service.rentService;



@Scope(value = "session")
@Controller(value ="rentcontroller")
@ELBeanName(value = "rentcontroller")
@Join(path = "/user/Rent", to = "/pages/user/RentDocs.jsf")

public class  RentcontrollerNR{
	@Autowired
    private rentService rentservice;
	 @Autowired 
	 private FileStorageService fileStorageService; 
	
	private String cin;
	private String garantor;
	private String letterOfCommitment;
	private String proofOfPayment;
	private Rent rent;
	private List<Rent> rents;
	private User connectedUser ;
	private List<Rent> rentD;
	private String adJson;
	private Part  cinf;
	private Part  letterOfCommitmentf ;
	private Part  proofOfPaymentf;
	private Part  garantorf;
	rentRepository rentRepository;
	 public void adddocs() throws Exception {	
			
		 cinf.write("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+cinf.getSubmittedFileName());		 		 
		File oldFile=new File("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+cinf.getSubmittedFileName());
		String AddedName= rentservice.getAlphaNumericString(7)+cinf.getSubmittedFileName();
		File newfile =new File("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+AddedName);
		oldFile.renameTo(newfile);
		
		letterOfCommitmentf.write("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+letterOfCommitmentf.getSubmittedFileName());		 		 
		File oldFilel=new File("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+letterOfCommitmentf.getSubmittedFileName());
		String AddedNamel= rentservice.getAlphaNumericString(7)+letterOfCommitmentf.getSubmittedFileName();
		File newfilel =new File("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+AddedNamel);
		oldFilel.renameTo(newfilel);
		
		proofOfPaymentf.write("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+proofOfPaymentf.getSubmittedFileName());		 		 
		File oldFilep=new File("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+proofOfPaymentf.getSubmittedFileName());
		String AddedNamep= rentservice.getAlphaNumericString(7)+proofOfPaymentf.getSubmittedFileName();
		File newfilep =new File("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+AddedNamep);
		oldFilep.renameTo(newfilep);
		
		 garantorf.write("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+garantorf.getSubmittedFileName());		 		 
		File oldFileg=new File("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+garantorf.getSubmittedFileName());
		String AddedNameg= rentservice.getAlphaNumericString(7)+garantorf.getSubmittedFileName();
		File newfileg =new File("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+AddedNameg);
		oldFileg.renameTo(newfileg);
	    rentservice.saveRent(HomeController.connectedUser.getUserName(), AddedName, AddedNamel, AddedNamep, AddedNameg);
			 
		}
	
	
	public FileStorageService getFileStorageService() {
		return fileStorageService;
	}
	public void setFileStorageService(FileStorageService fileStorageService) {
		this.fileStorageService = fileStorageService;
	}
	public String getAdJson() {
		return adJson;
	}
	public void setAdJson(String adJson) {
		this.adJson = adJson;
	}

	public rentService getRentservice() {
		return rentservice;
	}


	public void setRentservice(rentService rentservice) {
		this.rentservice = rentservice;
	}


	public Part getCinf() {
		return cinf;
	}


	public void setCinf(Part cinf) {
		this.cinf = cinf;
	}


	public Part getLetterOfCommitmentf() {
		return letterOfCommitmentf;
	}


	public void setLetterOfCommitmentf(Part letterOfCommitmentf) {
		this.letterOfCommitmentf = letterOfCommitmentf;
	}


	public Part getProofOfPaymentf() {
		return proofOfPaymentf;
	}


	public void setProofOfPaymentf(Part proofOfPaymentf) {
		this.proofOfPaymentf = proofOfPaymentf;
	}


	public Part getGarantorf() {
		return garantorf;
	}


	public void setGarantorf(Part garantorf) {
		this.garantorf = garantorf;
	}


	public List<Rent> getRentD() {
		 rentD=rentservice.getmyRentList(HomeController.connectedUser.getUserName());
		return rentD;
	}
	public void setRentD(List<Rent> rentD) {
		this.rentD = rentD;
	}
	public void  testwhoIsConnected()
	 {
		 connectedUser = HomeController.connectedUser; 
	 }
	 public User getConnectedUser() {
			return connectedUser;
		}

		public void setConnectedUser(User connectedUser) {
			this.connectedUser = connectedUser;
		}

	public Rent getRent() {
		return rent;
	}

	public void setRent(Rent rent) {
		this.rent = rent;
	}

	public List<Rent> getRents() {
		rents=rentservice.getAllRentList();
		return rents;
	}

	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}

	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getGarantor() {
		return garantor;
	}
	public void setGarantor(String garantor) {
		this.garantor = garantor;
	}
	public String getLetterOfCommitment() {
		return letterOfCommitment;
	}
	public void setLetterOfCommitment(String letterOfCommitment) {
		this.letterOfCommitment = letterOfCommitment;
	}
	public String getProofOfPayment() {
		return proofOfPayment;
	}
	public void setProofOfPayment(String proofOfPayment) {
		this.proofOfPayment = proofOfPayment;
	}
	public void addrent() throws Exception
	{	
	 rentservice.saveRent(HomeController.connectedUser.getUserName(),cin, letterOfCommitment, proofOfPayment, garantor);
	}
	
	public void updateRentc() throws IOException
	{
		cinf.write("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+cinf.getSubmittedFileName());		 		 
		File oldFile=new File("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+cinf.getSubmittedFileName());
		String AddedName= rentservice.getAlphaNumericString(7)+cinf.getSubmittedFileName();
		File newfile =new File("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+AddedName);
		oldFile.renameTo(newfile);
		
		rentservice.updateRentc(HomeController.connectedUser.getUserName(),AddedName);
	}
	public void updateRentl() throws IOException
	{
		
		letterOfCommitmentf.write("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+letterOfCommitmentf.getSubmittedFileName());		 		 
		File oldFilel=new File("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+letterOfCommitmentf.getSubmittedFileName());
		String AddedNamel= rentservice.getAlphaNumericString(7)+letterOfCommitmentf.getSubmittedFileName();
		File newfilel =new File("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+AddedNamel);
		oldFilel.renameTo(newfilel);
		
		rentservice.updateRentl(HomeController.connectedUser.getUserName(), AddedNamel);
	}
	public void updateRentp() throws IOException
	{
		
		proofOfPaymentf.write("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+proofOfPaymentf.getSubmittedFileName());		 		 
		File oldFilep=new File("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+proofOfPaymentf.getSubmittedFileName());
		String AddedNamep= rentservice.getAlphaNumericString(7)+proofOfPaymentf.getSubmittedFileName();
		File newfilep =new File("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+AddedNamep);
		oldFilep.renameTo(newfilep);
		
		rentservice.updateRentp(HomeController.connectedUser.getUserName(), AddedNamep);
	}
	public void updateRentg() throws IOException
	{
		
		garantorf.write("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+garantorf.getSubmittedFileName());		 		 
		File oldFileg=new File("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+garantorf.getSubmittedFileName());
		String AddedNameg= rentservice.getAlphaNumericString(7)+garantorf.getSubmittedFileName();
		File newfileg =new File("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+AddedNameg);
		oldFileg.renameTo(newfileg);
		rentservice.updateRentg(HomeController.connectedUser.getUserName() ,AddedNameg);
	}
	public void updateRent() throws IOException
	{
		cinf.write("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+cinf.getSubmittedFileName());		 		 
		File oldFile=new File("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+cinf.getSubmittedFileName());
		String AddedName= rentservice.getAlphaNumericString(7)+cinf.getSubmittedFileName();
		File newfile =new File("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+AddedName);
		oldFile.renameTo(newfile);
		letterOfCommitmentf.write("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+letterOfCommitmentf.getSubmittedFileName());		 		 
		File oldFilel=new File("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+letterOfCommitmentf.getSubmittedFileName());
		String AddedNamel= rentservice.getAlphaNumericString(7)+letterOfCommitmentf.getSubmittedFileName();
		File newfilel =new File("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+AddedNamel);
		oldFilel.renameTo(newfilel);
		proofOfPaymentf.write("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+proofOfPaymentf.getSubmittedFileName());		 		 
		File oldFilep=new File("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+proofOfPaymentf.getSubmittedFileName());
		String AddedNamep= rentservice.getAlphaNumericString(7)+proofOfPaymentf.getSubmittedFileName();
		File newfilep =new File("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+AddedNamep);
		oldFilep.renameTo(newfilep);
		garantorf.write("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+garantorf.getSubmittedFileName());		 		 
		File oldFileg=new File("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+garantorf.getSubmittedFileName());
		String AddedNameg= rentservice.getAlphaNumericString(7)+garantorf.getSubmittedFileName();
		File newfileg =new File("C:\\Users\\hp\\git\\SpringProject2\\dari\\src\\main\\webapp\\resources\\usersdocs\\"+AddedNameg);
		oldFileg.renameTo(newfileg);
		rentservice.updateRent(HomeController.connectedUser.getUserName(),AddedName, AddedNamel, AddedNamep, AddedNameg);
	}
	public List<Rent> RentList()
	{
		return rentservice.getAllRentList();
	}
	public void deleteRentById(int id)
	{
		rentservice.deleteRentById(id);
	}
}
