package com.example.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.example.entity.Announce;
import com.example.entity.Pictures;
import com.example.entity.Reservation;
import com.example.entity.User;
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
	private float price,superficie,prixtotla;
	private int idannonce;
	private int idupdate;
	
	
	public int getIdupdate() {
		return idupdate;
	}

	public void setIdupdate(int idupdate) {
		this.idupdate = idupdate;
	}

	public float getPrixtotla() {
		prixtotla=rS.calculprix(idannonce,checkIn,checkOut);
    	System.out.println("  idrannonce ="+idannonce+"  CheckIn ="+checkIn+"   CheckOut ="+checkOut);

		return prixtotla;
	}

	public void setPrixtotla(float prixtotla) {
		this.prixtotla = prixtotla;
	}
	

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
	this.setUser(HomeController.connectedUser);
	navigateTo = "/pages/user/reserve.xhtml?faces-redirect=true";
		return navigateTo;
	}
	public String res(Reservation res)
	{
		String navigateTo ="null";
	this.setIdupdate(res.getId());
	this.setAnnonce(res.getAnnounce());
	this.setIdannonce(res.getAnnounce().getId());
	this.setDescription(res.getAnnounce().getDescription());
	this.setImg(res.getAnnounce().getImg());
	this.setNbrchambre(res.getAnnounce().getNbrchambre());
	this.setSuperficie(res.getAnnounce().getSuperficie());
	this.setPrice(res.getAnnounce().getPrice());
	this.setPhoneNumber(res.getAnnounce().getUser().getPhoneNumber());
	this.setMailAddress(res.getAnnounce().getUser().getMailAddress());
    System.out.println("id="+idupdate);

	navigateTo = "/pages/user/modifres.xhtml?faces-redirect=true";
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
	
	
	    public void ajouterReservation() throws Exception {
	    	rS.ajouterReservation(idannonce,HomeController.connectedUser.getUserName(),checkIn,checkOut);
	    }
	    
	    public List<Reservation> getAllreservation() 
	    {
	    	return rS.findAllReservation();
	    }
	    

		public void modifierres() throws Exception {
	    	rS.modifierReservation(idupdate,idannonce,HomeController.connectedUser.getUserName(), checkIn, checkOut);
	    	System.out.println("idreservation ="+idupdate+"  idrannonce ="+idannonce+"  CheckIn ="+checkIn+"   CheckOut ="+checkOut);
		}		
		
		


		public void deleteReservationById(int id) {
			rS.deleteReservation(id);
			
		}
		

	    public void ajouterReservationLong() throws Exception {
	         rS.ajouterReservationLong(idannonce,HomeController.connectedUser.getUserName());
	    }
	    private List<Reservation> findReservationByUser=new ArrayList<>();;
	    
	    public List<Reservation> getFindReservationByUser() {
	    	
	    	findReservationByUser=rS.findReservationByUser(HomeController.connectedUser.getUserName());
	    	return findReservationByUser;
		}

		public void setFindReservationByUser(List<Reservation> findReservationByUser) {
			this.findReservationByUser = findReservationByUser;
		}
private List<Reservation> findReservationByUsern;
	    
	    public List<Reservation> getFindReservationByUsern() {
	    	
	    	findReservationByUsern=rS.findReservationByUsernow(HomeController.connectedUser.getUserName());
	    	return findReservationByUsern;
		}

		public void setFindReservationByUsern(List<Reservation> findReservationByUsern) {
			this.findReservationByUsern = findReservationByUsern;
		}
		
private List<Reservation> findReservationByUsert;
	    
	    public List<Reservation> getFindReservationByUsert() {
	    	
	    	findReservationByUsert=rS.findReservationByUserhis(HomeController.connectedUser.getUserName());
	    	return findReservationByUsert;
		}

		public void setFindReservationByUsert(List<Reservation> findReservationByUsert) {
			this.findReservationByUsert = findReservationByUsert;
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
		private ArrayList<Pictures> images=new ArrayList<Pictures>();
		
		
		public ArrayList<Pictures> getImages() {
			return images;
		}

		public void setImages(ArrayList<Pictures> images) {
			this.images = images;
		}

		public List<Pictures> findAllimages() {



			return rS.findAllimages(idannonce);


		}
	    
}
