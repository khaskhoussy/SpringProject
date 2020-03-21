package com.example.entity;

import java.util.List;

import javax.persistence.*;

@Entity
public class Rent {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String garantor;
	private String letterOfCommitment;
	private String proofOfPayment;
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Announce announce;
	
	@OneToMany(mappedBy="rent",cascade = CascadeType.ALL)
	private List<Reservation> Reservations;
	
	public Rent() {
		super();
	}



	public Rent(String garantor, String letterOfCommitment, String proofOfPayment) {
		super();
		this.garantor = garantor;
		this.letterOfCommitment = letterOfCommitment;
		this.proofOfPayment = proofOfPayment;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getGarantor() {
		return garantor;
	}



	public void setGarantor(String garantor) {
		this.garantor = garantor;
	}



	public String getLetterOfCommitment() {
		return letterOfCommitment;
	}



	public void setLetterOfCommitment(String letterOfCommitment) {
		this.letterOfCommitment = letterOfCommitment;
	}



	public String getProofOfPayment() {
		return proofOfPayment;
	}



	public void setProofOfPayment(String proofOfPayment) {
		this.proofOfPayment = proofOfPayment;
	}
	
	
}
