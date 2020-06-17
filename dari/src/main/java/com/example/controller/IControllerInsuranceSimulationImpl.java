package com.example.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entity.Category;
import com.example.entity.ChargeRequest;
import com.example.entity.Insurance;
import com.example.entity.InsuranceSimulation_Favoris;
import com.example.entity.Insurance_Offer;
import com.example.entity.Simulation;
import com.example.entity.SimulationPK;
import com.example.entity.Simulation_Favoris;
import com.example.entity.User;
import com.example.entity.ChargeRequest.Currency;
import com.example.repository.InsuranceRepository;
import com.example.repository.Insurance_OfferRepository;
import com.example.repository.Insurance_SimulationFavorisRepository;
import com.example.repository.Insurance_SimulationRepository;
import com.example.repository.UserRepository;
import com.example.restcontroller.Home;
import com.example.service.IInsuranceService;
import com.example.service.IInsuranceSimulationService;
import com.example.service.MailService;
import com.example.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@Scope(value = "session")
@Controller(value = "insuranceSimulationController")
@ELBeanName(value = "insuranceSimulationController")
//@Join(path = "/user/insuranceSimulation", to = "/pages/user/insuranceSimulation.jsf")
@Join(path = "/payement", to = "/pages/user/payement.jsf")
//@Join(path = "/user/showInsuranceSimulations", to = "/pages/user/showInsuranceSimulations.jsf")
public class IControllerInsuranceSimulationImpl {

	@Autowired
	IInsuranceService iinsuranceservice;

	@Autowired
	InsuranceRepository insurancerepository;

	@Autowired
	Insurance_OfferRepository insuranceOfferrepository;

	@Autowired
	Insurance_SimulationRepository insuranceSimulationRepository;

	@Autowired
	IInsuranceSimulationService iinsuranceSimulationService;

	@Autowired
	Insurance_SimulationFavorisRepository insuranceSimulationFavorisRepository;
	
    @Autowired
    UserRepository userRepository;
	
    @Autowired
    private MailService emailService;
    
    
	int number_rooms;
	int number_floors;
	int house_age;
	int house_value;
	int goods_value;
	@Enumerated(EnumType.STRING)
	Category category;
	String name;
	boolean fireSafety;
	boolean waterDamage;
	boolean robbery;

	public int getNumber_rooms() {
		return number_rooms;
	}

	public void setNumber_rooms(int number_rooms) {
		this.number_rooms = number_rooms;
	}

	public int getNumber_floors() {
		return number_floors;
	}

	public void setNumber_floors(int number_floors) {
		this.number_floors = number_floors;
	}

	public int getHouse_age() {
		return house_age;
	}

	public void setHouse_age(int house_age) {
		this.house_age = house_age;
	}

	public int getHouse_value() {
		return house_value;
	}

	public void setHouse_value(int house_value) {
		this.house_value = house_value;
	}

	public int getGoods_value() {
		return goods_value;
	}

	public void setGoods_value(int goods_value) {
		this.goods_value = goods_value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFireSafety() {
		return fireSafety;
	}

	public void setFireSafety(boolean fireSafety) {
		this.fireSafety = fireSafety;
	}

	public boolean isWaterDamage() {
		return waterDamage;
	}

	public void setWaterDamage(boolean waterDamage) {
		this.waterDamage = waterDamage;
	}

	public boolean isRobbery() {
		return robbery;
	}

	public void setRobbery(boolean robbery) {
		this.robbery = robbery;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Category[] getCategories() {
		return Category.values();
	}

	public String Simuler() {
		insuranceSimulationRepository.deleteAllInBatch();



		Category categoryy = null;
		Category categoryy1 = null;
		Category categoryy2 = null;
		Category categoryy3 = null;
		Category categoryparam = null;
		Category category1 = categoryy1.Appartement;
		Category category2 = categoryy2.Commercial_Local;
		Category category3 = categoryy3.House;
		int interest_self;

		if (category == category1) {

			categoryparam = category1;


		} else if (category == category2) {
			categoryparam = category2;
		} else if (category == category3) {
			categoryparam = category3;

		}

		Insurance insurance = new Insurance();
		Insurance_Offer insuranceOffer = new Insurance_Offer();

		for (int i = 0; i < insurancerepository.findByName(name).size(); i++) {
			insurance = insurancerepository.findByName(name).get(i);
		}
//		System.err.println(insurance.getId());
//		System.err.println(insuranceOfferrepository.insuranceOfferid(insurance.getId(), categoryparam));
		int test = insuranceOfferrepository.insuranceOfferid(insurance.getId(), categoryparam);
//		System.err.println(test);
		System.err.println(robbery);
		
		iinsuranceSimulationService.ajoutSimulation(number_rooms, number_floors, house_age, house_value, goods_value,
				category, name, fireSafety, waterDamage, robbery, HomeController.connectedUser.getUserName(),
				insuranceOfferrepository.insuranceOfferid(insurance.getId(), categoryparam),
				insuranceOfferrepository.FindByInterestCategory(insurance.getId(), categoryparam),
				insuranceOfferrepository.FindByInterestGoods(insurance.getId(), categoryparam));
		
		
		
		
		
		
		
		
		
		
		
		
		
		/////
		float rooms_interest=1;
		if (number_rooms>=3){
			rooms_interest=12/(float)(10);
		}
		else if (number_rooms<3){
			rooms_interest=14/(float)(10);
		}
		
		float floors_interest=1;
		if (number_floors<2){
			floors_interest=20/(float)(10);
		}
		else if (number_floors>=2){
			floors_interest=30/(float)(10);
		}
		
		float age_interest=1;
		if (house_age>=10){
			age_interest=12/(float)(10);
		}
		else if (house_age<10){
			age_interest=14/(float)(10);
		}
		
		//float total_requests =(int)(rooms_interest+floors_interest+age_interest);
//		float total = rooms_interest+floors_interest+age_interest;
		
		float robbery_total;
		if(robbery){
			System.err.println("someone was here");
			robbery_total =(float)((insurance.getInterest_robbery()*(house_value+goods_value))/(float)100*1.0);
		}
		else {
			robbery_total =0;
		}
		
		float fireSafety_total;
		if(fireSafety){
			
			fireSafety_total =(float)((insurance.getInterest_firesafety()*(house_value+goods_value))/(float)100*1.0);
		}
		else
		{
			fireSafety_total =0;
		}
		float waterDamage_total;
		if(waterDamage){
			waterDamage_total =(float)((insurance.getInterest_waterDamage()*(house_value+goods_value))/(float)100*1.0);	
			
		}
		else
		{
			waterDamage_total =0;	
		}
		float category_total=(float)((insuranceOfferrepository.FindByInterestCategory(insurance.getId(), categoryparam)*(house_value+goods_value))/(float)100*1.0);
		float goods_total=(float)((insuranceOfferrepository.FindByInterestGoods(insurance.getId(), categoryparam)*(house_value+goods_value))/(float)100*1.0);
		
		
		totalshow =((robbery_total+fireSafety_total+waterDamage_total+category_total+goods_total)*(rooms_interest*floors_interest*age_interest))/10;
		monthlyPaybackShow=(totalshow/12);
		
		
		return "simulationResultInsurance.xhtml";
//		return "showInsuranceSimulations.xhtml";


	}
	
	private List<InsuranceSimulation_Favoris> simulations;
	private String insuranceName;
	@Temporal(TemporalType.DATE)
	private Date date;
	private float monthlyPayback;
	private float total;
	
	public float getMonthlyPayback() {
		return monthlyPayback;
	}

	public void setMonthlyPayback(float monthlyPayback) {
		this.monthlyPayback = monthlyPayback;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	public List<InsuranceSimulation_Favoris> getSimulations() {
		simulations=iinsuranceSimulationService.getSimulationsById(HomeController.connectedUser.getUserName());
		return simulations;
	}

	public void setSimulations(List<InsuranceSimulation_Favoris> simulations) {
		this.simulations = simulations;
	}
	
	public void delete(int id){
		iinsuranceSimulationService.deleteSimulation_FavorisById(id);
//	iinsuranceSimulationService.
	}
	
	////////////////insuranceNamesList///////////////////
	 public List <String> insuranceNames;

	public List<String> getInsuranceNames() {
		insuranceNames = iinsuranceSimulationService.findInsuranceNames();
		return insuranceNames;
	}

	public void setInsuranceNames(List<String> insuranceNames) {
		this.insuranceNames = insuranceNames;
	}
	
    public void valueChanged(ValueChangeEvent event) {
        //do your stuff
    	String naame =String.valueOf(event.getNewValue().toString());
    	System.out.println(naame);
    	System.out.println("kont lehné");

    	
    }
	////////////////////////////////////
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    private boolean value1;  
    private boolean value2;
 
    public boolean isValue1() {
        return value1;
    }
 
    public void setValue1(boolean value1) {
        this.value1 = value1;
    }
 
    public boolean isValue2() {
        return value2;
    }
 
    public void setValue2(boolean value2) {
        this.value2 = value2;
    }
     
    public void addMessage() {
        String summary = value2 ? "Checked" : "Unchecked";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }
    
    
    
    
    
	public String redirectToSimulerInsurance(){
		return "insuranceSimulation.xhtml";
	}

	
	public String redirectToPayement(){
		return "payement.xhtml";
	}
	
	@Autowired
	private StripeService stripeService;
	
	public String customer1(){
		stripeService.createStripeCustomer(HomeController.connectedUser.getUserName());
		return "payement2.xhtml";
	}
	
	
	
	//test passing////
	
	
	 private String param1;
	    private String param2;
	    private int param1int;
	    
	    public int getParam1int() {
			return param1int;
		}
private float param1float;

		public float getParam1float() {
			param1float=Float.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param1Name"));
	return param1float;
}

public void setParam1float(float param1float) {
	this.param1float = param1float;
}

		public String parametersAction(){
	        FacesContext fc = FacesContext.getCurrentInstance();
	        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
//	        param1 = params.get("param1Name");
	        param2 = params.get("param2Name");
	        param1float=Float.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param1Name"));
	        System.err.println(param1float);
//	        System.err.println("heyy");
//	        int id =Integer.parseInt(params.get("param1Name"));
//	        System.err.println(id);
//	        float monthly=getMonthlyPayback();
//	        System.err.println(getMonthlyPayback());
	        return "payement.xhtml";
	    }

	    public String getParam1() {
	        return param1;
	    }

	    public String getParam2() {
	        return param2;
	    }
	    
	    public String showInsuranceSimulations(){
	    	return "showInsuranceSimulations.xhtml";
	    }
	    
	    
	    ///customer///////////
	    
	    private String customerId;
	    private String carta;
	    private String expMonth;
	    private String expYear;
	    private String cvc;
	    
	    public String getCustomerId() {
	    	customerId=stripeService.createStripeCustomer(HomeController.connectedUser.getUserName());
	    	
			return customerId;
		}

		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}

		public String getCarta() {
			return carta;
		}

		public void setCarta(String carta) {
			this.carta = carta;
		}

		public String getExpMonth() {
			return expMonth;
		}

		public void setExpMonth(String expMonth) {
			this.expMonth = expMonth;
		}

		public String getExpYear() {
			return expYear;
		}

		public void setExpYear(String expYear) {
			this.expYear = expYear;
		}

		public String getCvc() {
			return cvc;
		}

		public void setCvc(String cvc) {
			this.cvc = cvc;
		}

		public String createCustumorStripe()throws StripeException{
	    	stripeService.createCustumorStripe(customerId, carta, expMonth, expYear, cvc);
	    	return"payement3.xhtml";
	    }
		
		
		
		
		
		/////////////////payment intent/////////////////
	    public enum Currency {
	        EUR, USD, DT;
	    }
	    private String description;
	    private int amount;
	    private Currency currency;
	    private String stripeToken;		
				
		public int getAmount() {
//			amount=Integer.parseInt(param1.trim());
			return amount;
		}

		public void setAmount(int amount) {
			this.amount = amount;
		}
		private String id;
		
		public String getId() throws StripeException{
			return id;
//			return id=stripeService.paymentIntent(new ChargeRequest("payment",Math.round(param1float)*100,stripeToken,"EUR"));
		}

		public void setId(String id) {
			this.id = id;
		}
		public String paymentIntent()throws StripeException{
			
//			int i = Integer.parseInt(param1);
//			int i2 = (int)monthlyPayback;
//			int yy=(int)monthlyPayback*100;
			System.err.println("voila1");
//			Integer yyy=Integer.parseInt(param1)*10;
			System.err.println("voila");
//			int round=Math.round(yyy);
//			System.err.println(round);
			System.err.println("hay el amount");
//			stripeService.paymentIntent(new ChargeRequest("payment",round,stripeToken,"EUR"));
			stripeService.paymentIntent(new ChargeRequest("payment",Math.round(param1float)*100,stripeToken,"EUR"));
			id=stripeService.paymentIntent(new ChargeRequest("payment",Math.round(param1float)*100,stripeToken,"EUR"));
			System.err.println("eraeraerareaer");
			System.err.println(id);
			return"payement4.xhtml";
			
		}
		
		
		
		
		
		
		
		


		public ResponseEntity<String> confirm() throws StripeException {
			PaymentIntent paymentIntent = stripeService.confirm(id);
			String paymentStr = paymentIntent.toJson();
			
	    	User user = userRepository.findByUserName(HomeController.connectedUser.getUserName()).get();
	    	System.err.println(user.getMailAddress());
//	        emailService.sendMail(user.getMailAddress(), "Test Subject", "Test mail");
	    	emailService.sendMail(user.getMailAddress(), "Insurance Confirmed", "Hello Mr/Mrs "+user.getLastName()+
	    			"\n"
	    			+ "\n"
	    			+ "\n"
	    			+ "\n Your Insurance has been paid and confirmed "
	    			+ "\n You can log in to your Stripe Account to See Further details about your payment "
	    			+ "\n The Total Payment Amount is " +param2+" DT"
	    					+ "\n"
	    					+ "\n"
	    					+ "\n"
	    					+ "\n"
	    					+ "\n"
	    					+ "\n You want to go back to our website ? Check the link below !"
	    					+ "\n http://localhost:8084/user/Home"
	    					+ "\n"
	    					+ "\n"
	    					+ "\n"
	    					+ "DARI");
	        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
		}
	    
		
		
		public ResponseEntity<String> cancel() throws StripeException {
			PaymentIntent paymentIntent = stripeService.cancel(id);
			String paymentStr = paymentIntent.toJson();
			return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		////////////////simuler save//////////
		private float totalshow;
		private float monthlyPaybackShow;
		
		
		public float getTotalshow() {
			return totalshow;
		}

		public void setTotalshow(float totalshow) {
			this.totalshow = totalshow;
		}

		public float getMonthlyPaybackShow() {
			return monthlyPaybackShow;
		}

		public void setMonthlyPaybackShow(float monthlyPaybackShow) {
			this.monthlyPaybackShow = monthlyPaybackShow;
		}

		
		
		public String Simulersave(){
			
			insuranceSimulationRepository.deleteAllInBatch();


			Category categoryy = null;
			Category categoryy1 = null;
			Category categoryy2 = null;
			Category categoryy3 = null;
			Category categoryparam = null;
			Category category1=categoryy1.Appartement;
			Category category2=categoryy2.Commercial_Local;
			Category category3=categoryy3.House;
			int interest_self;

			if (category==category1) {

				categoryparam=category1;

				
			}
			else if (category==category2) {
				categoryparam=category2;

			}
			else if (category==category3) {
				categoryparam=category3;
			}
				
			
			Insurance insurance = new Insurance();
			Insurance_Offer insuranceOffer = new Insurance_Offer();
			
			
			for (int i = 0; i < insurancerepository.findByName(name).size(); i++) {
				insurance = insurancerepository.findByName(name).get(i);
			}

			
			iinsuranceSimulationService.saveSimulation(number_rooms, number_floors, house_age,
			house_value, goods_value, category, name, fireSafety, waterDamage, robbery, HomeController.connectedUser.getUserName(), 
			insuranceOfferrepository.insuranceOfferid(insurance.getId(),categoryparam),
			insuranceOfferrepository.FindByInterestCategory(insurance.getId(),categoryparam),
			insuranceOfferrepository.FindByInterestGoods(insurance.getId(),categoryparam));



			return "showInsuranceSimulations.xhtml";
//			return "votre simulation est enregistré : vous pouvez consulter la liste des simulations enregistrés pour trouvez plus de détails";

		
		}	
}
