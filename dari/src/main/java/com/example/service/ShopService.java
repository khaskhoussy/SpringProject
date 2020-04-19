package com.example.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.ProductImages;
import com.example.entity.Shop;
import com.example.repository.ProductImagesRepository;
import com.example.repository.ShopRepository;
import com.example.repository.UserRepository;

@Service
public class ShopService {
	
	@Autowired
	ShopRepository shopRepository;
	@Autowired
	ProductImagesRepository imagesRepository;
	@Autowired
	UserRepository userRepository;
	
	static List<Shop> activeBaseket = new ArrayList<>();

	
	public List<Shop> allShops()
	{
		List<Shop> all = new ArrayList<>();
		shopRepository.findAll().forEach(all::add);
		return all;		
	}
	public List<ProductImages> allImages()
	{
		List<ProductImages> all = new ArrayList<>();
		imagesRepository.findAll().forEach(all::add);
		return all;
	}

	
	
	public void addProduct(String username,Shop product,String photos)
	{
		
		product.setPostDate(new Date());
		product.setOwnerUser(userRepository.findByUserName(username).get());
		shopRepository.save(product);		
		List<String> productImages = new ArrayList<String>();
		productImages = Arrays.asList(photos.split(","));
		productImages.stream().forEach(ph->
		{
			ProductImages newone = new ProductImages(ph, product);
			imagesRepository.save(newone);			
		});				
   }
	
	
	public List<Shop> myBasket(String ids,String mounts)
	{
		List<Shop> myShops=new ArrayList<>();
		 Arrays.asList(ids.split("-")).stream().map(idd->Integer.parseInt(idd)).forEach(id->
		 {
			 myShops.add(shopRepository.findById(id).get());
		 });					 
		List<Integer>Listofumbers =Arrays.asList(mounts.split("-")).stream().map(mountss->Integer.parseInt(mountss)).collect(Collectors.toList());
		for (int i=0; i<Listofumbers.size();i++)
		{
			myShops.get(i).setAmount(Listofumbers.get(i));
		}
		activeBaseket = myShops;
		return myShops;

	}
	
	
	public void validateBasket()
	{	
		HashMap<Integer,Integer> listOfids = new HashMap<>();
		activeBaseket.stream().forEach(prod->
		{
		
			listOfids.put(prod.getId(),prod.getAmount());
		}
				);
		listOfids.entrySet().stream().forEach(x->
		{
			Shop selected =shopRepository.findById(x.getKey()).get();
			int newAmount = selected.getAmount()-x.getValue();
			if(newAmount>0)
			{	selected.setAmount(newAmount);
			shopRepository.save(selected);
			}
			else 
				System.out.println("sorry we dont have that much of amount!!");			
		});
	
		
		activeBaseket=null;
	}
	
	
	public void saveOneProduct(int id ,int amount)
	{
		Shop selected =shopRepository.findById(id).get();
		int newAmount = selected.getAmount()-amount;
		if(newAmount>0)
		{	
			selected.setAmount(newAmount);
			shopRepository.save(selected);
		}
		else 
			System.out.println("sorry we dont have that much of amount!!");	
	}
	public void backTofutur(HashMap<Date, int[]> entredMap)
	{
		entredMap.entrySet().forEach(saved->{
			Shop selected =shopRepository.findById(saved.getValue()[0]).get();
			int newAmount = selected.getAmount()+saved.getValue()[1];
			selected.setAmount(newAmount);
			shopRepository.save(selected);
		});
	}
	public float basketPrice()
	{
		float price = 0;
		for(Shop p : activeBaseket){
			price =	price +(p.getAmount()*p.getPrice());
		}
		return price;
	}
}
