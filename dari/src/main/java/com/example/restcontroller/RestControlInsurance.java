package com.example.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Insurance;
import com.example.service.IInsuranceService;


@RestController
@RequestMapping("/expert_insurance/insurance")
public class RestControlInsurance {

	
	
	@Autowired
	IInsuranceService iinsuranceservice;
	
	
//	{"adress":"eear", "description":"aaeraer", "email":"Khaled.kallel@gat.tn", "interest_age":4, "interest_firesafety":4, "name":"azeaze","phone":58885541, "interest_robbery":8, "interest_waterDamage":5}
	//http://localhost:8081/dari/servlet/ajouterInsurance
	@PostMapping("/ajouterInsurance")
	@ResponseBody
	public int ajouterInsurance(@RequestBody Insurance insurance) {
		iinsuranceservice.ajouterInsurance(insurance);
		return insurance.getId();
	}
	
	
    // URL : http://localhost:8081/dari/servlet/deleteInsuranceById/1
    @DeleteMapping("/deleteInsuranceById/{idinsurance}") 
	@ResponseBody 
	public void deleteInsuranceById(@PathVariable("idinsurance")int insuranceId) {
    	iinsuranceservice.deleteInsuranceById(insuranceId);
		
	}
    
 	// Modifier email : http://localhost:8081/dari/servlet/updateInsuranceById/1
@PutMapping(value = "/updateInsuranceById/{id}") 
@ResponseBody
public void updateBankById(@RequestBody Insurance insurance, @PathVariable("id") int insuranceId) {
	iinsuranceservice.updateInsuranceById(insurance, insuranceId);
	
}
	
}
