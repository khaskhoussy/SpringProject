package com.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Demande;
import com.example.entity.User;
import com.example.repository.DemandeRepository;
import com.example.repository.UserRepository;

@Service
public class DemandeService {

	@Autowired
	DemandeRepository demandeRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;

	
	public void SendDemande(String username,String connectedUserName)
	{
		
		User reciver = new User();
		reciver = userRepository.findByUserName(username).get();
		User sender = new User();
		sender=userRepository.findByUserName(connectedUserName).get();
		int id_sender ;
		id_sender=sender.getId();
	    Demande demande = new Demande(false,id_sender,"NULL",reciver);
	   demandeRepository.save(demande);		
	}
	public List<Demande> allDemande()
	{
		List<Demande> demandes = new ArrayList<>();
		demandeRepository.findAll().forEach(demandes::add);
		return demandes;
	}
	public List<Demande> myReivedDemandes(String userName)
	{
		
		return allDemande().stream().filter(d->d.getUser_Reciver().getUserName().equals(userName)).collect(Collectors.toList());		
		 
	}
	public List<Demande> MySendedDemandes(String userName)
	{	
		return  allDemande().stream().filter(d->d.getId()==userRepository.findByUserName(userName).get().getId()).collect(Collectors.toList());		
	}
	
	public void  changeStatus(Demande demande , int id )
	{
		Demande selected = allDemande().stream().filter(d->d.getId()==id).findFirst().get();
		
		selected.setEtat(demande.getEtat());
		selected.setDate(new Date());		
		demandeRepository.save(selected);		
	}
	public void DeleteDemande(Demande demande )
	{
		demandeRepository.delete(demande);
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
