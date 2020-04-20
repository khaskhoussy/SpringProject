package com.example.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.example.entity.Announce;
import com.example.entity.Reservation;
import com.example.entity.User;
import com.example.repository.AnnonceRepository;
import com.example.repository.UserRepository;
import com.example.repository.rentRepository;
import com.example.repository.reservationRepository;



@EnableScheduling
@Service
public class reservationServiceImpl implements reservationService 
{
	@Value("${jobs.enabled:true}")
	private boolean isEnabled;

	private static final Logger l=LogManager.getLogger(rentServiceImpl.class);

	@Autowired
	reservationRepository rR;
	@Autowired
	UserRepository uR;
	@Autowired
	AnnonceRepository aR;
	@Autowired
	rentRepository eR;

	@Override
	public void ajouterReservation(int idannounce,String username, Date checkIn, Date checkOut) throws Exception 
	{
		Reservation reservation = new Reservation();
		User user = uR.findByUserName(username).get();
		Announce announce = aR.findById(idannounce).get();
		String l="location";
		Reservation val= rR.ajout(idannounce, checkIn, checkOut) ;
		if(announce.getReservation().contains(val))
		{
			throw new Exception("Erreur cette annonce"+idannounce+" est deja reservée du "+checkIn+" au "+checkOut)	;
		}
		else if((announce.getType().equals(l))&&(checkIn.before(checkOut)))
		{
			LocalDateTime localDate = LocalDateTime.now();
			reservation.setUser(user);
			reservation.setAnnounce(announce);
			reservation.setCheckIn(checkIn);
			reservation.setCheckOut(checkOut);
			reservation.setValide(false); //par defaut non valide
			reservation.setDateres(localDate);
			rR.save(reservation);		
		}
		else if(checkOut.before(checkIn))
		{
			throw new Exception("Erreur date debut apres date fin ")	;
		}
		else 
		{
			throw new Exception("Erreur<")	;
		}

	}


	@Override
	public List<Reservation> findAllReservation() 
	{
		List<Reservation> reservation=(List<Reservation>) rR.findAll();
		for(Reservation Reservation:reservation)
		{
			l.info("Reservation ++ :" +Reservation);
		}
		return reservation;	
	}


	@Override
	public void modifierReservation(int id ,int idannounce,String username, Date checkIn, Date checkOut) throws Exception 
	{
		User user = uR.findByUserName(username).get();
		Announce announce = aR.findById(idannounce).get();
		Reservation reservationF= rR.modif(idannounce,username,checkIn, checkOut) ;	
		String l="location";
		Reservation reservatio= rR.validation(id, username, idannounce);
		if(announce.getReservation().contains(reservationF))
		{
			throw new Exception("Erreur cette annonce"+idannounce+" est deja reservée du "+checkIn+" au "+checkOut)	;
		}
		else if((checkIn.before(checkOut))&&(announce.getReservation().contains(reservatio))&&(announce.getType().equals(l)))
		{

			Reservation reservation= rR.validation(id, username, idannounce)	;
			reservation.setUser(user);
			reservation.setAnnounce(announce);
			reservation.setCheckIn(checkIn);
			reservation.setCheckOut(checkOut);
			reservation.setValide(false); //par defaut non valide
			rR.save(reservation);
		}
	}
	public void deleteReservation(int id) 
	{	
		rR.deletebyid(id);
	}
	@Override
	public void deleteReservationById(int id,String username) 
	{	
		User user = uR.findByUserName(username).get();
		Reservation r = rR.findById(id).get();
		if(user.getId()==r.getUser().getId())
		{
			rR.deletebyid(id);
		}
	}
	@Override
	public void ajouterReservationLong(int idannounce, String username) throws Exception 
	{
		Reservation reservation = new Reservation();
		User user = uR.findByUserName(username).get();
		Announce announce = aR.findById(idannounce).get();
		String l="LocationLongDuree";
		if((announce.isDisponibilité()==false)&&(announce.getType().equals(l)))
		{
			reservation.setUser(user);
			reservation.setAnnounce(announce);
			reservation.getAnnounce().setDisponibilité(true);
			reservation.setValide(false); //par defaut non valide
			rR.save(reservation);		
		}

	}
	/*public void validerReservationLong(int id ,int idannounce, int iduser,int announcer) 
	{
		User user = uR.findById(iduser).get();
		Announce announce = aR.findById(idannounce).get();
		User rent = uR.findById(iduser).get();
		Reservation validateF= rR.validation(id,iduser, idannounce) ;
		int ann=announce.getUser().getId();
		int ren=rent.getRent().getUser().getId();
		String l="LocationLongDuree";
		if((announce.getReservation().contains(validateF))&&(ann==announcer)&&(ren==iduser))
			{

			if(announce.getType().equals(l))
			{
				Reservation validate=rR.valLong(id, iduser, idannounce);
				validate.setId(id);
				validate.setUser(user);
				validate.setAnnounce(announce);
				validate.setValide(true); //par defaut non valide
				rR.save(validate);
				announce.setDisponibilité(false);
				aR.save(announce);
			}
			}
	}
	 */
	@Override
	public List<Reservation> findReservationByUser(String username) 
	{
		return  rR.findReservationByUser(username);
	}

	@Override
	public List<Announce> findannonceby(String type,String region ,int chambremin,int chambremax, float priceMin, float priceMax ) 
	{		
		return aR.findReservationBynbrChambreandprice(type,region,chambremin, chambremax, priceMin, priceMax);
	}
	@Override
	public List<Announce> annoncebyregion(String type,String region) 
	{
		return aR.findReservationByRegion(type,region);
	}
	@Override
	public List<Announce> annoncebyprice(String type,String region,float priceMin, float priceMax) 
	{
		return aR.findReservationBynbyprice(type, region, priceMin, priceMax);
	}
	@Override
	public List<Announce> findannoncebynbrchambre(String type,String region ,int chambremin,int chambremax) 
	{		
		return aR.findReservationBynbrChambre(type,region,chambremin, chambremax);
	}
	@Override
	public List<Announce> findannoncebydate(String type,String region ,Date checkIn,Date checkOut) throws Exception 
	{		
		return aR.findbyDate(type,region,checkIn, checkOut);
	}

	//@Override
	//@Scheduled(cron="*/15 * * * * ?")
	/*public void valider() 
	{
		List<User> user =uR.findAll();
		List<Reservation> res= rR.findReservationD();
		for (int i=0;i<res.size();i++) 
		{
			for (int j=0;j<user.size();j++) 
			{
				if(res.get(i).getUser().getId()==user.get(j).getId())
				{
					res.get(i).setValide(true);
					rR.save(res.get(i));
				}
			}
		}
	}*/
	@Override
	@Scheduled(cron="*/60 * * * * ?")
		public void supprimer() 
	{
		List<Reservation> res= rR.findReservationD();
		LocalDateTime now = LocalDateTime.now().plusMinutes(2);
		for (int i=0;i<res.size();i++) 
		 {
			 if(now.isAfter(res.get(i).getDateres()))
			 {

				 deleteReservation(res.get(i).getId());

			 }
		 }	 
	}
}
