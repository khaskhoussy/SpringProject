package com.example.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.example.entity.*;

public interface AppointmentRepository extends JpaRepositoryImplementation<Integer, Appointment>{

}
