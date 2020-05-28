package com.example.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.Part;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.example.entity.Shop;
import com.example.entity.User;
import com.example.service.ShopService;
import com.example.service.UserService;

@Scope(value = "session")
@Controller(value ="StoreController")
@ELBeanName(value = "StoreController")
@Join(path = "/user/Store", to = "/pages/user/Store.jsf")
public class StoreController {
	@Autowired
	 UserService userService;
	@Autowired
	ShopService shopService;

	
	
	Logger logger = LoggerFactory.getLogger(Profile.class);
	public String categorie;
	private Part   productImage;
	private String album;
	private Shop productToAdd;
	private int amount;
	private int price;
	private String productName;
	
	public void addPhoto()throws IOException {
		
		productImage.write("C:\\Users\\aisce\\SpringProject\\dari\\src\\main\\webapp\\resources\\productImages\\"+productImage.getSubmittedFileName());		 		 
		 File oldFile=new File("C:\\Users\\aisce\\SpringProject\\dari\\src\\main\\webapp\\resources\\productImages\\"+productImage.getSubmittedFileName());
		 String AddedName=userService.getAlphaNumericString(7)+productImage.getSubmittedFileName();
		 File newfile =new File("C:\\Users\\aisce\\SpringProject\\dari\\src\\main\\webapp\\resources\\productImages\\"+AddedName);
		 oldFile.renameTo(newfile);
		 if(album == null)
			 album =AddedName +",";
		 else 
			 album = album + AddedName +",";
		
		
	}
	
	public void addProduct(){
		Shop newProduct = new Shop();
		newProduct.setCategorie(categorie);
		newProduct.setAmount(amount);
		newProduct.setPrice(price);
		newProduct.setProductName(productName);
		shopService.addProductJsf(HomeController.connectedUser.getUserName(), newProduct, album);
		album = null;
	}
	
	
	
	public Part getProductImage() {
		return productImage;
	}



	public void setProductImage(Part productImage) {
		this.productImage = productImage;
	}



	

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public Shop getProductToAdd() {
		return productToAdd;
	}

	public void setProductToAdd(Shop productToAdd) {
		this.productToAdd = productToAdd;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	
	
}
