package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Simulation_Favoris;



@Repository
public interface Simulation_FavorisRepository extends JpaRepository<Simulation_Favoris, Integer>  {
	//
	//@Query("select o.interest_rate from Offre o join o.bank b where b.id=:bankId and o.duration=:durationparam")
	//@Query(value = "select sim.refund_period from simulation_favoris sim where sim.refund_period=:refund_period", nativeQuery = true)
	@Query(value = "select sim.bank_name from simulation_favoris sim where sim.refund_period BETWEEN :refund_period-4 AND :refund_period+4 "
			+ "AND sim.credit_amount BETWEEN :credit_amount-credit_amount*0.2 AND :credit_amount+credit_amount*0.2 AND sim.monthly_capacity BETWEEN :monthly_capacity-monthly_capacity*0.2 AND :monthly_capacity+monthly_capacity*0.2", nativeQuery = true)
    public List <String> FindByData(@Param("refund_period")int refund_period,@Param("credit_amount")float credit_amount,@Param("monthly_capacity")float monthly_capacity);
	

	
	//@Query(value = "select count id_offre from simulation_favoris where id_offre=idOffre", nativeQuery = true)
	@Query(value = "select simulation_favoris.bank_name,simulation_favoris.duration,count(simulation_favoris.id_offre) from simulation_favoris "
			
			+ "where (select bank_name from expert ex inner join user u on u.expert_id= ex.id where u.user_name =:userName) =simulation_favoris.bank_name  "
			+ "group by simulation_favoris.duration ", nativeQuery = true)
    public Object [] FindByOffer(@Param("userName")String userName);
	
	//@Query("select DISTINCT o from Offre o join o.bank b  where b.id=:bankId")
	@Query("select DISTINCT o from Simulation_Favoris o join o.user u  where u.id=:idUser")
	public List<Simulation_Favoris> getSimulationsById(@Param("idUser")int idUser);
	
	
	
	
	
	
//	@Query(value = "select u.mail_address from user u "
//			+ "where (select bank_name from expert ex inner join user u on u.expert_id= ex.id where u.id =:idUser) =simulation_favoris.bank_name  ", nativeQuery = true)
//    public String FindExpertEmail(@Param("name")String name);
	
	
	//public void FindByData(@Param("creditAmount")float creditAmount,@Param("refundPeriod")int refundPeriod,@Param("monthlyCapacity")float monthlyCapacity);
	/*
	@Query(value = "select  ca.category_name from category ca "
			+ " inner join product p on p.category_id = ca.id  "
			+ " inner join  product_commands on p.id = product_commands.produits_id "
			+ "inner join command c on c.id = product_commands.commands_id"
			+ " inner join   users u on u.id = c.id where u.age =:age ", nativeQuery = true)
	public List<String> countSellBySize(@Param("age") int age); 
	 */
	
	
	
	/*
@Query(value = "select  ca.category_name from category ca "
			+ " inner join product p on p.category_id = ca.id  "
			+ " inner join  product_commands on p.id = product_commands.produits_id "
			+ "inner join command c on c.id = product_commands.commands_id"
			+ " inner join   users u on u.id = c.user_id where datediff(  CURDATE(),u.datenaissance)/365  BETWEEN :age-2 AND :age+2  AND c.command_date BETWEEN :d AND :d1 ", nativeQuery = true)
	public List<String> countySize(@Param("age") int age ,@Param("d") Date d,@Param("d1") Date d1 );
	 */
}
