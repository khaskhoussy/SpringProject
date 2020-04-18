package com.example.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Rent;
import com.example.service.rentService;



@RestController
public class RentController {
    @Autowired
    private rentService rentservice;

    @PostMapping("/addrent/{user_id}/{cin}/{LetterOfCommitment}/{proofOfPayment}/{garantor}")
    @ResponseBody
    public void addrent(@PathVariable("user_id")int user_id,@PathVariable("cin")String cin,@PathVariable("LetterOfCommitment")String LetterOfCommitment,@PathVariable("proofOfPayment")String proofOfPayment,@PathVariable("garantor")String garantor) throws Exception {
        rentservice.saveRent(user_id, cin, LetterOfCommitment, proofOfPayment, garantor);
        		
    	
    }


    @RequestMapping(value="/allrent")
    public List<Rent> getAllUser() {
        return rentservice.getAllRentList();
    }
    @PutMapping(path ="/update/{id}/{user_id}/{cin}/{LetterOfCommitment}/{proofOfPayment}/{garantor}")
    @ResponseBody
    public void update(@PathVariable("id")int id,@PathVariable("user_id")int user_id,@PathVariable("cin")String cin,@PathVariable("LetterOfCommitment")String LetterOfCommitment,@PathVariable("proofOfPayment")String proofOfPayment,@PathVariable("garantor")String garantor ){
         rentservice.updateRent(id,user_id, cin, LetterOfCommitment, proofOfPayment, garantor);
    	
    }



   @DeleteMapping("/delete/{id}")

    public void delete(@PathVariable("id")int id){
        rentservice.deleteRentById(id);

    }



 

    }