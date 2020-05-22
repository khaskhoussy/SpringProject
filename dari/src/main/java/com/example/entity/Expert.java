package com.example.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Expert implements Serializable{

	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ;
	
	private String bank_name;

	public Expert() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Expert(String bank_name) {
		super();
		this.bank_name = bank_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	
	
}
