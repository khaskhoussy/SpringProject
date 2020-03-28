package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Demande;
import com.example.service.DemandeService;

@RestController
@RequestMapping("/user/Demande")
public class DemandeController {

	@Autowired
	DemandeService demandeService;
	
	@RequestMapping(method=RequestMethod.POST,value="/{username}")
	public void sendDemand(@PathVariable String username)
	{
		demandeService.SendDemande(username, Home.connectedUser);
		
	}
	@RequestMapping
	public List<Demande> myReivedDemandes()
	{
		return demandeService.myReivedDemandes(Home.connectedUser);
	}
	@RequestMapping(value="/sended")
	public List<Demande> mySendedDemandes()
	{
		return demandeService.MySendedDemandes(Home.connectedUser);
	}
	
	
	
}
