package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class ProductImages {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String image;
	
	
	@ManyToOne()
	private Shop idProduct;

	
	public ProductImages() {
		super();

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Shop getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Shop idProduct) {
		this.idProduct = idProduct;
	}

	public ProductImages(String image, Shop idProduct) {
		super();
		this.image = image;
		this.idProduct = idProduct;
	}

	public ProductImages(int id, String image, Shop idProduct) {
		super();
		this.id = id;
		this.image = image;
		this.idProduct = idProduct;
	}
	

}
