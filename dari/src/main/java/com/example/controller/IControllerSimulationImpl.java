package com.example.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.SlideEndEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Bank;
import com.example.entity.Duration;
import com.example.entity.Offre;
import com.example.entity.Simulation;
import com.example.entity.SimulationPK;
import com.example.entity.Simulation_Favoris;
import com.example.entity.User;
import com.example.repository.BankRepository;
import com.example.repository.OffreRepository;
import com.example.repository.SimulationPKRepository;
import com.example.repository.SimulationRepository;
import com.example.repository.Simulation_FavorisRepository;
import com.example.restcontroller.Home;
import com.example.service.ISimulationService;

@Scope(value = "session")
@Controller(value = "simulationController")
@ELBeanName(value = "simulationController")
@Join(path = "/simulationChoice", to = "/pages/user/simulationChoice.jsf")
//@Join(path = "/user/findByData", to = "/pages/user/findByData.jsf")
@Named
@RequestScoped
public class IControllerSimulationImpl {

	@Autowired
	ISimulationService isimulationservice;

	@Autowired
	BankRepository bankrepository;

	@Autowired
	OffreRepository OffreRepository;

	@Autowired

	SimulationRepository simulationRepository;

	 @Autowired
	 SimulationPKRepository simulationPKRepository;

	 @Autowired
	 IControllerInsuranceSimulationImpl ics;
	 
		@Autowired
		Simulation_FavorisRepository Simulation_FavorisRepository;
	 
	 
	private float creditAmount;
	private int refundPeriod;
	private float monthlyCapacity;
	private String name;
	private float self_finance;
	private Simulation_Favoris simulation;
	private String bankName;
	private float document_fees;
	private String duration;
	private String name2;
	
	
	
	
	
	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	private float monthlyPayback;
	private float total;
	private String selfFinanceComparaison;
	private int total_due_date;
	private float rest;
	private float rest1;
	private float rest2;
	private float rest3;
	private String monthlyPaybackCompraison;
	private String priceCompraison;
	private float monthlyPayback1;
	private float total1;
	private float document_fees1;
	private String documentFeesCompraison;
	private String selfFinanceBanksComparaison;
	
	
	
	
	private float total_interest;
	private float total_payback;
	private float self_finance_bank;
	
	private String restResult;
	private String restResult2;
	private float total_interest1;
	private float total_payback1;
	private String total_interestComparaison;
	private String totalPaybackComparaison;
	private float self_finance_bank1;
	
	// public String Simuler() {
	// String navigateTo ="null";
	// //if (authenticatedUser==null || !loggedIn) return "/login.xhtml";
	// iSimulationService.ajoutSimulation(creditAmount, refundPeriod, name,
	// monthlyCapacity, self_finance);
	// //return navigateTo;
	// return navigateTo;
	// }

	public float getSelf_finance_bank1() {
		return self_finance_bank1;
	}

	public void setSelf_finance_bank1(float self_finance_bank1) {
		this.self_finance_bank1 = self_finance_bank1;
	}

	public String getTotalPaybackComparaison() {
		return totalPaybackComparaison;
	}

	public void setTotalPaybackComparaison(String totalPaybackComparaison) {
		this.totalPaybackComparaison = totalPaybackComparaison;
	}

	public float getTotal_payback1() {
		return total_payback1;
	}

	public String getTotal_interestComparaison() {
		return total_interestComparaison;
	}

	public void setTotal_interestComparaison(String total_interestComparaison) {
		this.total_interestComparaison = total_interestComparaison;
	}

	public void setTotal_payback1(float total_payback1) {
		this.total_payback1 = total_payback1;
	}

	public float getTotal_interest1() {
		return total_interest1;
	}

	public void setTotal_interest1(float total_interest1) {
		this.total_interest1 = total_interest1;
	}

	public float getRest3() {
		return rest3;
	}

	public void setRest3(float rest3) {
		this.rest3 = rest3;
	}

	public String getRestResult2() {
		return restResult2;
	}

	public void setRestResult2(String restResult2) {
		this.restResult2 = restResult2;
	}

	public float getRest2() {
		return rest2;
	}

	public void setRest2(float rest2) {
		this.rest2 = rest2;
	}

	public String getRestResult() {
		return restResult;
	}

	public void setRestResult(String restResult) {
		this.restResult = restResult;
	}

	public float getSelf_finance_bank() {
		return self_finance_bank;
	}

	public void setSelf_finance_bank(float self_finance_bank) {
		this.self_finance_bank = self_finance_bank;
	}

	public float getTotal_payback() {
		return total_payback;
	}

	public void setTotal_payback(float total_payback) {
		this.total_payback = total_payback;
	}

	public float getTotal_interest() {
		return total_interest;
	}

	public void setTotal_interest(float total_interest) {
		this.total_interest = total_interest;
	}

	public String getSelfFinanceBanksComparaison() {
		return selfFinanceBanksComparaison;
	}

	public void setSelfFinanceBanksComparaison(String selfFinanceBanksComparaison) {
		this.selfFinanceBanksComparaison = selfFinanceBanksComparaison;
	}

	public String getDocumentFeesCompraison() {
		return documentFeesCompraison;
	}

	public void setDocumentFeesCompraison(String documentFeesCompraison) {
		this.documentFeesCompraison = documentFeesCompraison;
	}

	public float getDocument_fees1() {
		return document_fees1;
	}

	public void setDocument_fees1(float document_fees1) {
		this.document_fees1 = document_fees1;
	}

	public float getTotal1() {
		return total1;
	}

	public void setTotal1(float total1) {
		this.total1 = total1;
	}

	public float getMonthlyPayback1() {
		return monthlyPayback1;
	}

	public void setMonthlyPayback1(float monthlyPayback1) {
		this.monthlyPayback1 = monthlyPayback1;
	}

	public String getPriceCompraison() {
		return priceCompraison;
	}

	public void setPriceCompraison(String priceCompraison) {
		this.priceCompraison = priceCompraison;
	}

	public String getMonthlyPaybackCompraison() {
		return monthlyPaybackCompraison;
	}

	public void setMonthlyPaybackCompraison(String monthlyPaybackCompraison) {
		this.monthlyPaybackCompraison = monthlyPaybackCompraison;
	}

	public float getRest1() {
		return rest1;
	}

	public void setRest1(float rest1) {
		this.rest1 = rest1;
	}

	public float getRest() {
		return rest;
	}

	public void setRest(float rest) {
		this.rest = rest;
	}

	public int getTotal_due_date() {
		return total_due_date;
	}

	public void setTotal_due_date(int total_due_date) {
		this.total_due_date = total_due_date;
	}

	public String getSelfFinanceComparaison() {
		return selfFinanceComparaison;
	}

	public void setSelfFinanceComparaison(String selfFinanceComparaison) {
		this.selfFinanceComparaison = selfFinanceComparaison;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getMonthlyPayback() {
		return monthlyPayback;
	}

	public void setMonthlyPayback(float monthlyPayback) {
		this.monthlyPayback = monthlyPayback;
	}

	public float getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(float creditAmount) {
		this.creditAmount = creditAmount;
	}

	public int getRefundPeriod() {
		return refundPeriod;
	}

	public void setRefundPeriod(int refundPeriod) {
		this.refundPeriod = refundPeriod;
	}

	public float getMonthlyCapacity() {
		return monthlyCapacity;
	}

	public void setMonthlyCapacity(float monthlyCapacity) {
		this.monthlyCapacity = monthlyCapacity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getSelf_finance() {
		return self_finance;
	}

	public void setSelf_finance(float self_finance) {
		this.self_finance = self_finance;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public float getDocument_fees() {
		return document_fees;
	}

	public void setDocument_fees(float document_fees) {
		this.document_fees = document_fees;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	Logger logger = LoggerFactory.getLogger(Profile.class);
//	 private User connectedUser ;
	 
	public String Simuler() {
//		System.err.println(creditAmount);
//		System.err.println(refundPeriod);
//		System.err.println(monthlyCapacity);
//		System.err.println(name);
//		System.err.println(self_finance);
		simulationRepository.deleteAllInBatch();
		Duration durations = null;

		Duration durationparam = null;
		int interest_self;
		if (refundPeriod <= 5) {
			durationparam = durations.ONE_TO_FIVE;

		}

		else if (refundPeriod > 5 && refundPeriod <= 10) {
			durationparam = durations.FIVE_TO_TEN;
		}

		else if (refundPeriod > 10) {
			durationparam = durations.TEN_PLUS;
		}

		Bank bank = new Bank();
		Offre offre = new Offre();
		for (int i = 0; i < bankrepository.findByName(name).size(); i++) {
			bank = bankrepository.findByName(name).get(i);
		}

		OffreRepository.FindByInterest(bank.getId(), durationparam);
		OffreRepository.findBySelf_finance_rate(bank.getId(), durationparam);
		System.err.println(bank.getId());
		
		System.err.println("erzerezrzr");
//		System.err.println(Home.connectedUser);
		isimulationservice.ajoutSimulation(creditAmount, refundPeriod, name,
				OffreRepository.FindByInterest(bank.getId(), durationparam), HomeController.connectedUser.getUserName(),
				OffreRepository.offreid(bank.getId(), durationparam), monthlyCapacity, self_finance);
		isimulationservice.selfFinanceComparaison(self_finance, refundPeriod, creditAmount, name,
				OffreRepository.findBySelf_finance_rate(bank.getId(), durationparam));

		
		float c =(float)(OffreRepository.FindByInterest(bank.getId(), durationparam)/(float)100*1.0);
		float a = ((creditAmount*c)/12);
		float b =(float)(1-Math.pow((1+((float)c/(float)12*1.0)),-refundPeriod*12));
		
		monthlyPayback = (float)Math.abs((float)a/(float)b);
		total = monthlyPayback*12*refundPeriod;
		total_due_date = 12 * refundPeriod;

		document_fees=total*(bank.getDocument_fees_interest()/(float)100);
		System.err.println(OffreRepository.findBySelf_finance_rate(bank.getId(), durationparam));
		rest = ((OffreRepository.findBySelf_finance_rate(bank.getId(), durationparam) / (float) 100)
				* creditAmount) - self_finance;
		if (rest<0){
		rest1=0;
		restResult="You can take the loan";
		}
		if (rest>=0){
			rest1=rest;
			restResult="You're self finance amout is insufficient";
		}
		
		selfFinanceComparaison = isimulationservice.selfFinanceComparaison(self_finance, refundPeriod,
				 creditAmount, name,
				 OffreRepository.findBySelf_finance_rate(bank.getId(),
				 durationparam));
		
		total_interest = total-creditAmount;
		
		total_payback =total+document_fees;
		
		
		self_finance_bank = (OffreRepository.findBySelf_finance_rate(bank.getId(),
				 durationparam)/(float)100)*creditAmount;
		// return new Object[] { simulationRepository.findAll(), "number of due
		// dates :", total_due_date,
		// isimulationservice.selfFinanceComparaison(self_finance, refundPeriod,
		// creditAmount, name,
		// OffreRepository.findBySelf_finance_rate(bank.getId(),
		// durationparam)),
		// "you should add this amount to your self finance to get the credit
		// :", rest,
		// "you can contact the expert with email :" };
		return "simulationResult.xhtml";

	}

	
	public String Simulersave(){

		Duration durations = null;

		Duration durationparam = null;
		if (refundPeriod <= 5) {
			durationparam = durations.ONE_TO_FIVE;
		}

		else if (refundPeriod > 5 && refundPeriod <= 10) {
			durationparam = durations.FIVE_TO_TEN;
		}

		else if (refundPeriod > 10) {
			durationparam = durations.TEN_PLUS;
		}

		Bank bank = new Bank();
		Offre offre = new Offre();
		for (int i = 0; i < bankrepository.findByName(name).size(); i++) {
			bank = bankrepository.findByName(name).get(i);
		}

		OffreRepository.FindByInterest(bank.getId(), durationparam);
		System.out.print(bank.getId());


		isimulationservice.saveSimulation(creditAmount, refundPeriod, name,
				OffreRepository.FindByInterest(bank.getId(), durationparam), HomeController.connectedUser.getUserName(),
				OffreRepository.offreid(bank.getId(), durationparam), monthlyCapacity,self_finance);


		return "votre simulation est enregistré : vous pouvez consulter la liste des simulations enregistrés pour trouvez plus de détails";

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private List<Simulation_Favoris> simulations;

	public List<Simulation_Favoris> getSimulations() {
		simulations = isimulationservice.getSimulationsById(HomeController.connectedUser.getUserName());
		return simulations;
	}

	public void setSimulations(List<Simulation_Favoris> simulations) {
		this.simulations = simulations;
	}
	public String show(){
//		ics.getSimulations();
		getSimulationsById();
		return "showSimulations.xhtml";
	}
	public void setSimulation(Simulation_Favoris simulation) {
		this.simulation = simulation;
	}

	public List<Simulation_Favoris> getSimulationsById() {

		 simulations = isimulationservice.getSimulationsById(HomeController.connectedUser.getUserName());
//		simulations = isimulationservice.getAllSimulations();
		System.err.println(simulations);
		return simulations;
	}

	public void delete(int id) {
		// isimulationservice.deleteById(id_simfav);
		isimulationservice.deleteSimulation_FavorisById(id);
		// Simulation_Favorisrepository.deleteSimulation_FavorisById(id_simfav);

	}

	//// matemchich khater el simulationPK mafihech clé pirmaire
	 public List<SimulationPK> simulationss;
	
	 public List<SimulationPK> getSimulationss() {
//	 simulationss=simulationPKRepository.find;
	 return simulationss;
	 }
	
	 public void setSimulationss(List<SimulationPK> simulationss) {
	 this.simulationss = simulationss;
	 }


	 public List <String> bankNames;

	public List<String> getBankNames() {
		bankNames = bankrepository.findBankNames();
		return bankNames;
	}

	public void setBankNames(List<String> bankNames) {
		this.bankNames = bankNames;
	}
		
	
	
	public String Simuler2() {
//		System.err.println(creditAmount);
//		System.err.println(refundPeriod);
//		System.err.println(monthlyCapacity);
//		System.err.println(name);
//		System.err.println(self_finance);
//		System.err.println(name2);
		simulationRepository.deleteAllInBatch();

		Duration durations = null;
		int interest_rate;
		int interest_rate2;
		Duration durationparam = null;
		if (refundPeriod <= 5) {
			durationparam = durations.ONE_TO_FIVE;
		}

		else if (refundPeriod > 5 && refundPeriod <= 10) {
			durationparam = durations.FIVE_TO_TEN;
		}

		else if (refundPeriod > 10) {
			durationparam = durations.TEN_PLUS;
		}

		// durationparam1= durations.ONE_TO_FIVE;
		Bank bank1 = new Bank();
		Bank bank = new Bank();
		Offre offre = new Offre();
		for (int i = 0; i < bankrepository.findByName(name).size(); i++) {
			bank = bankrepository.findByName(name).get(i);
		}

		for (int i = 0; i < bankrepository.findByName(name2).size(); i++) {
			bank1 = bankrepository.findByName(name2).get(i);
		}
		/*
		 * for (int i=0;i<OffreRepository.findById(bank.getId()){
		 * offre=OffreRepository.FindByInterest(bankId, Duration); }
		 */
		OffreRepository.FindByInterest(bank.getId(), durationparam);
//		int total_due_date = 12*refundPeriod;
		rest = ((OffreRepository.findBySelf_finance_rate(bank.getId(), durationparam)/(float)100)*creditAmount)-self_finance;
		rest2 = ((OffreRepository.findBySelf_finance_rate(bank1.getId(), durationparam)/(float)100)*creditAmount)-self_finance;
		
		
		if (rest<0){
		rest1=0;
		restResult="You can take the loan from the first bank";
		}
		if (rest>=0){
			rest1=rest;
			restResult="You're self finance amout for the first bank is insufficient";
		}
		
		if (rest2<0){
		rest3=0;
		restResult2="You can take the loan from the second bank";
		}
		if (rest2>=0){
			rest3=rest2;
			restResult2="You're self finance amout for the second bank is insufficient";
		}
		
		


		isimulationservice.ajoutSimulation(creditAmount, refundPeriod, name,
				OffreRepository.FindByInterest(bank.getId(), durationparam), HomeController.connectedUser.getUserName(),
				OffreRepository.offreid(bank.getId(), durationparam), monthlyCapacity, self_finance);

		isimulationservice.ajoutSimulation(creditAmount, refundPeriod, name2,
				OffreRepository.FindByInterest(bank1.getId(), durationparam), HomeController.connectedUser.getUserName(),
				OffreRepository.offreid(bank1.getId(), durationparam), monthlyCapacity, self_finance);
		
		
		float c =(float)(OffreRepository.FindByInterest(bank.getId(), durationparam)/(float)100*1.0);
		float a = ((creditAmount*c)/12);
		float b =(float)(1-Math.pow((1+((float)c/(float)12*1.0)),-refundPeriod*12));
		
		monthlyPayback = (float)Math.abs((float)a/(float)b);
		
		float c1 =(float)(OffreRepository.FindByInterest(bank1.getId(), durationparam)/(float)100*1.0);
		float a1 = ((creditAmount*c1)/12);
		float b1 =(float)(1-Math.pow((1+((float)c1/(float)12*1.0)),-refundPeriod*12));
		
		monthlyPayback1 = (float)Math.abs((float)a1/(float)b1);
		monthlyPaybackCompraison=isimulationservice.monthlyPaybackCompraison(refundPeriod,creditAmount,name,name2,OffreRepository.FindByInterest(bank.getId(), durationparam),OffreRepository.FindByInterest(bank1.getId(), durationparam));
		
		total = monthlyPayback*12*refundPeriod;
		total1 = monthlyPayback1*12*refundPeriod;
		
		
		
		total_interest = total-creditAmount;
		total_interest1 = total1-creditAmount;
		
		if (total_interest<total_interest1){
			total_interestComparaison="The first bank adds less interest to the credit then the second bank";
		}
		else if (total_interest<total_interest1){
			total_interestComparaison="The second bank adds less interest to the credit then the first bank";
		}
		else {
			total_interestComparaison="Both Banks provide a similar interest amount";
		}
		
		total_payback =total+document_fees;
		total_payback1=total1+document_fees1;
		
		if (total_payback<total_payback1){
			totalPaybackComparaison="The first provides a better total payback offer then the second bank";
		}
		else if (total_payback<total_payback1){
			totalPaybackComparaison="The second provides a better total payback offer then the first bank";
		}
		else {
			totalPaybackComparaison="Both Banks provide a similar total payback offer";
		}
		
		
		
		self_finance_bank = (OffreRepository.findBySelf_finance_rate(bank.getId(),
				 durationparam)/(float)100)*creditAmount;
		self_finance_bank1 = (OffreRepository.findBySelf_finance_rate(bank1.getId(),
				 durationparam)/(float)100)*creditAmount;
		
		
		
		total_due_date = 12 * refundPeriod;

		document_fees=total*(bank.getDocument_fees_interest()/(float)100);
		document_fees1=total1*(bank1.getDocument_fees_interest()/(float)100);
		documentFeesCompraison =isimulationservice.documentFeesCompraison(refundPeriod, creditAmount, name, name2,
				OffreRepository.FindByInterest(bank.getId(), durationparam), 
				OffreRepository.FindByInterest(bank1.getId(), durationparam));
		
		
		priceCompraison = isimulationservice.priceCompraison(refundPeriod,creditAmount,name,name2,
				OffreRepository.FindByInterest(bank.getId(), durationparam),
				OffreRepository.FindByInterest(bank1.getId(), durationparam));
		
		selfFinanceBanksComparaison = isimulationservice.selfFinanceBanksComparaison(name, name2, 
				OffreRepository.offreid(bank.getId(), durationparam),
				OffreRepository.offreid(bank1.getId(), durationparam));

		
		return "simulation2Result.xhtml";
		


	}
	
	

	public String Simuler2save(){

		Duration durations = null;

		Duration durationparam = null;
		if (refundPeriod <= 5) {
			durationparam = durations.ONE_TO_FIVE;
		}

		else if (refundPeriod > 5 && refundPeriod <= 10) {
			durationparam = durations.FIVE_TO_TEN;
		}

		else if (refundPeriod > 10) {
			durationparam = durations.TEN_PLUS;
		}

		Bank bank = new Bank();
		Bank bank1 = new Bank();
		Offre offre = new Offre();
		for (int i = 0; i < bankrepository.findByName(name).size(); i++) {
			bank = bankrepository.findByName(name).get(i);
		}
		for (int i = 0; i < bankrepository.findByName(name2).size(); i++) {
			bank1 = bankrepository.findByName(name2).get(i);
		}
		OffreRepository.FindByInterest(bank.getId(), durationparam);
		System.out.print(bank.getId());

		isimulationservice.saveSimulation(creditAmount, refundPeriod, name,
				OffreRepository.FindByInterest(bank.getId(), durationparam), HomeController.connectedUser.getUserName(),
				OffreRepository.offreid(bank.getId(), durationparam), monthlyCapacity,self_finance);
		isimulationservice.saveSimulation(creditAmount, refundPeriod, name2,
				OffreRepository.FindByInterest(bank1.getId(), durationparam), HomeController.connectedUser.getUserName(),
				OffreRepository.offreid(bank.getId(), durationparam), monthlyCapacity,self_finance);

		return "votre simulation est enregistré : vous pouvez consulter la liste des simulations enregistrés pour trouvez plus de détails";


	}
	
	
	
	
	
	
	
	
	
	
	public String redirectToSimuler(){
		return "form2.xhtml";
	}
	public String redirectToSimuler2(){
//		return "simulation2.xhtml";
		return "form3.xhtml";
	}
	
			

	//SliderView
    private String name3;

    private float ajaxnb;
    private float ajaxnb2;
    private float ajaxnb3;

    private String name4;
    
    


	public String getName3() {
		return name3;
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}

	public String getName4() {
		return name4;
	}

	public void setName4(String name4) {
		this.name4 = name4;
	}

	public float getAjaxnb2() {
		return ajaxnb2;
	}

	public void setAjaxnb2(float ajaxnb2) {
		this.ajaxnb2 = ajaxnb2;
	}

	public float getAjaxnb3() {
		return ajaxnb3;
	}

	public void setAjaxnb3(float ajaxnb3) {
		this.ajaxnb3 = ajaxnb3;
	}

	public float getAjaxnb() {
    	
    	
    	
    	
		return ajaxnb;
	}

	public void setAjaxnb(float ajaxnb) {
		this.ajaxnb = ajaxnb;
	}

	private int number1;
    private float number2 = 0.2f;  
    private int number3;   
    private int number4;   
    private int number5;
    private int number6;  
    private int number7;
    private int number8 = 30;
    private int number9 = 80;
 
    public int getNumber1() {
        return number1;
    }
 
    public void setNumber1(int number1) {
        this.number1 = number1;
    }
 
    public float getNumber2() {
        return number2;
    }
 
    public void setNumber2(float number2) {
        this.number2 = number2;
    }
 
    public int getNumber3() {
        return number3;
    }
 
    public void setNumber3(int number3) {
        this.number3 = number3;
    }
 
    public int getNumber4() {
        return number4;
    }
 
    public void setNumber4(int number4) {
        this.number4 = number4;
    }
 
    public int getNumber5() {
        return number5;
    }
 
    public void setNumber5(int number5) {
        this.number5 = number5;
    }
 
    public int getNumber6() {
        return number6;
    }
 
    public void setNumber6(int number6) {
        this.number6 = number6;
    }
 
    public int getNumber7() {
        return number7;
    }
 
    public void setNumber7(int number7) {
        this.number7 = number7;
    }
 
    public int getNumber8() {
        return number8;
    }
 
    public void setNumber8(int number8) {
        this.number8 = number8;
    }
 
    public int getNumber9() {
        return number9;
    }
 
    public void setNumber9(int number9) {
        this.number9 = number9;
    }
    
    
    
    public void onInputChanged2(ValueChangeEvent event) {
    	System.out.println(ajaxnb);
    	System.out.println("azeaze");
    	System.out.println(event.getNewValue());
    
        FacesMessage message = new FacesMessage("Input Changed", "Value: " + event.getNewValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    } 
     
    public void onSlideEnd2(SlideEndEvent event) {
    	
    	
  // 	ajaxnb2 = UserService.convertirPionts((int) event.getValue());
    	ajaxnb2 =(float)  event.getValue();
    	ajaxnb =isimulationservice.calculerTotal(ajaxnb3, ajaxnb2,name);
   
    	System.out.println(ajaxnb);
    	System.out.println(event.getValue());
        FacesMessage message = new FacesMessage("Slide Ended", "Value: " + event.getValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    } 
 
    public void onInputChanged3(ValueChangeEvent event) {
    	System.out.println(ajaxnb);
    	System.out.println("azeaze");
    	System.out.println(event.getNewValue());
    
        FacesMessage message = new FacesMessage("Input Changed", "Value: " + event.getNewValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    } 
     
    public void onSlideEnd3(SlideEndEvent event) {
    	
    	
//    	ajaxnb = UserService.convertirPionts((int) event.getValue());
    	ajaxnb3 =(float)  event.getValue();
    	ajaxnb =isimulationservice.calculerTotal(ajaxnb3, ajaxnb2,name);
//    	
    	System.out.println(ajaxnb);
    	System.out.println(event.getValue());
        FacesMessage message = new FacesMessage("Slide Ended", "Value: " + event.getValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    } 
    
    public void valueChanged(ValueChangeEvent event) {
        //do your stuff
    	String naame =String.valueOf(event.getNewValue().toString());
    	System.out.println(naame);
    	System.out.println("kont lehné");

    	
    }
    
    public void onInputChanged4(ValueChangeEvent event) {
    	System.out.println(ajaxnb);
    	System.out.println("azeaze");
    	System.out.println(event.getNewValue());
    
        FacesMessage message = new FacesMessage("Input Changed", "Value: " + event.getNewValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    } 
     
    public void onSlideEnd4(SlideEndEvent event) {
    	
    	
  // 	ajaxnb2 = UserService.convertirPionts((int) event.getValue());
//    	ajaxnb2 =(float)  event.getValue();
//    	ajaxnb =isimulationservice.calculerTotal(ajaxnb3, ajaxnb2,name);
   
    	System.out.println(ajaxnb);
    	System.out.println(event.getValue());
        FacesMessage message = new FacesMessage("Slide Ended", "Value: " + event.getValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void onInputChanged5(ValueChangeEvent event) {
    	System.out.println(ajaxnb);
    	System.out.println("azeaze");
    	System.out.println(event.getNewValue());
    
        FacesMessage message = new FacesMessage("Input Changed", "Value: " + event.getNewValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    } 
     
    public void onSlideEnd5(SlideEndEvent event) {
    	
    	
  // 	ajaxnb2 = UserService.convertirPionts((int) event.getValue());
//    	ajaxnb2 =(float)  event.getValue();
//    	ajaxnb =isimulationservice.calculerTotal(ajaxnb3, ajaxnb2,name);
   
    	System.out.println(ajaxnb);
    	System.out.println(event.getValue());
        FacesMessage message = new FacesMessage("Slide Ended", "Value: " + event.getValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    
    
    
    
    
    
    
    
    //////////////////////////findbydata///////////////////////
    
    
//    public Map<String, Integer> FindByData(){
//    	isimulationservice.FindByData(refundPeriod, creditAmount, monthlyCapacity);
//    	
//    	return map.tostring;
//    }
    
    Map<String, Integer> map = new HashMap<>();
   
    
public Map<String, Integer> getMap() {
		return map;
	}

	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}
	List<String> list;
	
public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	List<Integer> list1;
	
public List<Integer> getList1() {
		return list1;
	}

	public void setList1(List<Integer> list1) {
		this.list1 = list1;
	}

public String Findbydataa() {
//	isimulationservice.FindByData(refundPeriod, creditAmount, monthlyCapacity);
	list = Simulation_FavorisRepository.FindByData(refundPeriod, creditAmount, monthlyCapacity);
//	List<Integer> list1;
//	Map<String, Integer> map = new HashMap<>();
	for (int i = 0; i < list.size(); i++) {
		if (map.containsKey(list.get(i)))
			map.replace(list.get(i), map.get(list.get(i)) + 1);
		// Sinon on l'ajoute
		else {
			map.put(list.get(i), 1);
		}
	}
	Set set = map.entrySet();// Converting to Set so that we can traverse
	Iterator itr = set.iterator();
	while (itr.hasNext()) {
		// Converting to Map.Entry so that we can get key and value
		// separately
		Map.Entry entry = (Map.Entry) itr.next();
		System.err.println(entry.getKey() + " " + entry.getValue());
	}
return map.toString() ;	
//	Map<String, Integer> map = new HashMap<>();
//	return map.toString();
//		return findbydataa;
	}



public String FindByData(){
	Findbydataa();
	return "showByData.xhtml";
}
}
