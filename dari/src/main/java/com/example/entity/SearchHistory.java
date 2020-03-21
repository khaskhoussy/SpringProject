package com.example.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.persistence.ManyToOne;

@Entity
public class SearchHistory {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private Date date;
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Announce announce;
	
	
	public SearchHistory(Date date) {
		super();
		this.date = date;
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
	
	
}
