package com.example.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Embeddable
public class SimulationPK implements Serializable {

	private static final long serialVersionUID = 5377539445871317492L;
	
	private int idUser;
	
	private int idOffre;
	
	
	
	@Temporal(TemporalType.DATE)
	private Date date;
	private float monthleyPayBack;
	private float creditAmount;
	private int refundPeriod;
	private float monthlyCapacity;
	private float price;
	private float self_finance;
	private float document_fees;
	public SimulationPK() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
	public SimulationPK(int idUser, int idOffre, Date date, float monthleyPayBack, float creditAmount, int refundPeriod,
			float monthlyCapacity, float price, float self_finance, float document_fees) {
		super();
		this.idUser = idUser;
		this.idOffre = idOffre;
		this.date = date;
		this.monthleyPayBack = monthleyPayBack;
		this.creditAmount = creditAmount;
		this.refundPeriod = refundPeriod;
		this.monthlyCapacity = monthlyCapacity;
		this.price = price;
		this.self_finance = self_finance;
		this.document_fees = document_fees;
	}





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(creditAmount);
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + idOffre;
		result = prime * result + idUser;
		result = prime * result + Float.floatToIntBits(monthleyPayBack);
		result = prime * result + Float.floatToIntBits(monthlyCapacity);
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + refundPeriod;
		result = prime * result + Float.floatToIntBits(self_finance);
		return result;
	}





	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimulationPK other = (SimulationPK) obj;
		if (Float.floatToIntBits(creditAmount) != Float.floatToIntBits(other.creditAmount))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (idOffre != other.idOffre)
			return false;
		if (idUser != other.idUser)
			return false;
		if (Float.floatToIntBits(monthleyPayBack) != Float.floatToIntBits(other.monthleyPayBack))
			return false;
		if (Float.floatToIntBits(monthlyCapacity) != Float.floatToIntBits(other.monthlyCapacity))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (refundPeriod != other.refundPeriod)
			return false;
		if (Float.floatToIntBits(self_finance) != Float.floatToIntBits(other.self_finance))
			return false;
		return true;
	}





	public int getIdUser() {
		return idUser;
	}





	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}





	public int getIdOffre() {
		return idOffre;
	}





	public void setIdOffre(int idOffre) {
		this.idOffre = idOffre;
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





	public float getMonthlyCapacity() {
		return monthlyCapacity;
	}





	public void setMonthlyCapacity(float monthlyCapacity) {
		this.monthlyCapacity = monthlyCapacity;
	}





	public float getPrice() {
		return price;
	}





	public void setPrice(float price) {
		this.price = price;
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


}
