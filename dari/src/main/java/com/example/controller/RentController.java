package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@RequestMapping("/user/rent")
public class RentController {
    @Autowired
    private rentService rentservice;

    @RequestMapping(method = RequestMethod.POST,value="/addrent/{cin}/{LetterOfCommitment}/{proofOfPayment}/{garantor}")
    @ResponseBody
    public void addrent(@PathVariable("cin")String cin,@PathVariable("LetterOfCommitment")String LetterOfCommitment,@PathVariable("proofOfPayment")String proofOfPayment,@PathVariable("garantor")String garantor,HttpServletResponse response,HttpServletRequest request) throws Exception {
        rentservice.saveRent(Home.connectedUser, cin, LetterOfCommitment, proofOfPayment, garantor);
        		
    	
    }


    @RequestMapping(value="/allrent")
    public List<Rent> getAllUser() {
        return rentservice.getAllRentList();
    }
    @PutMapping(path ="/update/{cin}/{LetterOfCommitment}/{proofOfPayment}/{garantor}")
    @ResponseBody
    public void update(@PathVariable("cin")String cin,@PathVariable("LetterOfCommitment")String LetterOfCommitment,@PathVariable("proofOfPayment")String proofOfPayment,@PathVariable("garantor")String garantor ,HttpServletResponse response,HttpServletRequest request){
         rentservice.updateRent(Home.connectedUser, cin, LetterOfCommitment, proofOfPayment, garantor);
    	
    }



   @DeleteMapping("/delete/{id}")

    public void delete(@PathVariable("id")int id){
        rentservice.deleteRentById(id);

    }



 

    }