package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.InsuranceSimulation_Favoris;
import com.example.entity.Simulation_Favoris;

@Repository
public interface Insurance_SimulationFavorisRepository extends JpaRepository<InsuranceSimulation_Favoris, Integer> {

	

	@Query(value = "select insurance_simulation_favoris.insurance_name,count(insurance_simulation_favoris.id) from insurance_simulation_favoris "
			
			+ "where (select insurance_name from expert_insurance ex inner join user u on u.expert_insurance_id= ex.id where u.user_name =:userName) =insurance_simulation_favoris.insurance_name  "
			, nativeQuery = true)
    public Object [] FindByInsuranceOffer(@Param("userName")String userName);
	
	
	@Query("select DISTINCT o from InsuranceSimulation_Favoris o join o.user u  where u.id=:idUser")
	public List<InsuranceSimulation_Favoris> getSimulationsById(@Param("idUser")int idUser);
	
	
//	@Query(value = "select user.mail_address from user "
//			
//			+ "where (select insurance_name from expert_insurance ex inner join user u on u.expert_insurance_id= ex.id where u.user_name =:userName) =insurance_simulation_favoris.insurance_name and u.id=: "
//			, nativeQuery = true)
//    public List<String> FindByInsuranceOfferEmail(@Param("userName")String userName);
	
	
}
