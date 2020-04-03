package com.example.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


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
		
		System.out.println("her herher is"+product);
		List<String> productImages = new ArrayList<String>();
		productImages = Arrays.asList(photos.split(","));
		productImages.stream().forEach(ph->
		{
			ProductImages newone = new ProductImages(ph, product);
			imagesRepository.save(newone);
			
		});
		
		
		
	}

}
