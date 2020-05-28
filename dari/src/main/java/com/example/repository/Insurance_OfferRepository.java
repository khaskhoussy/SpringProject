package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Category;
import com.example.entity.Insurance_Offer;


@Repository
public interface Insurance_OfferRepository extends JpaRepository<Insurance_Offer, Integer> {

	
	@Query("select o.category from Insurance_Offer o join o.insurance b where b.id=:idInsurance")
    public List <Category>insuranceOfferid(@Param("idInsurance")int idInsurance);
	
	
	@Query("select o.interest_category from Insurance_Offer o join o.insurance b where b.id=:insuranceId and o.category=:categoryparam")
    public float FindByInterestCategory(@Param("insuranceId")int insuranceId,@Param("categoryparam")Category categoryparam);

	@Query("select o.interest_goods from Insurance_Offer o join o.insurance b where b.id=:insuranceId and o.category=:categoryparam")
    public float FindByInterestGoods(@Param("insuranceId")int insuranceId,@Param("categoryparam")Category categoryparam);
	
	@Query("select o.id from Insurance_Offer o join o.insurance b where b.id=:idInsurance and o.category=:categoryparam")
    public int insuranceOfferid(@Param("idInsurance")int idInsurance,@Param("categoryparam")Category categoryparam);
	
	@Query("select o.category from Insurance_Offer o join o.insurance b where b.id=:insuranceId")
    public List <Category>insuranceoffreid1(@Param("insuranceId")int insuranceId);
}
