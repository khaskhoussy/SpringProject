package com.example.repository;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.entity.Rent;



@Repository
public interface rentRepository  extends CrudRepository<Rent,Integer>{

	@Query(value="Select r from Rent r where r.user.userName= :userName")
    public Rent user(@Param("userName")String userName);
	
	@Query(value="Select r from Rent r where r.id= :id")
    public Rent update(@Param("id")int id);
	
	@Query(value="Select r from Rent r where r.user.id= :id")
    public Rent userid (@Param("id")int id);
	
	@Query(value="Select r from Rent r where r.user.userName= :userName")
    public List<Rent> userdoc(@Param("userName")String userName);
}


