package com.example.controller;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Announce;
import com.example.entity.Pictures;
import com.example.repository.AnnonceRepository;
import com.example.repository.PicturesRepository;
import com.example.service.AnnounceServiceImpl;

@Controller(value = "DetailsController")
@ELBeanName(value = "DetailsController")
@Join(path = "/details", to = "/pages/user/details.jsf")

public class DetailsController {

	@Autowired
	AnnounceServiceImpl As;
	@Autowired
	PicturesRepository pR;
	private List <Pictures> images ;
	
	

	public List<Pictures> getImages() {
		return images;
	}


	public void setImages(List<Pictures> images) {
		this.images = images;
	}


	public Announce getAddbyid(){
				
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> param = fc.getExternalContext().getRequestParameterMap();
		int id =Integer.parseInt(param.get("idannonce"));
		
		 
		 
		 return  As.getAddById(id);
		
	}
	
	
public List<Pictures>findAddimages(Integer announceid) {
		
		
		
		return pR.findImageAdd(announceid);
		
		
	}
	
	
	

}
