package com.example.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Announce;
import com.example.entity.CommentsAnnonce;
import com.example.entity.Reservation;
import com.example.service.AnnounceService;

@RestController
@RequestMapping("/user/announce")
public class AnnounceController {
	@Autowired
	AnnounceService As;
	
	
	  @PostMapping("/createAd")
	  @ResponseBody
	  public void ajouterAnnonce(@RequestBody Announce ad,HttpServletResponse response,HttpServletRequest request)
	  {
		  
		  As.ajouterAnnonce(ad,Home.connectedUser);
	  }
	  
	  @DeleteMapping("/deleteadbyId/{id}") 
	  @ResponseBody 
		public void deleteReservationById(@PathVariable("id") int id,HttpServletResponse response,HttpServletRequest request) {
			As.deleteAdById(id,Home.connectedUser);
			
		}
	  @RequestMapping(value="/allAds")
	    public List<Announce> getAllAds(HttpServletResponse response,HttpServletRequest request) 
	    {
	    	return As.findAll();
	    }
	  
	    @RequestMapping(value="/adbyuser")
	    public List<Announce> findAnnounceByUser(HttpServletResponse response,HttpServletRequest request) 
	    {
	    	return As.findAnnounceByUser(Home.connectedUser);
	    }
	  

		  @PostMapping("/addcomment/{idannounce}")
		  @ResponseBody
		  public String ajouterComment(@RequestBody CommentsAnnonce com ,@PathVariable("idannounce") int idannounce,HttpServletResponse response,HttpServletRequest request)
		  {
			  
			  return As.ajouterComment(idannounce, Home.connectedUser, com);
		
		  }

}
