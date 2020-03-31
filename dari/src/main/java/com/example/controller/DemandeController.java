package com.example.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Demande;
import com.example.entity.Relation;
import com.example.service.DemandeService;
import com.example.service.RelationService;

@RestController
@RequestMapping("/user/Demande")
@EnableScheduling
public class DemandeController {

	@Autowired
	DemandeService demandeService;
	@Autowired 
	RelationService relationService;
	
	@RequestMapping(method=RequestMethod.POST,value="/{username}")
	public void sendDemand(@PathVariable String username)
	{
		demandeService.SendDemande(username, Home.connectedUser);
		
	}
	@RequestMapping
	public List<Demande> myRecivedDemandes()
	{
		return demandeService.myReivedDemandes(Home.connectedUser);
	}
	@RequestMapping(value="/sended")
	public List<Demande> mySendedDemandes()
	{
		return demandeService.MySendedDemandes(Home.connectedUser);
	}
	@RequestMapping(method=RequestMethod.PUT,value="/{id}")
	public void demandeStatus(@RequestBody Demande demande,@PathVariable int  id)
	{
		
		demandeService.changeStatus(demande, id);
		demande = demandeService.allDemande().stream().filter(d->d.getId()==id).findFirst().get();
		
		if(demande.getEtat().equals("Accepted"))			
		
		relationService.addRelation(new Relation(false,demande.getId_Sender(),demande.getUser_Reciver().getId()));
		else
			demandeService.DeleteDemande(demande);			
	}
	final String temp = "PT1M";
	
	@Scheduled(initialDelay=1000L,fixedDelayString= temp)
	public void test()
	{
		List<Demande> AcceptedDemande =demandeService.allDemande().stream().filter(d->d.getEtat().
				equals("Accepted")).collect(Collectors.toList());
		List<Demande> deletDemandeList =AcceptedDemande.stream().filter(d-> deleteAcceptedDemands(d.getDate())).collect(Collectors.toList());
		deletDemandeList.forEach(d-> demandeService.DeleteDemande(d));
				
	}
	
	public boolean deleteAcceptedDemands(Date demandeDate) //this function check if demandeDate get over 7 Days(i considred February have 30 Day )
	{													  //in java number of months is 11 starts from 0 and if you wanna put a year you need too substracted from 1900
	
		Date nowDate = new Date();
		int test30 =nowDate.getDate()+30 - demandeDate.getDate();
		int test31=nowDate.getDate()+31 - demandeDate.getDate();
		float testPair=nowDate.getMonth()%2;
		System.out.println(nowDate.getDate());
		if(nowDate.getMonth() == demandeDate.getMonth())
			{if ((nowDate.getDate() - demandeDate.getDate()) > 7)
				return true;
			else 
				return false;
			}
		if(nowDate.getMonth()<7 && nowDate.getMonth()>0 )
		{	
			
			if(testPair==0)
				{if ( test30 > 7)
					
					return true;
				}
				else 
					if (test31 > 7)
					return true;
							
		}
		else if(nowDate.getMonth()>7)
		{ 
			if(testPair==0)
			{
			if(test31>7)
				return true ;
			}
			else
				if  (test30 > 7)
				return true;
			
		}
		else if(nowDate.getMonth()==7)
			{
				if(test31>7)			
				return true ;
			}
		else if(nowDate.getMonth()==0)
		{
			if(test31>7)		
			return true ;
		}			
				return false ;	
	}
	
}
