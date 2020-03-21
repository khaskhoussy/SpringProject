package com.example.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CommentsAnnonce {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)

	private int id;
	private String contenu;
	private Date date;
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	@ManyToOne(cascade = CascadeType.ALL)
	private Announce announce;
	
	
	public CommentsAnnonce() {
		super();
	}



	public CommentsAnnonce(String contenu, Date date) {
		super();
		this.contenu = contenu;
		this.date = date;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getContenu() {
		return contenu;
	}



	public void setContenu(String contenu) {
		this.contenu = contenu;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}
	
	 

}
