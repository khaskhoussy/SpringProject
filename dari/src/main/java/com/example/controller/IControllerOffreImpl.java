package com.example.controller;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.example.entity.Bank;
import com.example.entity.Duration;
import com.example.entity.Offre;
import com.example.repository.OffreRepository;
import com.example.restcontroller.Home;
import com.example.service.IOffreService;

@Scope(value = "session")
@Controller(value = "offreController")
@ELBeanName(value = "offreController")
//@Join(path = "/user/addOffre", to = "/pages/user/addOffre.jsf")
@Join(path = "/form", to = "/pages/user/form.jsf")
public class IControllerOffreImpl {
	
	@Autowired
	OffreRepository offreRepository;
	
	@Autowired
	IOffreService ioffreservice;
	
	
	
	private String description;
	
	private int interest_rate;
	private int self_finance_rate;
	
	@Enumerated(EnumType.STRING)
	private Duration duration;

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
	public int ajouterOffre() {
		//ioffreservice.ajouterOffre(new Offre(description,interest_rate,self_finance_rate,duration),idBank);
		System.err.println(description);
		System.err.println(interest_rate);
		System.err.println(self_finance_rate);
		System.err.println(duration);
		
		ioffreservice.ajouterOffre(new Offre(description,interest_rate,self_finance_rate,duration),HomeController.connectedUser.getUserName());
		return 1;
	}
	public Duration[] getDurations() { return Duration.values(); }
	
	
	
	
}
