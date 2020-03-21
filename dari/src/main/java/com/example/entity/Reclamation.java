package com.example.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Reclamation {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private Date date;
	private String contenu;
	private String subject;
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	
	public Reclamation() {
		super();
	}



	public Reclamation(Date date, String contenu, String subject) {
		super();
		this.date = date;
		this.contenu = contenu;
		this.subject = subject;
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



	public String getContenu() {
		return contenu;
	}



	public void setContenu(String contenu) {
		this.contenu = contenu;
	}



	public String getSubject() {
		return subject;
	}



	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
}
