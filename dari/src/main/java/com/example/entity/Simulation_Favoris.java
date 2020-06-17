package com.example.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Simulation_Favoris implements Serializable {

	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ;
	@Temporal(TemporalType.DATE)
	private Date date;
	private float monthleyPayBack;
	private float creditAmount;
	private int refundPeriod;
	private float price;
	private float monthlyCapacity;
	private String BankName;
	@JsonIgnore
	private int id_offre;
	private float self_finance;
	private float document_fees;
	private String duration;
	
	@ManyToOne
	//@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonIgnore
	private User user;


	public Simulation_Favoris() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Simulation_Favoris(Date date, float monthleyPayBack, float creditAmount, int refundPeriod, float price,
			float monthlyCapacity, String bankName, float self_finance, float document_fees, String duration) {
		super();
		this.date = date;
		this.monthleyPayBack = monthleyPayBack;
		this.creditAmount = creditAmount;
		this.refundPeriod = refundPeriod;
		this.price = price;
		this.monthlyCapacity = monthlyCapacity;
		this.BankName = bankName;
		this.self_finance = self_finance;
		this.document_fees = document_fees;
		this.duration = duration;
	}


	public Simulation_Favoris(Date date, float monthleyPayBack, float creditAmount, int refundPeriod, float price,
			float monthlyCapacity, String bankName, float self_finance, float document_fees, String duration,
			User user) {
		super();
		this.date = date;
		this.monthleyPayBack = monthleyPayBack;
		this.creditAmount = creditAmount;
		this.refundPeriod = refundPeriod;
		this.price = price;
		this.monthlyCapacity = monthlyCapacity;
		this.BankName = bankName;
		this.self_finance = self_finance;
		this.document_fees = document_fees;
		this.duration = duration;
		this.user = user;
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


	public int getRefundPeriod() {
		return refundPeriod;
	}


	public void setRefundPeriod(int refundPeriod) {
		this.refundPeriod = refundPeriod;
	}



	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public float getMonthlyCapacity() {
		return monthlyCapacity;
	}


	public void setMonthlyCapacity(float monthlyCapacity) {
		this.monthlyCapacity = monthlyCapacity;
	}





	public String getBankName() {
		return BankName;
	}





	public void setBankName(String bankName) {
		BankName = bankName;
	}





	public int getId_offre() {
		return id_offre;
	}





	public void setId_offre(int id_offre) {
		this.id_offre = id_offre;
	}




	public float getSelf_finance() {
		return self_finance;
	}



	public void setSelf_finance(float self_finance) {
		this.self_finance = self_finance;
	}




	public float getDocument_fees() {
		return document_fees;
	}




	public void setDocument_fees(float document_fees) {
		this.document_fees = document_fees;
	}


	public String getDuration() {
		return duration;
	}



	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	
	
}
