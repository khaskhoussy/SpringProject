package com.example.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.persistence.ManyToOne;

@Entity
public class Reservation {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private Date checkIn;
	private Date checkOut;
	@ManyToOne(cascade = CascadeType.ALL)
	private Rent rent;
	
	
	public Reservation() {
		super();
	}



	public Reservation(int id, Date checkIn, Date checkOut) {
		super();
		this.id = id;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Date getCheckIn() {
		return checkIn;
	}



	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}



	public Date getCheckOut() {
		return checkOut;
	}



	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}
	
	
	
}
