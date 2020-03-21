package com.example.entity;

import java.util.List;

import javax.persistence.*;

@Entity
public class Offre {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ;
	private String description;
	private int intrestRate;
	private int duration;
	
	@OneToMany(mappedBy="offre",cascade = CascadeType.ALL)
	private List<Simulation> simulations;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Bank bank;
	
	public Offre() {
		super();
	}

	public Offre(String description, int intrestRate, int duration) {
		super();
		this.description = description;
		this.intrestRate = intrestRate;
		this.duration = duration;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIntrestRate() {
		return intrestRate;
	}

	public void setIntrestRate(int intrestRate) {
		this.intrestRate = intrestRate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	
	
	

}
