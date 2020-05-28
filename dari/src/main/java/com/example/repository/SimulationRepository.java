package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Simulation;


@Repository
public interface SimulationRepository extends JpaRepository<Simulation, Integer> {

	
}
