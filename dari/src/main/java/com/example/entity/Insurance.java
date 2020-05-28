package com.example.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
public class Insurance implements Serializable{
	private static final long serialVersionUID = 3152690779535828408L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
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

	
	@OneToMany(mappedBy="insurance", 
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, 
			fetch=FetchType.LAZY)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private List<Insurance_Offer> insurance_offers = new ArrayList<>();


	public Insurance() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Insurance(String name, @Email String email, int phone, String adress, float interest_firesafety,
			float interest_waterDamage, float interest_robbery, float interest_age, String description) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.adress = adress;
		this.interest_firesafety = interest_firesafety;
		this.interest_waterDamage = interest_waterDamage;
		this.interest_robbery = interest_robbery;
		this.interest_age = interest_age;
		this.description = description;
	}




	public Insurance(String name, @Email String email, int phone, String adress, float interest_firesafety,
			float interest_waterDamage, float interest_robbery, float interest_age, String description,
			List<Insurance_Offer> insurance_offers) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.adress = adress;
		this.interest_firesafety = interest_firesafety;
		this.interest_waterDamage = interest_waterDamage;
		this.interest_robbery = interest_robbery;
		this.interest_age = interest_age;
		this.description = description;
		this.insurance_offers = insurance_offers;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


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



	public List<Insurance_Offer> getInsurance_offers() {
		return insurance_offers;
	}


	public void setInsurance_offers(List<Insurance_Offer> insurance_offers) {
		this.insurance_offers = insurance_offers;
	}



	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
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
	
	
	
	
	
}
