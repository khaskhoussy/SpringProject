package com.example.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	static String productId=null;
	static HashMap<Date,int[]> savedProducts = new HashMap<>();
	static boolean checked = false;

		
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

	
	@GetMapping("/addToBasket/{id}/{amount}")
	public void addObjectToBasket(@PathVariable int id ,@PathVariable int amount ,HttpServletResponse response,HttpServletRequest request,
	@CookieValue(value = "IdCookie", defaultValue = "0") String productIdCookie,@CookieValue(value = "amountCookie", defaultValue = "0") String productamountCookie)
	{
		int idToDataBase = id ;		
		int amountToDataBase = amount;
		int idAndAmount[] = new int[2];
		idAndAmount[0]=id;
		idAndAmount[1]=amount;
		
		shopService.saveOneProduct(idToDataBase, amountToDataBase);
		
		savedProducts.put(new Date(),idAndAmount);
	
		
		if (productIdCookie.equals("0"))
		productIdCookie = Integer.toString(id) ;	
		else 
		productIdCookie = Integer.toString(id)+"-"+productIdCookie;
		if (productamountCookie.equals("0"))
			productamountCookie = Integer.toString(amount) ;	
		else 
			productamountCookie = Integer.toString(amount)+"-"+productamountCookie;
		
		Cookie cookie1 = new Cookie("IdCookie", productIdCookie);
		Cookie cookie2 = new Cookie("amountCookie", productamountCookie);	
		cookie1.setHttpOnly(true);
		cookie2.setHttpOnly(true);
		cookie1.setPath("/");
		cookie2.setPath("/");
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		
		checked = false;
	}
	
	
	@RequestMapping(value="/MyBasket")
	public List<Shop> readCookie(HttpServletRequest request,@CookieValue(value = "IdCookie", defaultValue = "0") String productIdCookie,
			@CookieValue(value = "amountCookie", defaultValue = "0") String productamountCookie) 
		{			   
		
	    return shopService.myBasket(productIdCookie, productamountCookie);
		}
	
	
	@RequestMapping(value="/valdiatBasket")
	public void validat(HttpServletResponse responses,@CookieValue(value = "IdCookie", defaultValue = "0") String productIdCookie,
			@CookieValue(value = "amountCookie", defaultValue = "0") String productamountCookie)
	{
		if(checked = false)
		{
			shopService.validateBasket();
			checked = true;	
			savedProducts.clear();
		}
		
			
		Cookie cookie1 = new Cookie("IdCookie", null);
		Cookie cookie2 = new Cookie("amountCookie", null);
		cookie1.setMaxAge(0);
		cookie1.setSecure(true);
		cookie1.setHttpOnly(true);
		cookie1.setPath("/");		
		cookie2.setMaxAge(0);
		cookie2.setSecure(true);
		cookie2.setHttpOnly(true);
		cookie2.setPath("/");
		responses.addCookie(cookie1);
		responses.addCookie(cookie2);
	}

final String temp = "PT10M";
	
	@Scheduled(initialDelay=1000L,fixedDelayString= temp)
	public void test()
	{
		Date now = new Date();

		if(now.getMinutes()>savedProducts.entrySet().iterator().next().getKey().getMinutes()+8 && checked == false )
		{
			shopService.backTofutur(savedProducts);
			savedProducts.clear();

		}
	}
}
