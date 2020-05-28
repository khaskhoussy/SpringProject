package com.example.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Demande {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private boolean Notification;
	private int id_Sender;
	private String  etat;
	private Date date;
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	@ManyToOne()
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
