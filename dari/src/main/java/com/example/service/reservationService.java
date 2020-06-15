package com.example.service;

import java.util.Date;
import java.util.List;

import com.example.entity.Announce;
import com.example.entity.Reservation;





public interface reservationService {
	

	public void ajouterReservation(int idannounce,String username, Date checkIn, Date checkOut) throws Exception;
	public void modifierReservation(int id,int idannounce, String username, Date checkIn, Date checkOut) throws Exception;
	public List<Reservation> findAllReservation();
	public void deleteReservationById(int id, String username);
	public void ajouterReservationLong(int idannounce, String username) throws Exception;
	public List<Reservation> findReservationByUser(String username);
	//public void supprimer();
	//public void valider();
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public List<Announce> findannoncebyid(int idannounce);
	public List<Announce> findannonceby(String type,String region ,int chambremin, float priceMin, float priceMax,Date checkIn,Date checkOut);
	public List<Announce> findannoncebyd(String type,String region , float priceMin, float priceMax,Date checkIn,Date checkOut);
	public List<Announce> annoncebyregion(String type,String region);
	public List<Announce> annoncebyprice(String type, String region, float priceMin, float priceMax);
	public List<Announce> findannoncebynbrchambre(String type, String region, int chambremin);
	public List<Announce> findannoncebydate(String type, String region, Date checkIn, Date checkOut) throws Exception;
	public List<Announce> findannoncebyprixnbrchambre(String type, String region, float priceMin, float priceMax,int chambremin);
	public List<Announce> findannoncebydatec(String type, String region, Date checkIn, Date checkOut, int chambremin);




}
