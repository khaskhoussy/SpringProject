package com.example.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Embeddable
public class Insurance_SimulationPK implements Serializable {
	
	private static final long serialVersionUID = 5377539445871317492L;
	
	private int idUser;
	
	private int idOffre;
	
	
	
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
	public Insurance_SimulationPK() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Insurance_SimulationPK(int idUser, int idOffre, Date date, int number_rooms, int number_floors,
			int house_age, int house_value, int goods_value, int interest_category, int interest_goods,
			float monthlyPayback, float total) {
		super();
		this.idUser = idUser;
		this.idOffre = idOffre;
		this.date = date;
		this.number_rooms = number_rooms;
		this.number_floors = number_floors;
		this.house_age = house_age;
		this.house_value = house_value;
		this.goods_value = goods_value;
//		this.interest_category = interest_category;
//		this.interest_goods = interest_goods;
		this.monthlyPayback = monthlyPayback;
		this.total = total;
	}




	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + goods_value;
		result = prime * result + house_age;
		result = prime * result + house_value;
		result = prime * result + idOffre;
		result = prime * result + idUser;
//		result = prime * result + interest_category;
//		result = prime * result + interest_goods;
		result = prime * result + Float.floatToIntBits(monthlyPayback);
		result = prime * result + number_floors;
		result = prime * result + number_rooms;
		result = prime * result + Float.floatToIntBits(total);
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Insurance_SimulationPK other = (Insurance_SimulationPK) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (goods_value != other.goods_value)
			return false;
		if (house_age != other.house_age)
			return false;
		if (house_value != other.house_value)
			return false;
		if (idOffre != other.idOffre)
			return false;
		if (idUser != other.idUser)
			return false;
//		if (interest_category != other.interest_category)
//			return false;
//		if (interest_goods != other.interest_goods)
//			return false;
		if (Float.floatToIntBits(monthlyPayback) != Float.floatToIntBits(other.monthlyPayback))
			return false;
		if (number_floors != other.number_floors)
			return false;
		if (number_rooms != other.number_rooms)
			return false;
		if (Float.floatToIntBits(total) != Float.floatToIntBits(other.total))
			return false;
		return true;
	}



	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdOffre() {
		return idOffre;
	}
	public void setIdOffre(int idOffre) {
		this.idOffre = idOffre;
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
	public int getGoods_value() {
		return goods_value;
	}
	public void setGoods_value(int goods_value) {
		this.goods_value = goods_value;
	}
//	public int getInterest_category() {
//		return interest_category;
//	}
//	public void setInterest_category(int interest_category) {
//		this.interest_category = interest_category;
//	}
//	public int getInterest_goods() {
//		return interest_goods;
//	}
//	public void setInterest_goods(int interest_goods) {
//		this.interest_goods = interest_goods;
//	}


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



	public int getHouse_value() {
		return house_value;
	}

	public void setHouse_value(int house_value) {
		this.house_value = house_value;
	}
	
	
	
}
