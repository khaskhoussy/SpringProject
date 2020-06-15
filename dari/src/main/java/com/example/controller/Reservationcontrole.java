package com.example.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entity.Announce;
import com.example.entity.Reservation;
import com.example.entity.User;
import com.example.restcontroller.Home;
import com.example.service.reservationService;


@Scope(value = "session")
@Controller(value ="reservationcontroller")
@ELBeanName(value = "reservationcontroller")
@Join(path = "/user/recherche", to = "/pages/user/recherche.jsf")
public class Reservationcontrole {
	
	
	@Autowired
	reservationService rS;
	private int id;
	private Announce announce;
	private LocalDateTime dateres;
	private User user;
	private boolean isValide;
	private Date checkIn;
	private Date checkOut;
	private String type;
	private String region;
	private float priceMin, priceMax;
	private int chambremin;
	private String description;
	private String mailAddress;
	private String img;
	private int phoneNumber,nbrchambre;
	private float price,superficie;
	private int idannonce;
	
	
	public int getIdannonce() {
		return idannonce;
	}

	public void setIdannonce(int idannonce) {
		this.idannonce = idannonce;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public int getNbrchambre() {
		return nbrchambre;
	}

	public void setNbrchambre(int nbrchambre) {
		this.nbrchambre = nbrchambre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getSuperficie() {
		return superficie;
	}

	public void setSuperficie(float superficie) {
		this.superficie = superficie;
	}

	public String afficher(Announce an)
	{
		String navigateTo ="null";
	this.setIdannonce(an.getId());
	this.setDescription(an.getDescription());
	this.setDescription(an.getDescription());
	this.setImg(an.getImg());
	this.setNbrchambre(an.getNbrchambre());
	this.setSuperficie(an.getSuperficie());
	this.setPrice(an.getPrice());
	this.setPhoneNumber(an.getUser().getPhoneNumber());
	this.setMailAddress(an.getUser().getMailAddress());
	navigateTo = "/pages/user/reserve.xhtml?faces-redirect=false";
		return navigateTo;
	}
	

	public void setRechercher(List<Announce> rechercher) {
		this.rechercher = rechercher;
	}

	public float getPriceMin() {
		return priceMin;
	}
	public void setPriceMin(float priceMin) {
		this.priceMin = priceMin;
	}
	public float getPriceMax() {
		return priceMax;
	}
	public void setPriceMax(float priceMax) {
		this.priceMax = priceMax;
	}
	public int getChambremin() {
		return chambremin;
	}
	public void setChambremin(int chambremin) {
		this.chambremin = chambremin;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Announce getAnnounce() {
		return announce;
	}
	public void setAnnounce(Announce announce) {
		this.announce = announce;
	}
	public LocalDateTime getDateres() {
		return dateres;
	}
	public void setDateres(LocalDateTime dateres) {
		this.dateres = dateres;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isValide() {
		return isValide;
	}
	public void setValide(boolean isValide) {
		this.isValide = isValide;
	}
	public Date getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}
	public Date getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}
	
	
	    public String ajouterReservation() throws Exception {
	    	String navigateTo ="null";
	    	rS.ajouterReservation(idannonce,HomeController.connectedUser.getUserName(),checkIn,checkOut);
	         navigateTo = "/pages/user/recherche.xhtml?faces-redirect=true";
	 		return navigateTo;
	    }
	    
	    public List<Reservation> getAllreservation() 
	    {
	    	return rS.findAllReservation();
	    }
	    

		public void modifierReservation() throws Exception {
		    
	    	rS.modifierReservation(id,announce.getId(),HomeController.connectedUser.getUserName(), checkIn, checkOut);

		}
		

		public void deleteReservationById() {
			rS.deleteReservationById(id,HomeController.connectedUser.getUserName());
			
		}

	    public void ajouterReservationLong() throws Exception {
	         rS.ajouterReservationLong(announce.getId(),HomeController.connectedUser.getUserName());
	    }
	    private List<Reservation> findReservationByUser;
	    
	    public List<Reservation> getFindReservationByUser() {
	    	
	    	findReservationByUser=rS.findReservationByUser(HomeController.connectedUser.getUserName());
	    	return findReservationByUser;
		}

		public void setFindReservationByUser(List<Reservation> findReservationByUser) {
			this.findReservationByUser = findReservationByUser;
		}


	    private Announce annonce;
	    
		public Announce getAnnonce() {
			return annonce;
		}
		public void setAnnonce(Announce annonce) {
			this.annonce = annonce;
		}

		private List<Announce>rechercher= new ArrayList<Announce>();
		
		public List<Announce> getRechercher() throws Exception {

			if((chambremin==0)&&(checkIn==null)&&(checkOut==null)&&(priceMin==0)&&(priceMax==0)) {
	    		return rS.annoncebyregion(type, region);
	    		
	    	}
	    	if((chambremin==0)&&(checkIn==null)&&(checkOut==null)) {
	    		return  rS.annoncebyprice(type, region, priceMin, priceMax);
	    	}
	    	else if((checkIn==null)&&(checkOut==null)&&(priceMin==0)&&(priceMax==0)){
	    		return rS.findannoncebynbrchambre(type, region, chambremin);
	    	}
			else if((checkIn==null)&&(checkOut==null)){
				return rS.findannoncebyprixnbrchambre(type, region, priceMin, priceMax, chambremin);
			}
			else if((priceMin==0)&&(priceMax==0)&&(chambremin==0)){
				return rS.findannoncebydate(type, region, checkIn, checkOut);
			}
			else if((priceMin==0)&&(priceMax==0)){
				return rS.findannoncebydatec(type, region, checkIn, checkOut,chambremin);
			}
			else if(chambremin==0){
				return rS.findannoncebyd(type, region, priceMin, priceMax, checkIn, checkOut);
			}
			else {
				return rS.findannonceby(type,region,chambremin, priceMin, priceMax, checkIn, checkOut);
	    	
		}

		}
	    
}
