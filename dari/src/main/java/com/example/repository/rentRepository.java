package com.example.repository;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.entity.Rent;



@Repository
public interface rentRepository  extends CrudRepository<Rent,Integer>{

	@Query(value="Select r from Rent r where r.user.id= :userId")
    public Rent user(@Param("userId")int userId);
	
	@Query(value="Select r from Rent r where r.id= :id  and r.user.id= :userId")
    public Rent update(@Param("id")int id,@Param("userId")int userId);
	
}


