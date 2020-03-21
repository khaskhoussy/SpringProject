package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.*;

public interface PictureRepository extends JpaRepository<Integer, Pictures> {

}
