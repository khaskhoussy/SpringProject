package com.example.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MessageBroker {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ;
	private String userSender;
	private String userReciver;
	private Date sendDate;
	private String messageContent;
	private boolean status ;
	
	
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public MessageBroker() {
		super();
	}

	public MessageBroker(String userSender, String userReciver, String messageContent) {
		super();
		this.userSender = userSender;
		this.userReciver = userReciver;
		this.messageContent = messageContent;
	}

	public MessageBroker(String userSender, String userReciver, Date sendDate, String messageContent) {
		super();
		this.userSender = userSender;
		this.userReciver = userReciver;
		this.sendDate = sendDate;
		this.messageContent = messageContent;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserSender() {
		return userSender;
	}
	public void setUserSender(String userSender) {
		this.userSender = userSender;
	}
	public String getUserReciver() {
		return userReciver;
	}
	public void setUserReciver(String userReciver) {
		this.userReciver = userReciver;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	

}
