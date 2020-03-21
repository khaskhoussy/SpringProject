package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.*;

public interface ReservationRepository extends JpaRepository<Integer, Reservation>{

}
