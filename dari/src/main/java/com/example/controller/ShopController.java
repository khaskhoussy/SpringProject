package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.ProductImages;
import com.example.entity.Shop;
import com.example.service.ShopService;

@RestController
@RequestMapping("/user/Shop")
public class ShopController {
	
	@Autowired
	ShopService shopService;
	
	static String photos ;
	
	
	public String ProductPhotos(String photo){
		photos = photos + photo +",";
		
		return photos;	
	}
	
	@RequestMapping(method = RequestMethod.POST,value="/addProduct")
	public void addProduct(@RequestBody Shop product)
	{
		String test="test1,test2,test3,test4";
		shopService.addProduct(Home.connectedUser, product,test);
	}
		

}
