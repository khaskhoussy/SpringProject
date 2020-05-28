package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.Choice;



public interface OptionRepository extends CrudRepository<Choice, Long> {
	
	void deleteByPollId(Long id);

}
