package com.example.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.example.entity.Announce;
import com.example.entity.Pictures;
import com.example.entity.Reservation;
import com.example.entity.User;
import com.example.repository.AnnonceRepository;
import com.example.repository.PicturesRepository;
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

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	reservationRepository rR;
	@Autowired
	PicturesRepository pR;
	@Autowired
	UserRepository uR;
	@Autowired
	AnnonceRepository aR;
	@Autowired
	rentRepository eR;
	private static float prixtotla;
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
		else if(checkIn.before(checkOut))
		{

			Reservation reservation= rR.validation(id, username, idannounce)	;
			reservation.setCheckIn(checkIn);
			reservation.setCheckOut(checkOut);
			reservation.setValide(false); //par defaut non valide
			rR.save(reservation);
		}
	}
	@Override
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
		LocalDateTime localDate = LocalDateTime.now();
		Date d = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
		Reservation reservation = new Reservation();
		User user = uR.findByUserName(username).get();
		Announce announce = aR.findById(idannounce).get();
		String l="location/mois";
		if((announce.isDisponibilité()==false)&&(announce.getType().equals(l)))
		{
			reservation.setUser(user);
			reservation.setAnnounce(announce);
			reservation.setDateres(localDate);
			reservation.getAnnounce().setDisponibilité(true);
			reservation.setValide(false); //par defaut non valide
			rR.save(reservation);		
		}else 
		{
			throw new Exception("Erreur cette annonce"+idannounce)	;
		}

	}

	@Override
	public List<Reservation> findReservationByUser(String username) 
	{
		return  rR.findReservationByUser(username);
	}
	@Override
	public List<Reservation> findReservationByUsernow(String username) 
	{
		Date now = new Date();
		return rR.findReservationByUsercourante(username, now);
	}
	@Override
	public List<Reservation> findReservationByUserhis(String username) 
	{
		Date now = new Date();
		return rR.findReservationByUserhis(username, now);
	}
	@Override
	public List<Announce> findannonceby(String type,String region ,int chambremin, float priceMin, float priceMax,Date checkIn,Date checkOut ) 
	{		
		return aR.findbyDatepc(chambremin,priceMin, priceMax,checkIn, checkOut,type,region);
	}
	@Override
	public List<Announce> annoncebyregion(String type,String region) 
	{
		return aR.findReservationByRegion(type,region);
	}
	@Override
	public List<Announce> annoncebyprice(String type,String region,float priceMin, float priceMax) 
	{
		return aR.findReservationBynbyprice(priceMin, priceMax,type,region);
	}
	@Override
	public List<Announce> findannoncebynbrchambre(String type,String region ,int chambremin) 
	{		
		return aR.findReservationBynbrChambre(chambremin,type,region);
	}
	@Override
	public List<Announce> findannoncebydate(String type,String region ,Date checkIn,Date checkOut) throws Exception 
	{		
		return aR.findbyDate(checkIn, checkOut,type,region);
	}
	@Override
	public List<Announce> findannoncebyd(String type,String region , float priceMin, float priceMax,Date checkIn,Date checkOut)
	{		
		return aR.findbyDatep(priceMin, priceMax,checkIn, checkOut,type,region);
	}


	@Override
	public List<Announce> findannoncebyprixnbrchambre(String type, String region, float priceMin, float priceMax,
			int chambremin) {
		return aR.findReservationBynbypricec(chambremin,priceMin, priceMax,type,region);

	}


	@Override
	public List<Announce> findannoncebydatec(String type, String region, Date checkIn, Date checkOut, int chambremin) {
		return aR.findbyDatec(chambremin,checkIn, checkOut,type,region);
	}


	@Override
	public List<Announce> findannoncebyid(int idannounce) {
		return aR.findAnnounceByid(idannounce);
	}
	@Override
	public float calculprix(int id,Date checkIn, Date checkOut){
		Announce r = aR.findById(id).get();

		long diff=checkOut.getTime()-checkIn.getTime();
		float res=(diff/(1000*60*60*24));
		float prixtotla=(res*r.getPrice());
		return prixtotla;
	}
	@Override
	public List<Pictures> findAllimages(int announceid) {

		
		return pR.findImageAdd(announceid);
		

	}


	@Override
	@Scheduled(cron="0 * * * * ?")
	public void valider() 
	{
		List<User> user =uR.findAll();
		List<Reservation> res= rR.findReservationD();
		SimpleMailMessage msg = new SimpleMailMessage();
		SimpleMailMessage msg2 = new SimpleMailMessage();
		
		for (int i=0;i<res.size();i++) 
		{
			for (int j=0;j<user.size();j++) 
			{

				if((res.get(i).getUser().getRent().getUser().getId()==user.get(j).getId())&&(res.get(i).isValide()==false))
				{
					float prixtot=calculprix(res.get(i).getAnnounce().getId(),res.get(i).getCheckIn(),res.get(i).getCheckOut());
					res.get(i).setValide(true);
					rR.save(res.get(i));
					msg.setTo(res.get(i).getUser().getMailAddress());
					msg.setSubject("Validate");
					msg.setText("Hello "+res.get(i).getUser().getName()+" your reservation from "+res.get(i).getCheckIn()+" to "+res.get(i).getCheckOut() +" has been validated and the price of your stay "+prixtot+"Tdn ,you have to contact the announcer at least tow days before your checkIn ");
					javaMailSender.send(msg);
					msg2.setTo(res.get(i).getAnnounce().getUser().getMailAddress());
					msg2.setSubject("Announce rent");
					msg2.setText("Hello "+res.get(i).getAnnounce().getUser().getName()+" your Announce has been rent from "+res.get(i).getCheckIn()+" to "+res.get(i).getCheckOut()+"\n client email adress:"+res.get(i).getUser().getRent().getUser().getMailAddress()+"\n client phonenumber:"+res.get(i).getUser().getRent().getUser().getPhoneNumber());
					javaMailSender.send(msg2);
				}
				else if (res.get(i).isValide()==false){

					msg.setTo(res.get(i).getUser().getMailAddress());
					msg.setSubject("Validation");
					msg.setText("Hello "+res.get(i).getUser().getName()+" you have to upload your docs to validate your reservation");
					javaMailSender.send(msg);
				}


			}
		}
	}
	@Override
	@Scheduled(cron="2 * * * * ?")
	public void supprimer() 
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
	}
	@Override
	public float estimate(String type,String region,Date checkIn, Date checkOut,int nbrchambre){

		long diff=checkOut.getTime()-checkIn.getTime();
		float res=(diff/(1000*60*60*24));
if(type.equals("location"))
{
		if((region.equals("Ariana"))&&(nbrchambre<=2)){
			float prixtotla=(res*25);
			return prixtotla;
			}
		else if((region.equals("Ariana"))&&(nbrchambre>=3)&&(nbrchambre<5)){
			float prixtotla=(res*45);
			return prixtotla;
			}
		else if((region.equals("Ariana"))&&(nbrchambre>=5)){
			float prixtotla=(res*75);
			return prixtotla;
			}
		else if ((region.equals("Tunis"))&&(nbrchambre<=2)){
			float prixtotla=(res*30);
			return prixtotla;
			}
		else if((region.equals("Tunis"))&&(nbrchambre>=3)&&(nbrchambre<5)){
			float prixtotla=(res*50);
			return prixtotla;
			}
		else if((region.equals("Tunis"))&&(nbrchambre>=5)){
			float prixtotla=(res*80);
			return prixtotla;
			}
		else if ((region.equals("Nabeul"))&&(nbrchambre<=2)){
			float prixtotla=(res*30);
			return prixtotla;
			}
		else if((region.equals("Nabeul"))&&(nbrchambre>=3)&&(nbrchambre<5)){
			float prixtotla=(res*60);
			return prixtotla;
			}
		else if((region.equals("Nabeul"))&&(nbrchambre>=5)){
			float prixtotla=(res*100);
			return prixtotla;
			}
		else if ((region.equals("Sousse"))&&(nbrchambre<=2)){
			float prixtotla=(res*30);
			return prixtotla;
			}
		else if((region.equals("Sousse"))&&(nbrchambre>=3)&&(nbrchambre<5)){
			float prixtotla=(res*60);
			return prixtotla;
			}
		else if((region.equals("Sousse"))&&(nbrchambre>=5)){
			float prixtotla=(res*100);
			return prixtotla;
			}
		else if ((region.equals("Sfax"))&&(nbrchambre<=2)){
			float prixtotla=(res*20);
			return prixtotla;
			}
		else if((region.equals("Sfax"))&&(nbrchambre>=3)&&(nbrchambre<5)){
			float prixtotla=(res*50);
			return prixtotla;
			}
		else if((region.equals("Sfax"))&&(nbrchambre>=5)){
			float prixtotla=(res*80);
			return prixtotla;
			}
		else if ((region.equals("Tozeur"))&&(nbrchambre<=2)){
			float prixtotla=(res*20);
			return prixtotla;
			}
		else if((region.equals("Tozeur"))&&(nbrchambre>=3)&&(nbrchambre<5)){
			float prixtotla=(res*40);
			return prixtotla;
			}
		else if((region.equals("Tozeur"))&&(nbrchambre>=5)){
			float prixtotla=(res*70);
			return prixtotla;
			}
		else if ((region.equals("Bizert"))&&(nbrchambre<=2)){
			float prixtotla=(res*20);
			return prixtotla;
			}
		else if((region.equals("Bizert"))&&(nbrchambre>=3)&&(nbrchambre<5)){
			float prixtotla=(res*40);
			return prixtotla;
			}
		else if((region.equals("Bizert"))&&(nbrchambre>=5)){
			float prixtotla=(res*70);
			return prixtotla;
			}
		else if ((region.equals("Monastir"))&&(nbrchambre<=2)){
			float prixtotla=(res*25);
			return prixtotla;
			}
		else if((region.equals("Monastir"))&&(nbrchambre>=3)&&(nbrchambre<5)){
			float prixtotla=(res*50);
			return prixtotla;
			}
		else if((region.equals("Monastir"))&&(nbrchambre>=5)){
			float prixtotla=(res*75);
			return prixtotla;
			}
		else if ((region.equals("Tabarka"))&&(nbrchambre<=2)){
			float prixtotla=(res*20);
			return prixtotla;
			}
		else if((region.equals("Tabarka"))&&(nbrchambre>=3)&&(nbrchambre<5)){
			float prixtotla=(res*40);
			return prixtotla;
			}
		else if((region.equals("Tabarka"))&&(nbrchambre>=5)){
			float prixtotla=(res*60);
			return prixtotla;
			}
	}
		 return prixtotla;

	}
	@Override
	public float estimatelocm(String type,String region,int nbrchambre){

		if(type.equals("locationparmois"))
		{
				if((region.equals("Ariana"))&&(nbrchambre<=2)){
					float prixtotla=500;
					return prixtotla;
					}
				else if((region.equals("Ariana"))&&(nbrchambre>=3)&&(nbrchambre<5)){
					float prixtotla=800;
					return prixtotla;
					}
				else if((region.equals("Ariana"))&&(nbrchambre>=5)){
					float prixtotla=1300;
					return prixtotla;
					}
				else if ((region.equals("Tunis"))&&(nbrchambre<=2)){
					float prixtotla=550;
					return prixtotla;
					}
				else if((region.equals("Tunis"))&&(nbrchambre>=3)&&(nbrchambre<5)){
					float prixtotla=850;
					return prixtotla;
					}
				else if((region.equals("Tunis"))&&(nbrchambre>=5)){
					float prixtotla=1500;
					return prixtotla;
					}
				else if ((region.equals("Nabeul"))&&(nbrchambre<=2)){
					float prixtotla=400;
					return prixtotla;
					}
				else if((region.equals("Nabeul"))&&(nbrchambre>=3)&&(nbrchambre<5)){
					float prixtotla=700;
					return prixtotla;
					}
				else if((region.equals("Nabeul"))&&(nbrchambre>=5)){
					float prixtotla=1200;
					return prixtotla;
					}
				else if ((region.equals("Sousse"))&&(nbrchambre<=2)){
					float prixtotla=400;
					return prixtotla;
					}
				else if((region.equals("Sousse"))&&(nbrchambre>=3)&&(nbrchambre<5)){
					float prixtotla=700;
					return prixtotla;
					}
				else if((region.equals("Sousse"))&&(nbrchambre>=5)){
					float prixtotla=1500;
					return prixtotla;
					}
				else if ((region.equals("Sfax"))&&(nbrchambre<=2)){
					float prixtotla=450;
					return prixtotla;
					}
				else if((region.equals("Sfax"))&&(nbrchambre>=3)&&(nbrchambre<5)){
					float prixtotla=700;
					return prixtotla;
					}
				else if((region.equals("Sfax"))&&(nbrchambre>=5)){
					float prixtotla=1300;
					return prixtotla;
					}
				else if ((region.equals("Tozeur"))&&(nbrchambre<=2)){
					float prixtotla=350;
					return prixtotla;
					}
				else if((region.equals("Tozeur"))&&(nbrchambre>=3)&&(nbrchambre<5)){
					float prixtotla=600;
					return prixtotla;
					}
				else if((region.equals("Tozeur"))&&(nbrchambre>=5)){
					float prixtotla=1100;
					return prixtotla;
					}
				else if ((region.equals("Bizert"))&&(nbrchambre<=2)){
					float prixtotla=450;
					return prixtotla;
					}
				else if((region.equals("Bizert"))&&(nbrchambre>=3)&&(nbrchambre<5)){
					float prixtotla=700;
					return prixtotla;
					}
				else if((region.equals("Bizert"))&&(nbrchambre>=5)){
					float prixtotla=1200;
					return prixtotla;
					}
				else if ((region.equals("Monastir"))&&(nbrchambre<=2)){
					float prixtotla=400;
					return prixtotla;
					}
				else if((region.equals("Monastir"))&&(nbrchambre>=3)&&(nbrchambre<5)){
					float prixtotla=750;
					return prixtotla;
					}
				else if((region.equals("Monastir"))&&(nbrchambre>=5)){
					float prixtotla=1200;
					return prixtotla;
					}
				else if ((region.equals("Tabarka"))&&(nbrchambre<=2)){
					float prixtotla=400;
					return prixtotla;
					}
				else if((region.equals("Tabarka"))&&(nbrchambre>=3)&&(nbrchambre<5)){
					float prixtotla=650;
					return prixtotla;
					}
				else if((region.equals("Tabarka"))&&(nbrchambre>=5)){
					float prixtotla=1200;
					return prixtotla;
					}
			}
		return prixtotla;
	}
	

	 
	
}
