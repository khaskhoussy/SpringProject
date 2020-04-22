package com.example.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Reservation;



@Repository
public interface reservationRepository  extends CrudRepository<Reservation,Integer>{


	
	@Query(value="Select r from Reservation r where r.user.userName= :userName and r.announce.id= :idannounce and r.id= :id ")
    public Reservation validation(@Param("id")int id,@Param("userName")String userName, @Param("idannounce")int idannounce);
	
	@Query(value="Select r from Reservation r where r.announce.id= :idannounce and ((r.checkIn<= :checkIn and r.checkOut>= :checkIn) or (r.checkIn<= :checkOut and r.checkOut>= :checkOut) or ((r.checkIn<= :checkIn and r.checkOut>= :checkIn) and (r.checkIn<= :checkOut and r.checkOut>= :checkOut)))")
	public Reservation ajout( @Param("idannounce")int idannounce, @Param("checkIn")Date checkIn, @Param("checkOut")Date checkOut);
	
	@Query(value="Select r from Reservation r where r.user.userName!= :userName and r.announce.id= :idannounce and ((r.checkIn<= :checkIn and r.checkOut>= :checkIn) or (r.checkIn<= :checkOut and r.checkOut>= :checkOut) or ((r.checkIn<= :checkIn and r.checkOut>= :checkIn) and (r.checkIn<= :checkOut and r.checkOut>= :checkOut)))")
	public Reservation modif( @Param("idannounce")int idannounce,@Param("userName")String username,@Param("checkIn")Date checkIn, @Param("checkOut")Date checkOut);
	
	@Query(value="Select r from Reservation r where r.user.userName= :userName and r.announce.id= :idannounce and r.id= :id  and r.checkIn= :checkIn and r.checkOut= :checkOut ")
	public Reservation val(@Param("id")int id,@Param("userName")String userName, @Param("idannounce")int idannounce, @Param("checkIn")Date checkIn, @Param("checkOut")Date checkOut);
	
	
	@Query(value="Select r from Reservation r where r.user.userName= :userName and r.announce.id= :idannounce and r.id= :id   ")
	public Reservation valLong(@Param("id")int id,@Param("userName")String userName, @Param("idannounce")int idannounce);
	
	
	@Query(value="Select r from Reservation r  where r.announce.id= :idannounce and r.checkIn= :checkIn and r.checkOut= :checkOut ")
    public Reservation verif(@Param("idannounce")int idannounce, @Param("checkIn")Date checkIn, @Param("checkOut")Date checkOut);

	@Query(value="Select r from Reservation r  where r.user.userName= :userName ")
    public List<Reservation> findReservationByUser(@Param("userName")String userName);
	
	@Query(value="Select r from Reservation r  where r.user.id=r.user.rent.id ")
    public List<Reservation> findReservation();
	
	@Query(value="Select r from Reservation r  where r.isValide=0 ")
    public List<Reservation> findReservationD();
	
	@Modifying
    @Transactional
	@Query(value="DELETE from Reservation r  where  r.id= :id")
	public void deletebyid(@Param("id")int id);
	
	
	
	@Query(value="Select r from Reservation r  where  r.user.userName= :userName")
	public List<Reservation> deleteByUser(@Param("userName")String userName);
	
	
	
}
