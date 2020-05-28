package com.example.restcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.example.entity.Insurance_Offer;
import com.example.service.IInsuranceOfferService;
import com.example.service.IInsuranceService;



@RestController
@RequestMapping("/expert_insurance/insurance_offer")
public class RestControlInsuranceOffer {

	@Autowired
	IInsuranceService iinsuranceservice;
	@Autowired
	IInsuranceOfferService iinsuranceofferservice;
	
	//{"id":1,"description":"type d'interet 1 ","interest_rate":5,"duration":"ONE_TO_FIVE"}
	//http://localhost:8081/dari/servlet/ajouterInsuranceOffer
/*{
    "category": "House",
    "description": "yyyy",
    "interest_category": 5,
    "interest_goods": 1000
}*/
	@PostMapping("/ajouterInsuranceOffer/{idInsurance}")
	@ResponseBody
	public int ajouterOffre(@RequestBody Insurance_Offer insuranceOffer,@PathVariable("idInsurance")int idInsurance,HttpServletResponse response,HttpServletRequest request) {
		iinsuranceofferservice.ajouterInsuranceOffer(insuranceOffer,Home.connectedUser);
		return insuranceOffer.getId();
	}
	
 	// Modifier email : http://localhost:8081/dari/servlet/updateInsuranceOfferById/1
	/*
	 * {
    "category": "House",
    "description": "yyyyuyy",
    "interest_category": 50,
    "interest_goods": 1
}
	 */
    @PutMapping(value = "/updateInsuranceOfferById/{idOffreInsurance}") 
    @ResponseBody
    public void updateInsuranceOfferById(@RequestBody Insurance_Offer insuranceOffer,@PathVariable("idOffreInsurance")int idOffreInsurance,HttpServletResponse response,HttpServletRequest request) {
    	iinsuranceofferservice.updateInsuranceOfferById(insuranceOffer, idOffreInsurance,Home.connectedUser);
	
}
    
    // http://localhost:8081/dari/servlet/getInsuranceOfferById/1
    @GetMapping(value = "getInsuranceOfferById/{idOfferInsurance}")
    @ResponseBody
	public Insurance_Offer getInsuranceOfferById(@PathVariable("idOfferInsurance") int idOfferInsurance) {
    	Insurance_Offer insuranceOffer1 = new Insurance_Offer();
//		return iinsuranceofferservice.getInsuranceOfferById(idOfferInsurance);
		return iinsuranceofferservice.getInsuranceOfferById(idOfferInsurance);
	}
    
    
    // http://localhost:8081/dari/servlet/deleteOffreById/1
    @DeleteMapping("/deleteInsuranceOfferById/{idOfferInsurance}") 
	@ResponseBody 
	public void deleteInsuranceOfferById(@PathVariable("idOfferInsurance")int idOfferInsurance,HttpServletResponse response,HttpServletRequest request) {
    	iinsuranceofferservice.deleteInsuranceOfferById(idOfferInsurance,Home.connectedUser);
		
	}
    
    
}
