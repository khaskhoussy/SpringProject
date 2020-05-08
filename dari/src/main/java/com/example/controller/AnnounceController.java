package com.example.controller;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.entity.Announce;
import com.example.entity.Pictures;
import com.example.repository.PicturesRepository;
import com.example.service.AnnounceServiceImpl;

@Controller(value = "AnnounceController")
@ELBeanName(value = "AnnounceController")
@Join(path = "/Add", to = "/pages/user/Add.jsf")
public class AnnounceController {
	@Autowired
	AnnounceServiceImpl As;
	@Autowired
	PicturesRepository pR;
	private String image ;
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<Announce> findAll() {
		return As.findAll();
		
	}
	public Pictures findAllimages(Integer announceid) {
		
		
		
		return pR.findImageAdd(announceid).get(0);
		
		
	}
	//FacesContext fc = FacesContext.getCurrentInstance();
	//Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
	// int id =Integer.parseInt(params.get("idannonce"));
	 //trecuperi param 
	
	

}
