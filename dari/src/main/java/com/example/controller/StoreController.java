package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
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
	public String firstPhoto;
	public List<Shop> shops;
	public static String param1 ;
	public String ids;
	public String amounts;
	public List<Shop> myBusket ;
	private float totalPrice = 0;
	public float relatedTobasket =0;

	
	public String passingParameters(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        param1 = params.get("param1Name");
        logger.info("aaa"+param1);
		//logger.info("I work Fine !!!");
        return "ProductDetail.xhtml?faces-redirect=true";
    }

	
	public void shopTable(){
		shops = shopService.allShops();
		
		
		
		
	}
	
	public void addPhoto()throws IOException {
		
		productImage.write("C:\\Users\\aisce\\SpringProject\\dari\\src\\main\\webapp\\resources\\productImages\\"+productImage.getSubmittedFileName());		 		 
		 File oldFile=new File("C:\\Users\\aisce\\SpringProject\\dari\\src\\main\\webapp\\resources\\productImages\\"+productImage.getSubmittedFileName());
		 String AddedName=userService.getAlphaNumericString(7)+productImage.getSubmittedFileName();
		 File newfile =new File("C:\\Users\\aisce\\SpringProject\\dari\\src\\main\\webapp\\resources\\productImages\\"+AddedName);
		 oldFile.renameTo(newfile);		 
		 if(album == null)
		 { album =AddedName +",";
		   firstPhoto = AddedName;
		 }
		 else 
			 album = album + AddedName +",";
		
		
	}
	
	public void addProduct(){
		Shop newProduct = new Shop();
		newProduct.setCategorie(categorie);
		newProduct.setAmount(amount);
		newProduct.setPrice(price);
		newProduct.setProductName(productName);
		newProduct.setPhoto(firstPhoto);
		
		shopService.addProductJsf(HomeController.connectedUser.getUserName(), newProduct, album);
		album = null;
	}
	
	public void desplayButton(){
		myBusket = shopService.myBasket(ids,amounts);
		totalPrice = shopService.basketPriceJsf(myBusket);
	}
	public void validateBasket(){
		myBusket = null;
		totalPrice=0;
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

	public List<Shop> getShops() {
		return shops;
	}

	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}


	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}


	public String getAmounts() {
		return amounts;
	}


	public void setAmounts(String amounts) {
		this.amounts = amounts;
	}


	public List<Shop> getMyBusket() {
		return myBusket;
	}


	public void setMyBusket(List<Shop> myBusket) {
		this.myBusket = myBusket;
	}


	public float getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
	
}
