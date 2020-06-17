package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Duration;
import com.example.entity.Offre;



@Repository
public interface OffreRepository extends JpaRepository<Offre, Integer>{
   /* @Query("Select "
			+ "DISTINCT off from Offre off "
			+ "join off.offers bnk "
			+ "where bnk=:entreprise")
    public List<Offre> getAllOffersByBankRepo(@Param("bank") Bank bank);*/
	
	/*
	 	@Query("select DISTINCT o from Offre o join o.bank b  where b.id=:bankId")
	public List<Bank> getAllOffresByBankRepo(@Param("bankId")int bankId);  
	 */

	/*@Query("select o.duration from Offre o join o.bank b where b.id=:bankId")
    public List<String> FindByInterest(@Param("bankId")int bankId);*/
	
	
	@Query("select o.interest_rate from Offre o join o.bank b where b.id=:bankId and o.duration=:durationparam")
    public int FindByInterest(@Param("bankId")int bankId,@Param("durationparam")Duration durationparam);
	
	
	@Query("select o.id from Offre o join o.bank b where b.id=:bankId and o.duration=:durationparam")
    public int offreid(@Param("bankId")int bankId,@Param("durationparam")Duration durationparam);
	
	@Query("select o.duration from Offre o join o.bank b where b.id=:bankId")
    public List <Duration>offreid1(@Param("bankId")int bankId);
	
	@Query("select o.self_finance_rate from Offre o join o.bank b where b.id=:bankId and o.duration=:durationparam")
    public int findBySelf_finance_rate(@Param("bankId")int bankId,@Param("durationparam")Duration durationparam);
	
	
}
