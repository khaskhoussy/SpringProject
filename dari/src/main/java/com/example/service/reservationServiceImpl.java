package com.example.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@EnableScheduling
@Service
public class reservationServiceImpl implements reservationService 
{
	@Value("${jobs.enabled:true}")
	private boolean isEnabled;

	private static final Logger l=LogManager.getLogger(rentServiceImpl.class);
	//@Autowired
	//private JavaMailSender javaMailSender;
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
		LocalDateTime localDate = LocalDateTime.now();
		Date d = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
		String l="location";
		Reservation val= rR.ajout(idannounce, checkIn, checkOut) ;
		if(announce.getReservation().contains(val))
		{
			throw new Exception("Erreur cette annonce"+idannounce+" est deja reservée du "+checkIn+" au "+checkOut)	;
		}
		else if((announce.getType().equals(l))&&(checkIn.before(checkOut)&&(checkIn.after(d))))
		{

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

	@Override
	public List<Reservation> findReservationByUser(String username) 
	{
		return  rR.findReservationByUser(username);
	}

	@Override
	public List<Announce> findannonceby(String type,String region ,int chambremin, float priceMin, float priceMax,Date checkIn,Date checkOut ) 
	{		
		List<Announce> r= aR.findAll();
		r= aR.findReservationByRegion(type,region);
		r= aR.findReservationBynbrChambre(chambremin);
		r= aR.findReservationBynbyprice(priceMin, priceMax);
		r = aR.findbyDate(checkIn, checkOut);
		return r;	
		}
	@Override
	public List<Announce> annoncebyregion(String type,String region) 
	{
		return aR.findReservationByRegion(type,region);
	}
	@Override
	public List<Announce> annoncebyprice(String type,String region,float priceMin, float priceMax) 
	{
		List<Announce> r= aR.findAll();
		r= aR.findReservationBynbyprice(priceMin, priceMax);
		return r;	
		}
	@Override
	public List<Announce> findannoncebynbrchambre(String type,String region ,int chambremin) 
	{		
		List<Announce> r= aR.findAll();
		r= aR.findReservationBynbrChambre(chambremin);
		return r;
		}
	@Override
	public List<Announce> findannoncebydate(String type,String region ,Date checkIn,Date checkOut) throws Exception 
	{		
		List<Announce> r= aR.findAll();
		r = aR.findbyDate(checkIn, checkOut);
		return r;
	}
	@Override
	public List<Announce> findannonceby(String type,String region ,Date checkIn,Date checkOut,float priceMin, float priceMax) throws Exception 
	{		
		List<Announce> r= aR.findAll();
		r = aR.findbyDate(checkIn, checkOut);
		return r;
	}


	@Override
	public List<Announce> findannoncebyprixnbrchambre(String type, String region, float priceMin, float priceMax,
			int chambremin) {
		List<Announce> r= aR.findAll();
		r= aR.findReservationByRegion(type,region);
		r= aR.findReservationBynbrChambre(chambremin);
		r= aR.findReservationBynbyprice(priceMin, priceMax);
		return r;	

	}


	@Override
	public List<Announce> findannoncebydatec(String type, String region, Date checkIn, Date checkOut, int chambremin) {
		List<Announce> r= aR.findAll();
		r= aR.findReservationByRegion(type,region);
		r= aR.findReservationBynbrChambre(chambremin);
		return r;	
	}

	//@Override
	//@Scheduled(cron="*/15 * * * * ?")
	/*public void valider() 
	{
		List<User> user =uR.findAll();
		List<Reservation> res= rR.findReservationD();
		SimpleMailMessage msg = new SimpleMailMessage();

		for (int i=0;i<res.size();i++) 
		{
			for (int j=0;j<user.size();j++) 
			{

				if((res.get(i).getUser().getRent().getUser().getId()==user.get(j).getId())&&(res.get(i).isValide()==false))
				{
					res.get(i).setValide(true);
					rR.save(res.get(i));
					msg.setTo(res.get(i).getUser().getMailAddress());
					msg.setSubject("Validate");
					msg.setText("Hello "+res.get(i).getUser().getName()+" your reservation from "+res.get(i).getCheckIn()+" to "+res.get(i).getCheckOut() +" has been validated");
					javaMailSender.send(msg);
				}
				else if (res.get(i).isValide()==false){

					msg.setTo(res.get(i).getUser().getMailAddress());
					msg.setSubject("Validation");
					msg.setText("Hello "+res.get(i).getUser().getName()+" you have to upload your docs to validate your reservation");
					javaMailSender.send(msg);
				}
		

			}
		}
	}*/
	//@Override
	//@Scheduled(cron="*/60 * * * * ?")
	/*public void supprimer() 
	{
		List<Reservation> res= rR.findReservationD();
		LocalDateTime now = LocalDateTime.now().plusMinutes(2);
		SimpleMailMessage msg = new SimpleMailMessage();

		for (int i=0;i<res.size();i++) 
		{
			if(now.isAfter(res.get(i).getDateres()))
			{

				deleteReservation(res.get(i).getId());
				msg.setTo(res.get(i).getUser().getMailAddress());
				msg.setSubject("Validation");
				msg.setText("Hello "+res.get(i).getUser().getName()+"Your reservation has been deleted");
				javaMailSender.send(msg);
			}
		}	 
	}*/
}
