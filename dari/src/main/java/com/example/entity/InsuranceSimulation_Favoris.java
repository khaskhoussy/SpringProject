package com.example.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class InsuranceSimulation_Favoris implements Serializable  {
	
	
	private static final long serialVersionUID = 5377539445871317492L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Temporal(TemporalType.DATE)
	private Date date;
	private int number_rooms;
	private int number_floors;
	private int house_age;
	private int house_value;
	private int goods_value;
//	private int interest_category;
//	private int interest_goods;
	private float monthlyPayback;
	private float total;
	private String InsuranceName;
	
	@ManyToOne
	//@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonIgnore
	private User user;


	public InsuranceSimulation_Favoris() {
		super();
		// TODO Auto-generated constructor stub
	}


	public InsuranceSimulation_Favoris(Date date, int number_rooms, int number_floors, int house_age, int house_value,
			int goods_value, float monthlyPayback, float total, String insuranceName, User user) {
		super();
		this.date = date;
		this.number_rooms = number_rooms;
		this.number_floors = number_floors;
		this.house_age = house_age;
		this.house_value = house_value;
		this.goods_value = goods_value;
		this.monthlyPayback = monthlyPayback;
		this.total = total;
		InsuranceName = insuranceName;
		this.user = user;
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


	public int getNumber_rooms() {
		return number_rooms;
	}


	public void setNumber_rooms(int number_rooms) {
		this.number_rooms = number_rooms;
	}


	public int getNumber_floors() {
		return number_floors;
	}


	public void setNumber_floors(int number_floors) {
		this.number_floors = number_floors;
	}


	public int getHouse_age() {
		return house_age;
	}


	public void setHouse_age(int house_age) {
		this.house_age = house_age;
	}


	public int getHouse_value() {
		return house_value;
	}


	public void setHouse_value(int house_value) {
		this.house_value = house_value;
	}


	public int getGoods_value() {
		return goods_value;
	}


	public void setGoods_value(int goods_value) {
		this.goods_value = goods_value;
	}


	public float getMonthlyPayback() {
		return monthlyPayback;
	}


	public void setMonthlyPayback(float monthlyPayback) {
		this.monthlyPayback = monthlyPayback;
	}


	public float getTotal() {
		return total;
	}


	public void setTotal(float total) {
		this.total = total;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getInsuranceName() {
		return InsuranceName;
	}


	public void setInsuranceName(String insuranceName) {
		InsuranceName = insuranceName;
	}
	
	
	
	
	
	
}
