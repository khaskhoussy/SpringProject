package com.example.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.CommentsAnnonce;





@Repository
public interface CommentRepository extends JpaRepository<CommentsAnnonce, Integer>{
	
}
