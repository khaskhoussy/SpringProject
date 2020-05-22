package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Simulation;
import com.example.entity.SimulationPK;

public interface SimulationPKRepository extends JpaRepository<Simulation, SimulationPK>{

}
