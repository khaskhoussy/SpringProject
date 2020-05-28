package com.example.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Expert_Insurance implements Serializable{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ;
	
	private String insurance_name;

	public Expert_Insurance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Expert_Insurance(String insurance_name) {
		super();
		this.insurance_name = insurance_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInsurance_name() {
		return insurance_name;
	}

	public void setInsurance_name(String insurance_name) {
		this.insurance_name = insurance_name;
	}
	
	
	
	
	
}
