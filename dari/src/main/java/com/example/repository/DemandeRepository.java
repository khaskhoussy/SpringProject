package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Demande;
import com.example.entity.User;

public interface DemandeRepository extends JpaRepository<Demande, Integer> {

	List<Demande> findDemandeByUserReciver(User user);
}
