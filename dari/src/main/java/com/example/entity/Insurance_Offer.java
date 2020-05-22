package com.example.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@JsonIgnoreProperties({ "insurance"})
public class Insurance_Offer implements Serializable {

	
	private static final long serialVersionUID = 6191889143079517027L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private float interest_category;
	private float interest_goods;
	
	@Enumerated(EnumType.STRING)
	private Category category;
	
	@ManyToOne
	//@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Insurance insurance;
	
	
	@JsonIgnore
	@OneToMany(mappedBy="insurance_offer",cascade = CascadeType.ALL)
	private List<Insurance_Simulation> insurance_simulations;


	public Insurance_Offer() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Insurance_Offer(float interest_category, float interest_goods, Category category, Insurance insurance,
			List<Insurance_Simulation> insurance_simulations) {
		super();
		this.interest_category = interest_category;
		this.interest_goods = interest_goods;
		this.category = category;
		this.insurance = insurance;
		this.insurance_simulations = insurance_simulations;
	}


	public Insurance_Offer(float interest_category, float interest_goods, Category category) {
		super();
		this.interest_category = interest_category;
		this.interest_goods = interest_goods;
		this.category = category;
	}



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public Insurance getInsurance() {
		return insurance;
	}


	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}


	public List<Insurance_Simulation> getInsurance_simulations() {
		return insurance_simulations;
	}


	public void setInsurance_simulations(List<Insurance_Simulation> insurance_simulations) {
		this.insurance_simulations = insurance_simulations;
	}
	
	
}
