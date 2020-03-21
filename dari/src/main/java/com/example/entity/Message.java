package com.example.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Message {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)

	private int id ;
	private Date date;
	private String messageContenu;
	private boolean notification ;
	
	@OneToMany(mappedBy="message",cascade = CascadeType.ALL)
	private List<User> users;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;	
	
	
	public Message() {
		super();
	}
	public Message(Date date, String messageContenu, boolean notification) {
		super();
		this.date = date;
		this.messageContenu = messageContenu;
		this.notification = notification;
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
	public String getMessageContenu() {
		return messageContenu;
	}
	public void setMessageContenu(String messageContenu) {
		this.messageContenu = messageContenu;
	}
	public boolean isNotification() {
		return notification;
	}
	public void setNotification(boolean notification) {
		this.notification = notification;
	}
	
	
}
