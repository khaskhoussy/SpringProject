package com.example.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
		System.out.println("cookie 1 : = " + cookie1.getValue()+cookie1.getName() +"\t cookie 2 : ="+ cookie2.getValue()+cookie2.getName());
	}
	
	
	@RequestMapping(value="/MyBasket")
	public String readCookie(HttpServletRequest request,@CookieValue(value = "IdCookie", defaultValue = "0") String productIdCookie,
			@CookieValue(value = "amountCookie", defaultValue = "0") String productamountCookie) 
	{
		/*Cookie[] cookies = request.getCookies();
	    System.out.println("IdCookie = : " + Arrays.stream(cookies).filter(c->c.getName().equals("IdCookie")).findFirst().get().getValue()
	    		+ " \t amountCookie =: " +Arrays.stream(cookies).filter(c->c.getName().equals("amountCookie")).findFirst().get().getValue() ); 
	    return "chek console";*/
	    Cookie[] cookies = request.getCookies();

	    if (cookies != null) {

	        return Arrays.stream(cookies)

	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));

	    }

	    return "No cookies";

	}
	@RequestMapping(value="/valdiatBasket")
	public void validat(HttpServletResponse responses)
	{
		Cookie cookie1 = new Cookie("IdCookie", null);
		Cookie cookie2 = new Cookie("amountCookie", null);
		cookie1.setMaxAge(0);
		cookie1.setSecure(true);
		cookie1.setHttpOnly(true);
		cookie1.setPath("/");

		//add cookie to response
		
		cookie2.setMaxAge(0);
		cookie2.setSecure(true);
		cookie2.setHttpOnly(true);
		cookie2.setPath("/");

		//add cookie to response
		responses.addCookie(cookie1);
		responses.addCookie(cookie2);
	}

}
