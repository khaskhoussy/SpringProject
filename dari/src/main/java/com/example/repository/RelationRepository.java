package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.*;


public interface RelationRepository extends JpaRepository<Integer, Relation> {

}
