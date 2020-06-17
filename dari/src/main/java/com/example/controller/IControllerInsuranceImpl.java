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

import com.example.entity.Category;
import com.example.entity.Insurance;
import com.example.entity.Insurance_Offer;
import com.example.repository.InsuranceRepository;
import com.example.service.IInsuranceOfferService;
import com.example.service.IInsuranceService;

@Scope(value = "session")
@Controller(value = "insuranceController")
@ELBeanName(value = "insuranceController")
//@Join(path = "/user/addInsurance", to = "/pages/user/addInsurance.jsf")
@Join(path = "/addOffre3", to = "/pages/user/addOffre3.jsf")
public class IControllerInsuranceImpl {
	
	@Autowired
	InsuranceRepository iinsurancerepository;
	@Autowired
	IInsuranceService iinsuranceservice;
	
	@Autowired
	IInsuranceOfferService iinsuranceOfferService;
	
	
	private String name;
	
	@Email
	private String email;
	
	private int phone;
	
	private String adress;
	
	private float interest_firesafety;
	
	private float interest_waterDamage;
	
	private float interest_robbery;
	
	private float interest_age;
	
	private String description;

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

	public float getInterest_firesafety() {
		return interest_firesafety;
	}

	public void setInterest_firesafety(float interest_firesafety) {
		this.interest_firesafety = interest_firesafety;
	}

	public float getInterest_waterDamage() {
		return interest_waterDamage;
	}

	public void setInterest_waterDamage(float interest_waterDamage) {
		this.interest_waterDamage = interest_waterDamage;
	}

	public float getInterest_robbery() {
		return interest_robbery;
	}

	public void setInterest_robbery(float interest_robbery) {
		this.interest_robbery = interest_robbery;
	}

	public float getInterest_age() {
		return interest_age;
	}

	public void setInterest_age(float interest_age) {
		this.interest_age = interest_age;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int ajouterInsurance() {
		//if(insurance.getInterest_age()<1 && insurance.getInterest_firesafety()<1 && insurance.getInterest_robbery()<1 && insurance.getInterest_waterDamage()<1)
		if(interest_age<1 && interest_firesafety<1 && interest_robbery<1 && interest_waterDamage<1)
		{
//			iinsurancerepository.save(new Insurance(name,email,phone,adress,interest_firesafety,interest_waterDamage,interest_robbery,description));
			iinsuranceservice.ajouterInsurance(new Insurance(name,email,phone,adress,interest_firesafety,interest_waterDamage,interest_robbery,interest_age,description));
		}
		return 1;
	}
	
	
	
	
	
	
	////show Insurance///
	private List<Insurance> showInsurance;

	public List<Insurance> getShowInsurance() {
		showInsurance=iinsuranceservice.getInsuranceById(HomeController.connectedUser.getUserName());
		return showInsurance;
	}

	public void setShowInsurance(List<Insurance> showInsurance) {
		this.showInsurance = showInsurance;
	}
	
	
	
	
	
	
	public void displayInsurance(Insurance insurance){
		this.setAdress(insurance.getAdress());
		this.setDescription(insurance.getDescription());
		this.setEmail(insurance.getEmail());
		this.setInterest_age(insurance.getInterest_age());
		this.setInterest_firesafety(insurance.getInterest_firesafety());
		this.setInterest_robbery(insurance.getInterest_robbery());
		this.setInterest_waterDamage(insurance.getInterest_waterDamage());
		this.setName(insurance.getName());
		this.setPhone(insurance.getPhone());
		this.setIdInsuranceToBeUpdated(insurance.getId());
	}
	
	
////////////////////////update insurance/////
	private int idInsuranceToBeUpdated;

	public int getIdInsuranceToBeUpdated() {
		return idInsuranceToBeUpdated;
	}

	public void setIdInsuranceToBeUpdated(int idInsuranceToBeUpdated) {
		this.idInsuranceToBeUpdated = idInsuranceToBeUpdated;
	}
	
	public void updateInsurance(){
		iinsuranceservice.addOrUpdateInsurance(new Insurance(idInsuranceToBeUpdated,name,email,phone,adress,
interest_firesafety,interest_waterDamage,interest_robbery,interest_age,description));
//		iinsuranceservice.updateInsuranceById(new Insurance(name,email,phone,adress,
//				interest_firesafety,interest_waterDamage,interest_robbery,interest_age,description), idInsuranceToBeUpdated);
	}

	
	
	
	///////////////////show offers/////////////////////
	
	private float interest_category;
	private float interest_goods;
	
	@Enumerated(EnumType.STRING)
	private Category category;

	public Category[] getCategories() { return Category.values(); }	
	
	private int IdInsuranceOfferToBeUpdated;
	
	public int getIdInsuranceOfferToBeUpdated() {
		return IdInsuranceOfferToBeUpdated;
	}

	public void setIdInsuranceOfferToBeUpdated(int idInsuranceOfferToBeUpdated) {
		IdInsuranceOfferToBeUpdated = idInsuranceOfferToBeUpdated;
	}

	public float getInterest_category() {
		return interest_category;
	}

	public void setInterest_category(float interest_category) {
		this.interest_category = interest_category;
	}

	public float getInterest_goods() {
		return interest_goods;
	}

	public void setInterest_goods(float interest_goods) {
		this.interest_goods = interest_goods;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
	private List <Insurance_Offer>offers;

	public List<Insurance_Offer> getOffers() {
		offers=iinsuranceservice.getAllOffresByInsurance(HomeController.connectedUser.getUserName());
		return offers;
	}

	public void setOffers(List<Insurance_Offer> offers) {
		this.offers = offers;
	}
	
	
	
	
	public void DisplayInsuranceOffer(Insurance_Offer offer){
		this.setIdInsuranceOfferToBeUpdated(offer.getId());
		this.setInterest_category(offer.getInterest_category());
		this.setInterest_goods(offer.getInterest_goods());
		this.setCategory(offer.getCategory());
	}
	
	public void ajouterOffre(){
		iinsuranceOfferService.ajouterInsuranceOffer(new Insurance_Offer(interest_category,interest_goods,category), HomeController.connectedUser.getUserName());
	}
	
	
	
	public void updateOffre(){
		iinsuranceOfferService.updateInsuranceOfferById(new Insurance_Offer(interest_category,interest_goods,category), IdInsuranceOfferToBeUpdated, HomeController.connectedUser.getUserName());
	}
}
