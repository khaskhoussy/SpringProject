package com.example.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "bank"})
public class Offre implements Serializable {
	
	private static final long serialVersionUID = 6191889143079517027L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String description;
	
	private int interest_rate;
	private int self_finance_rate;
	
	@Enumerated(EnumType.STRING)
	private Duration duration;

	
	////////////////////////
	@ManyToOne
	//@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Bank bank;
	//////////////////////////
	@JsonIgnore
	@OneToMany(mappedBy="offre",cascade = CascadeType.ALL)
	private List<Simulation> simulations;
	
	public Offre() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Offre(String description, int interest_rate, int self_finance_rate, Duration duration, Bank bank,
			List<Simulation> simulations) {
		super();
		this.description = description;
		this.interest_rate = interest_rate;
		this.self_finance_rate = self_finance_rate;
		this.duration = duration;
		this.bank = bank;
		this.simulations = simulations;
	}



	public Offre(String description, int interest_rate, int self_finance_rate, Duration duration) {
		super();
		this.description = description;
		this.interest_rate = interest_rate;
		this.self_finance_rate = self_finance_rate;
		this.duration = duration;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public List<Simulation> getSimulations() {
		return simulations;
	}

	public void setSimulations(List<Simulation> simulations) {
		this.simulations = simulations;
	}



	public int getSelf_finance_rate() {
		return self_finance_rate;
	}



	public void setSelf_finance_rate(int self_finance_rate) {
		this.self_finance_rate = self_finance_rate;
	}


	
	

}
