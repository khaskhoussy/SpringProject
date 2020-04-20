package com.example.controller;


import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.Announce;
import com.example.entity.Reservation;
import com.example.service.reservationService;



@RestController
@RequestMapping("/user/reservation")
public class ResersvationController {
	
	 @Autowired
	    private reservationService rS;
	    @PostMapping("/addreservation/{idannounce}/{checkIn}/{checkOut}")
	    @ResponseBody
	    public void ajouterReservation(@PathVariable("idannounce") int idannounce, @PathVariable("checkIn") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkIn,@PathVariable("checkOut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkOut,HttpServletResponse response,HttpServletRequest request) throws Exception {
	         rS.ajouterReservation(idannounce,Home.connectedUser,checkIn,checkOut);
	    }
	    
	    @RequestMapping(value="/allreservation")
	    public List<Reservation> getAllreservation() 
	    {
	    	return rS.findAllReservation();
	    }
	    

	    @PutMapping(value = "/modifierreservation/{id}/{idannounce}/{checkIn}/{checkOut}") 
	    @ResponseBody
		public void modifierReservation(@PathVariable("id") int id,@PathVariable("idannounce") int idannounce, @PathVariable("checkIn") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkIn,@PathVariable("checkOut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkOut,HttpServletResponse response,HttpServletRequest request) throws Exception {
		    
	    	rS.modifierReservation(id,idannounce, Home.connectedUser, checkIn, checkOut);

		}
		
	    @DeleteMapping("/deletereservationbyId/{id}") 
		@ResponseBody 
		public void deleteReservationById(@PathVariable("id") int id,HttpServletResponse response,HttpServletRequest request) {
			rS.deleteReservationById(id,Home.connectedUser);
			
		}
	    @PostMapping("/addreservation/{idannounce}")
	    @ResponseBody
	    public void ajouterReservationLong(@PathVariable("idannounce") int idannounce,HttpServletResponse response,HttpServletRequest request) throws Exception {
	         rS.ajouterReservationLong(idannounce,Home.connectedUser);
	    }
	  /*  @PutMapping(value ="/validerreservationLong/{id}/{idannounce}/{iduser}/{announcer}") 
	    @ResponseBody
		public void validerReservationLong(@PathVariable("id") int id,@PathVariable("idannounce") int idannounce, @PathVariable("iduser") int iduser, @PathVariable("announcer") int announcer) {
		    
		    	rS.validerReservationLong(id, idannounce, iduser, announcer);

		}*/
	    @RequestMapping(value="/reservationbyuser")
	    public List<Reservation> findReservationByUser(HttpServletResponse response,HttpServletRequest request) 
	    {
	    	return rS.findReservationByUser(Home.connectedUser);
	    }

	    @RequestMapping(value="/findAnnounce/{type}/{region}")
	    @ResponseBody
	    public List<Announce> annoncebyregion(@PathVariable("type") String type,@PathVariable("region") String region) 
		{
	    	
	    		return rS.annoncebyregion(type, region);
		}
	    @RequestMapping(value="/findAnnouncebyp/{type}/{region}/{priceMin}/{priceMax}")
	   
	    public List<Announce> annoncebyprice(@PathVariable("type") String type,@PathVariable("region") String region,@PathVariable("priceMin") float priceMin,@PathVariable("priceMax") float priceMax) 
		{
	    
	    			return rS.annoncebyprice(type, region, priceMin, priceMax);
	    		
	    	
	    }
	    @RequestMapping(value="/findAnnouncebynbrch/{type}/{region}/{chambremin}/{chambremax}")
	   
	    public List<Announce> annoncebychambre(@PathVariable("type") String type,@PathVariable("region") String region
	    		,@PathVariable("chambremin") int chambremin,@PathVariable("chambremax") int chambremax) 
		{
	    	
	    			return rS.findannoncebynbrchambre(type, region, chambremin, chambremax);
	    		
	    	
	    }
	    @RequestMapping(value="/findAnnounce/{type}/{region}/{chambremin}/{chambremax}/{priceMin}/{priceMax}")
	   
	    public List<Announce> annoncebych(@PathVariable("type") String type,@PathVariable("region") String region
	    		,@PathVariable("chambremin") int chambremin,@PathVariable("chambremax") int chambremax,
	    		@PathVariable("priceMin") float priceMin,@PathVariable("priceMax") float priceMax) 
		{
	  
	    			return rS.findannonceby(type,region,chambremin, chambremax, priceMin, priceMax);
	    		

		}
	    
	    @RequestMapping(value="/findAnnouncebydate/{type}/{region}/{checkIn}/{checkOut}")
		public List<Announce> findannonceby(@PathVariable("type") String type,
				 @PathVariable("region") String region,
				 @PathVariable("checkIn") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkIn,
				 @PathVariable("checkOut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkOut) throws Exception 
	    {
	    	return rS.findannoncebydate(type, region, checkIn, checkOut);
	    }
	    }



