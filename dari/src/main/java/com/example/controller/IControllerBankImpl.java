package com.example.controller;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.entity.Bank;
import com.example.entity.Duration;
import com.example.entity.Offre;
import com.example.repository.BankRepository;
import com.example.service.IBankService;
import com.example.service.IOffreService;

//import tn.esprit.spring.entities.Bank;
//import tn.esprit.spring.repository.BankRepository;
//import tn.esprit.spring.services.IBankService;

@Scope(value = "session")
@Controller(value = "bankController")
@ELBeanName(value = "bankController")
//@Join(path = "/user/addBank", to = "/pages/user/addBank.jsf")
//@Join(path = "/user/showBank", to = "/pages/user/showBank.jsf")
//@Join(path = "/user/bankChoice", to = "/pages/user/bankChoice.jsf")
@Join(path = "/addOffre2", to = "/pages/user/addOffre2.jsf")
public class IControllerBankImpl {

	@Autowired
	IBankService ibankservice;
	
	@Autowired
	IOffreService ioffreservice;
	
	@Autowired
	BankRepository bankRepository;
	public int ajouterBank(Bank bank) {
		ibankservice.ajouterBank(bank);
		return bank.getId();
	}

	private String name;
	
	@Email
	private String email;
	
	private int phone;
	
	private String adress;
	
	private int document_fees_interest;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public int getDocument_fees_interest() {
		return document_fees_interest;
	}

	public void setDocument_fees_interest(int document_fees_interest) {
		this.document_fees_interest = document_fees_interest;
	}

	public IBankService getIbankservice() {
		return ibankservice;
	}

	public void setIbankservice(IBankService ibankservice) {
		this.ibankservice = ibankservice;
	}
	
	public String doLogin() {
		String navigateTo = "null";
			navigateTo = "/pages/admin/welcome.xhtml?faces-redirect=fase";	
		return navigateTo;
	}
	
	

	
	public String addBank() {
		String navigateTo ="null";
		//if (authenticatedUser==null || !loggedIn) return "/login.xhtml";
		ibankservice.addOrUpdateBank(new Bank(name, email, phone, adress, document_fees_interest));
		//return navigateTo;
		return navigateTo;
	}
	
	private List<Bank> showBank;
	public List<Bank> getShowBank() {
		showBank=ibankservice.getBankById(HomeController.connectedUser.getUserName());
		return showBank;
	}

	public void setShowBank(List<Bank> showBank) {
		this.showBank = showBank;
	}
	
	/////////////////////update
	private int bankIdToBeUpdated;
	

	public int getBankIdToBeUpdated() {
		return bankIdToBeUpdated;
	}

	public void setBankIdToBeUpdated(int bankIdToBeUpdated) {
		this.bankIdToBeUpdated = bankIdToBeUpdated;
	}

	public void displayBank(Bank bank){	
		this.setName(bank.getName());
		this.setAdress(bank.getAdress());
		this.setDocument_fees_interest(bank.getDocument_fees_interest());
		this.setEmail(bank.getEmail());
		this.setPhone(bank.getPhone());
		this.setBankIdToBeUpdated(bank.getId());
	}
	
	
	
	public void updateBank(){
//		System.err.println(bankIdToBeUpdated);
//		System.err.println(adress);
//		System.err.println(document_fees_interest);
//		System.err.println(email);
//		System.err.println(phone);
		
//		ibankservice.addOrUpdateBank(new Bank(bankIdToBeUpdated,name,adress,document_fees_interest,email,phone));
//		ibankservice.addOrUpdateBank(new Bank(bankIdToBeUpdated,adress,document_fees_interest,email,phone));	
		ibankservice.addOrUpdateBank(new Bank(bankIdToBeUpdated,name,email,phone,adress,document_fees_interest));
	}
	
	
	public String redirectToAddBank(){
		return "addBank.xhtml";
	}
	public String redirectToShowBank(){
//		return "simulation2.xhtml";
		return "showBank.xhtml";
	}
	public String redirectToUpdateBank(){
//		return "simulation2.xhtml";
		return "updateBank.xhtml";
	}	
	
	
////////////////////////////////////show offres//////////////////////
	private int bankId;
	
	
	private String description;
	
	private int interest_rate;
	private int self_finance_rate;
	
	@Enumerated(EnumType.STRING)
	private Duration duration;
	private int idOffreToBeUpdated;

	
	public int getIdOffreToBeUpdated() {
		return idOffreToBeUpdated;
	}

	public void setIdOffreToBeUpdated(int idOffreToBeUpdated) {
		this.idOffreToBeUpdated = idOffreToBeUpdated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getInterest_rate() {
		return interest_rate;
	}

	public void setInterest_rate(int interest_rate) {
		this.interest_rate = interest_rate;
	}

	public int getSelf_finance_rate() {
		return self_finance_rate;
	}

	public void setSelf_finance_rate(int self_finance_rate) {
		this.self_finance_rate = self_finance_rate;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	
	private List<Offre> offres;
	
	
	public List<Offre> getOffres() {
		offres=ibankservice.getAllOffresByBank(HomeController.connectedUser.getUserName());
		return offres;
	}

	public void setOffres(List<Offre> offres) {
		this.offres = offres;
	}

	
	public void displayOffre(Offre offre){

		this.setIdOffreToBeUpdated(offre.getId());
		this.setDescription(offre.getDescription());
		this.setInterest_rate(offre.getInterest_rate());
		this.setSelf_finance_rate(offre.getSelf_finance_rate());
		this.setDuration(offre.getDuration());

//		System.err.println(description);
//		System.err.println(interest_rate);
//		System.err.println(self_finance_rate);
//		System.err.println(duration);
//		System.err.println(idOffreToBeUpdated);		
		
	}
	
	
	
	
	
	public void updateOffre(){
//		System.err.println(bankIdToBeUpdated);
//		System.err.println(adress);
//		System.err.println(document_fees_interest);
//		System.err.println(email);
//		System.err.println(phone);
		//public void updateOffreById(Offre offre, int offreId,String userName)
//		ioffreservice.addOrUpdateOffer(new Offre(idOffreToBeUpdated,description,interest_rate,self_finance_rate,duration));
		ioffreservice.updateOffreById(new Offre(description,interest_rate,self_finance_rate,duration), idOffreToBeUpdated, HomeController.connectedUser.getUserName());
	}
	
	
	
	
	public int ajouterOffre() {
		//ioffreservice.ajouterOffre(new Offre(description,interest_rate,self_finance_rate,duration),idBank);
//		System.err.println(description);
//		System.err.println(interest_rate);
//		System.err.println(self_finance_rate);
//		System.err.println(duration);
		
		ioffreservice.ajouterOffre(new Offre(description,interest_rate,self_finance_rate,duration),HomeController.connectedUser.getUserName());
		return 1;
	}
	public Duration[] getDurations() { return Duration.values(); }
}
