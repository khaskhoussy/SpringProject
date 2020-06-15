package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.mail.Store;
import javax.servlet.http.Part;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.example.entity.ProductImages;
import com.example.service.ShopService;

@Scope(value = "session")
@Controller(value ="ProductDetailController")
@ELBeanName(value = "ProductDetailController")
@Join(path = "/user/ProductDetail", to = "/pages/user/ProductDetail.jsf")
public class ProductDetail {
	@Autowired
	ShopService shopService;
	
	Logger logger = LoggerFactory.getLogger(Profile.class);
	private List<ProductImages> images;
	private int amount;
	private int idProduct;

	
	public void init()
	{
		logger.info("azeazeaze \t"+Integer.parseInt(StoreController.param1));
		images = shopService.allImages().stream().filter(im->im.getIdProduct().getId() == Integer.parseInt(StoreController.param1)).collect(Collectors.toList());
		idProduct=Integer.parseInt(StoreController.param1);
	}

	public void addToBasket()
	{
		shopService.saveOneProduct(Integer.parseInt(StoreController.param1), amount);
	}
	
	
	public List<ProductImages> getImages() {
		return images;
	}

	public void setImages(List<ProductImages> images) {
		this.images = images;
	}




	public int getAmount() {
		return amount;
	}




	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	


	
}
