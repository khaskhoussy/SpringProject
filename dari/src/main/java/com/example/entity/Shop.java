package com.example.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Shop {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ;
	
	private String categorie;
	
	private int amount;
	
	private String productName;
	
	private Date postDate;
	
	private float price;
	
	@ManyToOne()
	private User ownerUser ;
	
	@OneToMany(mappedBy="idProduct",cascade = CascadeType.ALL)
	private List<ProductImages> productImages;

	public Shop() {
		super();
	}

	public Shop(String categorie, int amount, String productName, float price) {
		super();
		this.categorie = categorie;
		this.amount = amount;
		this.productName = productName;
		this.price = price;
	}

	public Shop(String categorie, int amount, String productName, Date postDate, float price, User ownerUser) {
		super();
		this.categorie = categorie;
		this.amount = amount;
		this.productName = productName;
		this.postDate = postDate;
		this.price = price;
		this.ownerUser = ownerUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public User getOwnerUser() {
		return ownerUser;
	}

	public void setOwnerUser(User ownerUser) {
		this.ownerUser = ownerUser;
	}
	
	
	
}
