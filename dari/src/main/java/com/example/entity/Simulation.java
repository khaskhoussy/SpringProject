package com.example.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.persistence.ManyToOne;

@Entity
public class Simulation {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private Date date;
	private float monthleyPayBack;
	private float creditAmount;
	private int refoundPeroid;
	private float salary;
	private float price;
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Offre offre;
	
	
	
	public Simulation() {
		super();
	}



	public Simulation(Date date, float monthleyPayBack, float creditAmount, int refoundPeroid, float salary,
			float price) {
		super();
		this.date = date;
		this.monthleyPayBack = monthleyPayBack;
		this.creditAmount = creditAmount;
		this.refoundPeroid = refoundPeroid;
		this.salary = salary;
		this.price = price;
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



	public float getMonthleyPayBack() {
		return monthleyPayBack;
	}



	public void setMonthleyPayBack(float monthleyPayBack) {
		this.monthleyPayBack = monthleyPayBack;
	}



	public float getCreditAmount() {
		return creditAmount;
	}



	public void setCreditAmount(float creditAmount) {
		this.creditAmount = creditAmount;
	}



	public int getRefoundPeroid() {
		return refoundPeroid;
	}



	public void setRefoundPeroid(int refoundPeroid) {
		this.refoundPeroid = refoundPeroid;
	}



	public float getSalary() {
		return salary;
	}



	public void setSalary(float salary) {
		this.salary = salary;
	}



	public float getPrice() {
		return price;
	}



	public void setPrice(float price) {
		this.price = price;
	}
	
	
}
