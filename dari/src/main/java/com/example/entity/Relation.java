package com.example.entity;

import java.util.List;

import javax.persistence.*;

@Entity
public class Relation {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private boolean notification;
	
	@OneToMany(mappedBy="relation",cascade = CascadeType.ALL)
	private List<User> users;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	
	public Relation() {
		super();
	}
	public Relation(boolean notification) {
		super();
		this.notification = notification;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isNotification() {
		return notification;
	}
	public void setNotification(boolean notification) {
		this.notification = notification;
	}
	
}
