package com.example.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.entity.Bank;
import com.example.entity.Duration;
import com.example.entity.Offre;
import com.example.entity.Simulation_Favoris;
import com.example.repository.BankRepository;
import com.example.repository.OffreRepository;
import com.example.repository.SimulationPKRepository;
import com.example.repository.SimulationRepository;
import com.example.service.ISimulationService;

@ManagedBean(name="charts")
@ViewScoped
public class Charts {
	
	@Autowired
	ISimulationService isimulationservice;

	@Autowired
	BankRepository bankrepository;

	@Autowired
	OffreRepository OffreRepository;

	@Autowired

	SimulationRepository simulationRepository;

	 @Autowired
	 SimulationPKRepository simulationPKRepository;
	
	
	
	
	
	private float creditAmount;
	private int refundPeriod;
	private String name;
	private float monthlyCapacity;	
	private float self_finance;
	private float monthlyPayback;
	private float total;
	private float document_fees;
	

	
	public float getCreditAmount() {
		return creditAmount;
	}



	public void setCreditAmount(float creditAmount) {
		this.creditAmount = creditAmount;
	}



	public int getRefundPeriod() {
		return refundPeriod;
	}



	public void setRefundPeriod(int refundPeriod) {
		this.refundPeriod = refundPeriod;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public float getMonthlyCapacity() {
		return monthlyCapacity;
	}



	public void setMonthlyCapacity(float monthlyCapacity) {
		this.monthlyCapacity = monthlyCapacity;
	}



	public float getSelf_finance() {
		return self_finance;
	}



	public void setSelf_finance(float self_finance) {
		this.self_finance = self_finance;
	}



	public float getMonthlyPayback() {
		return monthlyPayback;
	}



	public void setMonthlyPayback(float monthlyPayback) {
		this.monthlyPayback = monthlyPayback;
	}



	public float getTotal() {
		return total;
	}



	public void setTotal(float total) {
		this.total = total;
	}



	public float getDocument_fees() {
		return document_fees;
	}



	public void setDocument_fees(float document_fees) {
		this.document_fees = document_fees;
	}



	@PostConstruct
	public void init(){
//		System.err.println(creditAmount);
//		System.err.println(refundPeriod);
//		System.err.println(monthlyCapacity);
//		System.err.println(name);
//		System.err.println(self_finance);
		Duration durations = null;

		Duration durationparam = null;
		int interest_self;
		if (refundPeriod <= 5) {
			durationparam = durations.ONE_TO_FIVE;

		}

		else if (refundPeriod > 5 && refundPeriod <= 10) {
			durationparam = durations.FIVE_TO_TEN;
		}

		else if (refundPeriod > 10) {
			durationparam = durations.TEN_PLUS;
		}

		Bank bank = new Bank();
		Offre offre = new Offre();
		for (int i = 0; i < bankrepository.findByName(name).size(); i++) {
			bank = bankrepository.findByName(name).get(i);
		}

//		OffreRepository.FindByInterest(bank.getId(), durationparam);
//		OffreRepository.findBySelf_finance_rate(bank.getId(), durationparam);
		System.err.println(bank.getId());
		
		System.err.println("erzerezrzr");

//		isimulationservice.ajoutSimulation(creditAmount, refundPeriod, name,
//				OffreRepository.FindByInterest(bank.getId(), durationparam), "bbb",
//				OffreRepository.offreid(bank.getId(), durationparam), monthlyCapacity, self_finance);
//		isimulationservice.selfFinanceComparaison(self_finance, refundPeriod, creditAmount, name,
//				OffreRepository.findBySelf_finance_rate(bank.getId(), durationparam));

		
		float c =(float)(OffreRepository.FindByInterest(bank.getId(), durationparam)/(float)100*1.0);
		float a = ((creditAmount*c)/12);
		float b =(float)(1-Math.pow((1+((float)c/(float)12*1.0)),-refundPeriod*12));
		
		monthlyPayback = (float)Math.abs((float)a/(float)b);
//		this.creditAmount = creditAmount;
	}

}
