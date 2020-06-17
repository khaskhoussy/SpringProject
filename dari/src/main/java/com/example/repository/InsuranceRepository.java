package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Insurance;
import com.example.entity.Insurance_Offer;
import com.example.entity.Offre;


@Repository
public interface InsuranceRepository extends CrudRepository<Insurance, Integer> {
	List<Insurance> findByName(String name);
	
	
	
	@Query(value = "select name from insurance i", nativeQuery = true)
	public List<String> findInsuranceNames();
	
	
	
	@Query("select DISTINCT o from Insurance_Offer o join o.insurance b where b.id=:insuranceId")
	public List<Insurance_Offer> getAllOffresByInsuranceRepo(@Param("insuranceId")int insuranceId); 
}
