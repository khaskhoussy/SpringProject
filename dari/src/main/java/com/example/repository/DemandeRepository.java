package com.example.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.example.entity.*;

public interface DemandeRepository extends JpaRepositoryImplementation<Integer, Demande> {

}
