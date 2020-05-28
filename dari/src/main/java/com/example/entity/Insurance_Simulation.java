package com.example.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties({ "user", "insurance_offer"})
public class Insurance_Simulation implements Serializable{

	
	@EmbeddedId
	private Insurance_SimulationPK insurance_simulationPK;
	
	private static final long serialVersionUID = 3876346912862238239L;
	
	 //idUser est a la fois primary key et foreign key
	@ManyToOne
   @JoinColumn(name = "idUser", referencedColumnName = "id", insertable=false, updatable=false)
	private User user;
	
	//idOffre est a la fois primary key et foreign key
	
	@ManyToOne
   @JoinColumn(name = "idOffre", referencedColumnName = "id", insertable=false, updatable=false)
	private Insurance_Offer insurance_offer;

	public Insurance_SimulationPK getInsurance_simulationPK() {
		return insurance_simulationPK;
	}

	public void setInsurance_simulationPK(Insurance_SimulationPK insurance_simulationPK) {
		this.insurance_simulationPK = insurance_simulationPK;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Insurance_Offer getInsurance_offer() {
		return insurance_offer;
	}

	public void setInsurance_offer(Insurance_Offer insurance_offer) {
		this.insurance_offer = insurance_offer;
	}

	@Override
	public String toString() {
		return "Insurance_Simulation [insurance_simulationPK=" + insurance_simulationPK + "]";
	}
	
	
	
	
	
}
