package com.example.entity;

import java.util.List;

import javax.persistence.*;

@Entity
public class Demande {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private boolean Notification;
	private int id_Sender;
	private String  etat;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User userReciver;
	

	
	
	public Demande(boolean notification, int id_Sender, String etat, User user_Reciver) {
		super();
		Notification = notification;
		this.id_Sender = id_Sender;
		this.etat = etat;
		this.userReciver = user_Reciver;
	}
	public Demande(boolean notification, int id_Sender, User user_Reciver) {
		super();
		Notification = notification;
		this.id_Sender = id_Sender;
		this.userReciver = user_Reciver;
	}
	public int getId_Sender() {
		return id_Sender;
	}
	public void setId_Sender(int id_Sender) {
		this.id_Sender = id_Sender;
	}
	public User getUser_Reciver() {
		return userReciver;
	}
	public void setUser_Reciver(User user_Reciver) {
		this.userReciver = user_Reciver;
	}
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
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	

}
