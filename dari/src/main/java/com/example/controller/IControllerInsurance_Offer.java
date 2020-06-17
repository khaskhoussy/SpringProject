package com.example.controller;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.example.entity.Category;
import com.example.entity.Duration;
import com.example.entity.Insurance_Offer;
import com.example.entity.Offre;
import com.example.service.IInsuranceOfferService;
import com.example.service.IInsuranceService;
import com.example.service.IOffreService;

@Scope(value = "session")
@Controller(value = "insuranceOffreController")
@ELBeanName(value = "insuranceOffreController")
@Join(path = "/addInsuranceOffer", to = "/pages/user/addInsuranceOffer.jsf")
public class IControllerInsurance_Offer {
	
	
	@Autowired
	IInsuranceOfferService iinsuranceofferservice;
	
	private float interest_category;
	private float interest_goods;
	
	@Enumerated(EnumType.STRING)
	private Category category;

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
	public Category[] getCategories() { return Category.values(); }	

	
	public int ajouterInsuranceOffre() {
		//ioffreservice.ajouterOffre(new Offre(description,interest_rate,self_finance_rate,duration),idBank);
//		System.err.println(description);
//		System.err.println(interest_rate);
//		System.err.println(self_finance_rate);
//		System.err.println(duration);
		
//		ioffreservice.ajouterOffre(new Offre(interest_category,interest_goods,category),"bbb");
		iinsuranceofferservice.ajouterInsuranceOffer(new Insurance_Offer(interest_category,interest_goods,category),HomeController.connectedUser.getUserName());
		return 1;
	}
}
