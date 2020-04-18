package com.example.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
public class Announce {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private float longitude;
	private float latitude;
	private Date beginDate;
	private Date endDate;
	private int vueNumber;
	private float price;
	private String img;
	private boolean disponibilité;
	private String type;
	




	//	@Enumerated(EnumType.STRING)
	//@NotNull
	private String regions;
	private int nbrchambre;
	private float superficie;
	
	
	
/*	public Regions getRegions() {
		return regions;
	}



	public void setRegions(Regions regions) {
		this.regions = regions;
	}


*/
	public int getNbrchambre() {
		return nbrchambre;
	}



	public void setNbrchambre(int nbrchambre) {
		this.nbrchambre = nbrchambre;
	}



	public float getSuperficie() {
		return superficie;
	}



	public void setSuperficie(float superficie) {
		this.superficie = superficie;
	}



	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "iduser", referencedColumnName = "id", insertable=false, updatable=false)
	private User user;
	
	@OneToMany(mappedBy="announce",cascade = CascadeType.ALL)
	private List<Appointment> Appointments;

	@JsonBackReference
	@OneToMany(mappedBy="announce",cascade = CascadeType.ALL)
	private List<Reservation> Reservation;	
	
	
	
	public List<Reservation> getReservation() {
		return Reservation;
	}



	public void setReservation(List<Reservation> reservation) {
		Reservation = reservation;
	}



	@OneToMany(mappedBy="announce",cascade = CascadeType.ALL)
	private List<CommentsAnnonce> commentsAnnonces;
	
	@OneToMany(mappedBy="announce",cascade = CascadeType.ALL)
	private List<Pictures> pictures;
	
	@OneToMany(mappedBy="announce",cascade = CascadeType.ALL)
	private List<SearchHistory> searchHistorys;
	
	@OneToMany
	@JoinTable(name="Favorites")
	private List<User> users;
	
	
	


	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Announce() {
		super();
	}

	


	public String getRegions() {
		return regions;
	}



	public void setRegions(String regions) {
		this.regions = regions;
	}



	public Announce(int id, String name, String description, Date beginDate, Date endDate, int vueNumber, float price,
			String img, boolean disponibilité, String type, String regions, int nbrchambre, float superficie,
			List<User> users) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.vueNumber = vueNumber;
		this.price = price;
		this.img = img;
		this.disponibilité = disponibilité;
		this.type = type;
		this.regions = regions;
		this.nbrchambre = nbrchambre;
		this.superficie = superficie;
		this.users = users;
	}



	public Announce(String name, String description, float longitude, float latitude, Date beginDate, Date endDate,
			int vueNumber, float price, String img, boolean disponibilité, String type) {
		super();
		this.name = name;
		this.description = description;
		this.longitude = longitude;
		this.latitude = latitude;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.vueNumber = vueNumber;
		this.price = price;
		this.img = img;
		this.disponibilité = disponibilité;
		this.type = type;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public float getLongitude() {
		return longitude;
	}



	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}



	public float getLatitude() {
		return latitude;
	}



	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}



	public Date getBeginDate() {
		return beginDate;
	}



	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}



	public Date getEndDate() {
		return endDate;
	}



	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	public int getVueNumber() {
		return vueNumber;
	}



	public void setVueNumber(int vueNumber) {
		this.vueNumber = vueNumber;
	}



	public float getPrice() {
		return price;
	}



	public void setPrice(float price) {
		this.price = price;
	}



	public String getImg() {
		return img;
	}



	public void setImg(String img) {
		this.img = img;
	}



	public boolean isDisponibilité() {
		return disponibilité;
	}



	public void setDisponibilité(boolean disponibilité) {
		this.disponibilité = disponibilité;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}
	
	

}
