package com.example.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.entity.Choice;
import com.example.entity.Poll;
import com.example.repository.PollRepository;
import com.example.repository.UserRepository;
import com.example.service.PollService;

//import tn.esprit.spring.entities.Choix;
//import tn.esprit.spring.entities.Poll;
//import tn.esprit.spring.repository.ChoixRepository;
//import tn.esprit.spring.repository.UserRepository;
//import tn.esprit.spring.service.PollService;

@Controller(value = "PollJsf")
@ELBeanName(value = "PollJsf")
//@Join(path = "/user/vote", to = "/pages/user/vote.jsf")
@Join(path = "/poll", to = "/pages/user/poll.jsf")
public class PollJsf {
	
	
	
	
	  @Autowired
	   PollService pollService;
//	  @Autowired
//	   SigninJsf SigninJsf;
	  
	  @Autowired
	  UserRepository UserRepository;
//	  @Autowired
//	  ChoixRepository ChoixRepository;
	  
	  @Autowired
	  PollRepository PollRepository;
	  private String paaram;
	  private String sujet ;
	  





	public String getSujet() {
		return sujet;
	}




	public void setSujet(String sujet) {
		this.sujet = sujet;
	}


	List <Choice> ch = new ArrayList<Choice>();
		
	public List<Choice> getCh() {
			return ch;
		}


		public void setCh(List<Choice> ch) {
			this.ch = ch;
		}

	public String getPaaram() {
		return paaram;
	}


	public void setPaaram(String paaram) {
		
		System.out.println("paaaaaaaaaaa " + paaram );
		if (paaram!=null || paaram!="")
			
		{
			System.out.println("sssssssssssssssssssssssssssssssssssssssss");
			ch.add(new Choice(paaram,0l) );
			
			
			System.out.println("raaaaaaaaaaaaaaaaaaaaaaaaaaaak lhna");
			
			
			
		}
//		this.paaram = this.paaram+"," + paaram;
//		System.out.println("aaaaaaaaaaaaaaassssss   " + this.paaram);
	}




	public String  Addpoll()
	  {
		  
		
//		Poll p = new Poll();
//		
//		p.setTitle(sujet);
//		p.setEndDate(new Date(2020,1,1));
//	p.setVisible(false);
//	
//	p.setOptions(ch);
		
		
		
		
	
	pollService.savePoll(new Poll(ch, sujet, new Date(2020,1,1), false),HomeController.connectedUser.getUserName() );
		
	
	
	
	System.out.println("aaaaaaaaaaaaaaaaa");
		 
		  
		  System.out.println("ccccccccccccccccccccccc " + ch );
		  
		  paaram = "";
		  return"";
	  }
	
/////////////////////////////////////////vote////////////////////////////////////
	
	
	
//	private Long id_poll ;
//	private String title;
//	
//
//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public Long getId_poll() {
//		return id_poll;
//	}
//
//	public void setId_poll(Long id_poll) {
//		this.id_poll = id_poll;
//	}
//
//	
//	
//	public Poll get() {
//		System.err.println("heyy");
//        return pollService.getPollById(1L);
//        
//    }
	

	private long optionselect;






	public long getOptionselect() {
		return optionselect;
	}




	public void setOptionselect(long optionselect) {
		System.out.println("ssssssssssssss "+optionselect);
		if(optionselect != 0l)
		this.optionselect = optionselect;
	}	
	
	
	
	private String selectoption;

	public String getSelectoption() {
		return selectoption;
	}

	public void setSelectoption(String selectoption) {
		System.out.println("ssssssssssssss "+selectoption);
		
		if(selectoption.equals("")==false)		
		this.selectoption = selectoption;
	}
	

	
	public List<Choice> choix(long id){

		return PollRepository.findById(id).get().getOptions();
				
	}
	
	
	public void voote(long idpoll) throws Exception{
		
		
		System.out.println("rrrrrrttttttyyyyyuuuuuuuuuuuu "+ this.getOptionselect());
		
		
		String retour=  pollService.vote(idpoll, optionselect, HomeController.connectedUser.getUserName());
	  
	  
		FacesMessage facesMessage =

				new FacesMessage(retour);
		FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);
		
		
		optionselect=0l;
		selectoption = "";
	
	}	

	public List<Poll> polls(){
				
		return	PollRepository.findAll();
			
			
		}
						
		public List<Poll> mespoll()
		{
			
//			return pollService.getAllForUser(  UserRepository.findById(SigninJsf.idusercurrent()).get().getUsername() ) ;			
			return pollService.getAllForUser(HomeController.connectedUser.getUserName()) ;
		}
	
		private String title;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
		
}
