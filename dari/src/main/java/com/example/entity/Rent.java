package com.example.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
public class Rent  implements Serializable {
	private static final long serialVersionUID= 1L;

	 
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String cin;
	private String garantor;
	private String letterOfCommitment;
	private String proofOfPayment;


	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	

	


	public Rent() {
		super();

	}




	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}





	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	public String getGarantor() {
		return garantor;
	}



	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Rent [cin=" + cin + ", garantor=" + garantor + ", letterOfCommitment=" + letterOfCommitment
				+ ", proofOfPayment=" + proofOfPayment + ", ]";
	}
	
	
}
