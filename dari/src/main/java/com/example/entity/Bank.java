package com.example.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.persistence.OneToMany;

@Entity
public class Bank {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String mail;
	private int phoneNumber;
	private String address;
	
	@OneToMany(mappedBy="bank",cascade = CascadeType.ALL)
	private List<Offre> offres;
	
	
	
	public Bank() {
		super();
	}



	public Bank(String name, String mail, int phoneNumber, String address) {
		super();
		this.name = name;
		this.mail = mail;
		this.phoneNumber = phoneNumber;
		this.address = address;
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



	public String getMail() {
		return mail;
	}



	public void setMail(String mail) {
		this.mail = mail;
	}



	public int getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}
	
	

	
}
