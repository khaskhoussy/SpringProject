package com.example.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "user", "offre"})
public class Simulation implements Serializable{
///	

	@EmbeddedId
	private SimulationPK simulationpk;
	
	private static final long serialVersionUID = 3876346912862238239L;
	
	
	
	 //idUser est a la fois primary key et foreign key
	@ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id", insertable=false, updatable=false)
	private User user;
	
	//idOffre est a la fois primary key et foreign key
	
	@ManyToOne
    @JoinColumn(name = "idOffre", referencedColumnName = "id", insertable=false, updatable=false)
	private Offre offre;

	public SimulationPK getSimulationpk() {
		return simulationpk;
	}

	public void setSimulationpk(SimulationPK simulationpk) {
		this.simulationpk = simulationpk;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Offre getOffre() {
		return offre;
	}

	public void setOffre(Offre offre) {
		this.offre = offre;
	}

	@Override
	public String toString() {
		return "Simulation [simulationpk=" + simulationpk + "]";
	}
	 
	
	
	
	
	
	/*


	
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Offre offre;*/
}
