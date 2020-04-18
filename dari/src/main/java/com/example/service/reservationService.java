package com.example.service;

import java.util.Date;
import java.util.List;

import com.example.entity.Announce;
import com.example.entity.Reservation;





public interface reservationService {
	

	public void ajouterReservation(int idannounce, int iduser, Date checkIn, Date checkOut) throws Exception;
	public void modifierReservation(int id,int idannounce, int iduser, Date checkIn, Date checkOut) throws Exception;
	public List<Reservation> findAllReservation();
	//public void deleteReservationById(int id);
	public void deleteReservationByUser(int iduser);
	public void ajouterReservationLong(int idannounce, int iduser) throws Exception;
	//public void validerReservationLong(int id ,int idannounce, int iduser,int announcer); 
	public List<Reservation> findReservationByUser(int iduser);
	public List<Announce> findannonceby(String type,String region,int chambremax, int chambremin, float priceMin, float priceMax);
	public List<Announce> annoncebyregion(String type,String region);
	public List<Announce> annoncebyprice(String type, String region, float priceMin, float priceMax);
	public List<Announce> findannoncebynbrchambre(String type, String region, int chambremin, int chambremax);
	public void valider();
	public List<Announce> findannoncebydate(String type, String region, Date checkIn, Date checkOut) throws Exception;
	public void supprimer();




}
