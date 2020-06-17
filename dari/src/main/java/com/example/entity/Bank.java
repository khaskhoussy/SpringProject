package com.example.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Bank implements Serializable{

	
	
	private static final long serialVersionUID = 3152690779535828408L;
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	

	private String email;
	
	private int phone;
	
	private String adress;
	
	private int document_fees_interest;

	
	
	//////////////////////////////
	@OneToMany(mappedBy="bank", 
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, 
			fetch=FetchType.LAZY)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private List<Offre> offres = new ArrayList<>();
	/////////////////////////
	public Bank() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Bank(String name, String email, int phone, String adress, int document_fees_interest) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.adress = adress;
		this.document_fees_interest = document_fees_interest;
	}






	public Bank(int id, String email, int phone, String adress, int document_fees_interest) {
		super();
		this.id = id;
		this.email = email;
		this.phone = phone;
		this.adress = adress;
		this.document_fees_interest = document_fees_interest;
	}



	public Bank(int id, String name, String email, int phone, String adress, int document_fees_interest) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.adress = adress;
		this.document_fees_interest = document_fees_interest;
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

	public List<Offre> getOffres() {
		return offres;
	}

	public void setOffres(List<Offre> offres) {
		this.offres = offres;
	}

	public int getDocument_fees_interest() {
		return document_fees_interest;
	}

	public void setDocument_fees_interest(int document_fees_interest) {
		this.document_fees_interest = document_fees_interest;
	}



	
	
	
	
}