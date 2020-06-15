package com.example.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Reservation implements Serializable  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	@JsonBackReference
	@ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "iduser", referencedColumnName = "id")
	private User user;
	

	//@JsonBackReference
    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "idannounce", referencedColumnName = "id")
	private Announce announce;
	private LocalDateTime dateres;

	public LocalDateTime getDateres() {
		return dateres;
	}



	public void setDateres(LocalDateTime dateres) {
		this.dateres = dateres;
	}
	private boolean isValide;
	@Temporal(TemporalType.DATE)
	private Date checkIn;
	@Temporal(TemporalType.DATE)
	private Date checkOut;

	public boolean isisValide() {
		return isValide;
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
	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}


	public Announce getAnnounce() {
		return announce;
	}
	public void setAnnounce(Announce announce) {
		this.announce = announce;
	}
	public boolean isValide() {
		return isValide;
	}


	public void setValide(boolean isValide) {
		this.isValide = isValide;
	}


	public Reservation() {
		super();
	}
	public Reservation(int id, User user, Announce announce, boolean isValide, Date checkIn, Date checkOut,LocalDateTime dateres) {
		super();
		this.id = id;
		this.user = user;
		this.announce = announce;
		this.isValide = isValide;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.dateres = dateres;
	}
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", user=" + user + ", announce=" + announce + ", isValide=" + isValide
				+ ", checkIn=" + checkIn + ", checkOut=" + checkOut + "]";
	}

}
