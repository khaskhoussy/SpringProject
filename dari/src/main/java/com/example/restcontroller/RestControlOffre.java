package com.example.restcontroller;

import java.util.List;

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

import com.example.entity.Offre;
import com.example.service.IBankService;
import com.example.service.IOffreService;


@RestController
@RequestMapping("/expert/offer")
public class RestControlOffre {

	@Autowired
	IOffreService ioffreservice;
	
	@Autowired
	IBankService ibankservice;
	
	//{"id":1,"description":"type d'interet 1 ","interest_rate":5,"duration":"ONE_TO_FIVE"}
	//http://localhost:8081/dari/servlet/ajouterOffre
	@PostMapping("/ajouterOffre/{idBank}")
	@ResponseBody
	public int ajouterOffre(@RequestBody Offre offre,@PathVariable("idBank")int idBank,HttpServletResponse response,HttpServletRequest request) {
		ioffreservice.ajouterOffre(offre,Home.connectedUser);
		return offre.getId();
	}
	
	// http://localhost:8081/dari/servlet/affecterOffreABank/1/2
    @PutMapping(value = "/affecterOffreABank/{offreid}/{bankid}") 
	public void affecterOffreABank(@PathVariable("offreid")int offId, @PathVariable("bankid")int bankId) {
    	ioffreservice.affecterOffreABank(offId, bankId);
	}
    
    
 	// Modifier email : http://localhost:8081/dari/servlet/updateOffreById/1
    @PutMapping(value = "/updateOffreById/{id}") 
    @ResponseBody
    public void updateOffreById(@RequestBody Offre offre, @PathVariable("id") int offreId,HttpServletResponse response,HttpServletRequest request) {
    	ioffreservice.updateOffreById(offre, offreId,Home.connectedUser);
	
}
    // http://localhost:8081/dari/servlet/getOffreById/1
    @GetMapping(value = "getOffreById/{OffreId}")
    @ResponseBody
	public Offre getOffreById(@PathVariable("OffreId") int OffreId) {
		Offre offre1 = new Offre();
		return ioffreservice.getOffreById(OffreId);
	}
    
 // http://localhost:8081/dari/servlet/deleteOffreById/1
    @DeleteMapping("/deleteOffreById/{offreId}") 
	@ResponseBody 
	public void deleteOffreById(@PathVariable("offreId")int offreId,HttpServletResponse response,HttpServletRequest request) {
    	ioffreservice.deleteOffreById(offreId,Home.connectedUser);
		
	}
    /*
	 // URL : http://localhost:8081/dari/servlet/getAllOffres
	@GetMapping(value = "/getAllOffres")
   @ResponseBody
	public List<Offre> getAllOffres() {
		
		return ioffreservice.getAllOffres();
	}
	*/
    
    
    // URL : http://localhost:8081/SpringMVC/servlet/getAllEmployeByEntreprise/1
    /*@GetMapping(value = "getAllOffersByBank/{idBank")
    @ResponseBody
	public List<Offre> getAllOffresByBank(@PathVariable("idBank") int idBank) {
    	Bank bank=ibankservice.getBankById(idBank);
		return ioffreservice.getAllOffresByBank(bank);
	}*/
}
