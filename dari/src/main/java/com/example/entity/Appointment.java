package com.example.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Appointment {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private Date date;
	private String address;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Announce announce;
	
	public Appointment() {
		super();
	}



	public Appointment(int id, Date date, String address) {
		super();
		this.id = id;
		this.date = date;
		this.address = address;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
