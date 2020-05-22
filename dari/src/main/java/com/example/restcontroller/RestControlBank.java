package com.example.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Bank;
import com.example.entity.Offre;
import com.example.service.IBankService;



@RestController
@RequestMapping("/expert/bank")
public class RestControlBank {

	
	@Autowired
	IBankService ibankservice;
	
	//{"id":1,"adress":"BH Bank","email":"bh@bank.com","name":"Cite El Ghazela","phone":71800900}
	//http://localhost:8081/dari/servlet/ajouterBank
	@PostMapping("/ajouterBank")
	@ResponseBody
	public int ajouterBank(@RequestBody Bank bank) {
		ibankservice.ajouterBank(bank);
		return bank.getId();
	}
	
	
    // URL : http://localhost:8081/dari/servlet/getAllOffresByBank/1
    @GetMapping(value = "getAllOffresByBank/{bankid}")
    @ResponseBody
	public List<Offre> getAllOffresByBank(@PathVariable("bankid") int bankId) {

		return ibankservice.getAllOffresByBank(bankId);
	}
    
    
    // URL : http://localhost:8081/dari/servlet/deleteBankById/1
    @DeleteMapping("/deleteBankById/{idbank}") 
	@ResponseBody 
	public void deleteBankById(@PathVariable("idbank")int bankId) {
    	ibankservice.deleteBankById(bankId);
		
	}
    
    
     	// Modifier email : http://localhost:8081/dari/servlet/updateBankById/1/newemail
	@PutMapping(value = "/updateBankById/{id}") 
	@ResponseBody
	public void updateBankById(@RequestBody Bank bank, @PathVariable("id") int bankId) {
		ibankservice.updateBankById(bank, bankId);
		
	}
     
     
}
