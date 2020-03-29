package com.example.entity;

import java.util.List;

import javax.persistence.*;

@Entity
public class Relation {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private boolean notification;
	private int idUser1;
	private int idUser2;
	
	
	public Relation(boolean notification, int idUser1, int idUser2) {
		super();
		this.notification = notification;
		this.idUser1 = idUser1;
		this.idUser2 = idUser2;
	}
	public int getIdUser1() {
		return idUser1;
	}
	public void setIdUser1(int idUser1) {
		this.idUser1 = idUser1;
	}
	public int getIdUser2() {
		return idUser2;
	}
	public void setIdUser2(int idUser2) {
		this.idUser2 = idUser2;
	}
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
