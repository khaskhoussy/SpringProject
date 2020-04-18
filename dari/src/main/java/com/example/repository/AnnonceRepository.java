package com.example.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Announce;



@Repository
public interface AnnonceRepository extends JpaRepository<Announce, Integer>{

	
	@Query(value="Select r from Announce r  where r.type= :type and r.regions= :region and r.nbrchambre>= :NbrchambreMin and r.nbrchambre<= :NbrchambreMax and "
			+ "r.price >= :priceMin and r.price<= :priceMax")
    public List<Announce> findReservationBynbrChambreandprice(@Param("type")String type ,@Param("region")String region ,@Param("NbrchambreMin")int NbrchambreMin,
    		@Param("NbrchambreMax")int NbrchambreMax,@Param("priceMin")float priceMin,@Param("priceMax")float priceMax);
	
	@Query(value="Select r from Announce r  where r.type= :type and r.regions= :region and r.nbrchambre >= :NbrchambreMin and r.nbrchambre <= :NbrchambreMax and "
			+ "r.price >= :priceMin and r.price <= :priceMax")
    public List<Announce> findReservationBynbrChambre(@Param("type")String type ,@Param("region")String region ,@Param("NbrchambreMin")int NbrchambreMin,
    		@Param("NbrchambreMax")int NbrchambreMax);

	@Query(value="Select r from Announce r  where r.type= :type and r.regions= :region and "
			+ "r.price >= :priceMin and r.price<= :priceMax")
    public List<Announce> findReservationBynbyprice(@Param("type")String type ,@Param("region")String region 
    		,@Param("priceMin")float priceMin,@Param("priceMax")float priceMax);

	@Query(value="Select r from Announce r  where r.type= :type and  r.regions= :region")
	public List<Announce> findReservationByRegion(@Param("type")String type ,@Param("region")String region );

	@Query(value="Select a from Announce a where a.type= :type and  a.regions= :region and a.id Not IN (Select r.announce from Reservation r where  ((r.checkIn<= :checkIn and r.checkOut>= :checkIn) or (r.checkIn<= :checkOut and r.checkOut>= :checkOut) or ((r.checkIn<= :checkIn and r.checkOut>= :checkIn) and (r.checkIn<= :checkOut and r.checkOut>= :checkOut))))")
	public List<Announce> findbyDate(@Param("type")String type ,@Param("region")String region ,@Param("checkIn")Date checkIn, @Param("checkOut")Date checkOut);

	


	}
		