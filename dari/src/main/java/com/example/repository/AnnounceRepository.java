package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;



import com.example.entity.Announce;


public interface AnnounceRepository extends JpaRepository<Integer, Announce> {

}
