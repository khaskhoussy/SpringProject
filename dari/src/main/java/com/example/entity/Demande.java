package com.example.entity;

import java.util.List;

import javax.persistence.*;

@Entity
public class Demande {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private boolean Notification;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	@OneToMany(mappedBy="demande",cascade = CascadeType.ALL)
	private List<User> Users;
	
	
	public Demande() {
		super();
	}
	public Demande(boolean notification) {
		super();
		Notification = notification;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isNotification() {
		return Notification;
	}
	public void setNotification(boolean notification) {
		Notification = notification;
	}
	

}
