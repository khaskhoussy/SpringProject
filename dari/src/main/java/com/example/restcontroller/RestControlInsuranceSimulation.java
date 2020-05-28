package com.example.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Category;
import com.example.entity.Insurance;
import com.example.entity.InsuranceSimulation_Favoris;
import com.example.entity.Insurance_Offer;
import com.example.entity.Simulation;
import com.example.entity.SimulationPK;
import com.example.entity.Simulation_Favoris;
import com.example.repository.InsuranceRepository;
import com.example.repository.Insurance_OfferRepository;
import com.example.repository.Insurance_SimulationFavorisRepository;
import com.example.repository.Insurance_SimulationRepository;
import com.example.service.IInsuranceService;
import com.example.service.IInsuranceSimulationService;



@RestController
@RequestMapping("/user/insurance_simulation")
public class RestControlInsuranceSimulation {


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

//	@Autowired
//	Simulation_FavorisRepository Simulation_Favorisrepository;
	
	
	@PostMapping(value = "/SimulerInsurance")
	public Object [] Simuler(@RequestParam(value = "number_rooms") int number_rooms,
			@RequestParam(value = "number_floors") int number_floors,@RequestParam(value = "house_age") int house_age,
			@RequestParam(value = "house_value") int house_value,@RequestParam(value = "goods_value") int goods_value,
			@RequestParam(value = "nameCategory") Category category,@RequestParam(value = "name") String name,
			@RequestParam(value = "fireSafety") boolean fireSafety,@RequestParam(value = "waterDamage") boolean waterDamage,
			@RequestParam(value = "robbery") boolean robbery,HttpServletResponse response,HttpServletRequest request){
		
		insuranceSimulationRepository.deleteAllInBatch();

//		System.err.println("voilavoila");
//		Category category1 = null;
//		Category category2 = null;
//		Category category3 = null;
		

		 
		Category categoryy = null;
		Category categoryy1 = null;
		Category categoryy2 = null;
		Category categoryy3 = null;
		Category categoryparam = null;
		Category category1=categoryy1.Appartement;
		Category category2=categoryy2.Commercial_Local;
		Category category3=categoryy3.House;
		int interest_self;
		//if (category.equals("Appartement")) 
		if (category==category1) {

			//categoryparam=category.Appartement;
			categoryparam=category1;
//			categoryparam=categoryy.Appartement;
			
		}
		else if (category==category2) {
			categoryparam=category2;
//			categoryparam=category.Commercial_Local;
//			categoryparam=categoryy.Commercial_Local;
		}
		else if (category==category3) {
			categoryparam=category3;
//			System.err.println(categoryparam=category.House);
//			categoryparam=category.House;
//			categoryparam=categoryy.House;
//			System.err.println(categoryparam=category.House);
		}
			
		
		Insurance insurance = new Insurance();
		Insurance_Offer insuranceOffer = new Insurance_Offer();
		
		
		for (int i = 0; i < insurancerepository.findByName(name).size(); i++) {
			insurance = insurancerepository.findByName(name).get(i);
		}

		System.err.println(insurance.getId());
		System.err.println(categoryparam);
		System.err.println(categoryy);
		iinsuranceSimulationService.ajoutSimulation(number_rooms, number_floors, house_age,
 		house_value,goods_value,category,name,fireSafety,waterDamage,robbery,"bbb",
 		insuranceOfferrepository.insuranceOfferid(insurance.getId(),categoryparam),
 		insuranceOfferrepository.FindByInterestCategory(insurance.getId(),categoryparam),
 		insuranceOfferrepository.FindByInterestGoods(insurance.getId(),categoryparam));
		
		
		
//		OffreRepository.FindByInterest(bank.getId(), durationparam);
//		OffreRepository.findBySelf_finance_rate(bank.getId(), durationparam);
//		System.out.print(bank.getId());
//		int total_due_date = 12*refundPeriod;


		
		return new Object[] {insuranceSimulationRepository.findAll()};

	
	}
	
	
	
	@PostMapping(value = "/SimulerInsurancesave")
	public String Simulersave(@RequestParam(value = "number_rooms") int number_rooms,
			@RequestParam(value = "number_floors") int number_floors,@RequestParam(value = "house_age") int house_age,
			@RequestParam(value = "house_value") int house_value,@RequestParam(value = "goods_value") int goods_value,
			@RequestParam(value = "nameCategory") Category category,@RequestParam(value = "name") String name,
			@RequestParam(value = "fireSafety") boolean fireSafety,@RequestParam(value = "waterDamage") boolean waterDamage,
			@RequestParam(value = "robbery") boolean robbery,HttpServletResponse response,HttpServletRequest request){
		
		insuranceSimulationRepository.deleteAllInBatch();

//		System.err.println("voilavoila");

		 		

		 
		Category categoryy = null;
		Category categoryy1 = null;
		Category categoryy2 = null;
		Category categoryy3 = null;
		Category categoryparam = null;
		Category category1=categoryy1.Appartement;
		Category category2=categoryy2.Commercial_Local;
		Category category3=categoryy3.House;
		int interest_self;
		//if (category.equals("Appartement")) 
		if (category==category1) {

			//categoryparam=category.Appartement;
			categoryparam=category1;
//			categoryparam=categoryy.Appartement;
			
		}
		else if (category==category2) {
			categoryparam=category2;
//			categoryparam=category.Commercial_Local;
//			categoryparam=categoryy.Commercial_Local;
		}
		else if (category==category3) {
			categoryparam=category3;
//			System.err.println(categoryparam=category.House);
//			categoryparam=category.House;
//			categoryparam=categoryy.House;
//			System.err.println(categoryparam=category.House);
		}
			
		
		Insurance insurance = new Insurance();
		Insurance_Offer insuranceOffer = new Insurance_Offer();
		
		
		for (int i = 0; i < insurancerepository.findByName(name).size(); i++) {
			insurance = insurancerepository.findByName(name).get(i);
		}
//		System.err.println("voila");
		//FindByInterest(@Param("insuranceId")int insuranceId,@Param("durationparam")Category categoryparam);

		/*
	private int interest_firesafety;
	
	private int interest_waterDamage;
	
	private int interest_robbery;
		 */
//		System.err.println(insuranceOfferrepository.insuranceOfferid(insurance.getId(),categoryparam));
//		System.err.println(insuranceOfferrepository.FindByInterestCategory(insurance.getId(),categoryparam));
//		System.err.println(insuranceOfferrepository.FindByInterestGoods(insurance.getId(),categoryparam));
		
		iinsuranceSimulationService.saveSimulation(number_rooms, number_floors, house_age,
		house_value, goods_value, category, name, fireSafety, waterDamage, robbery, "bbb", 
		insuranceOfferrepository.insuranceOfferid(insurance.getId(),categoryparam),
		insuranceOfferrepository.FindByInterestCategory(insurance.getId(),categoryparam),
		insuranceOfferrepository.FindByInterestGoods(insurance.getId(),categoryparam));
		
		
		
//		OffreRepository.FindByInterest(bank.getId(), durationparam);
//		OffreRepository.findBySelf_finance_rate(bank.getId(), durationparam);
//		System.out.print(bank.getId());
//		int total_due_date = 12*refundPeriod;


		
		return "votre simulation est enregistré : vous pouvez consulter la liste des simulations enregistrés pour trouvez plus de détails";

	
	}	
	// URL : http://localhost:8081/dari/servlet/FindByOffer
	@GetMapping(value = "/FindByInsurranceOffer")
	@ResponseBody
	// public String FindByData(@RequestParam(value="refundPeriod") Integer
	// refundPeriod) {
	public Object[] FindByInsuranceOffer(HttpServletResponse response,HttpServletRequest request){
		Simulation simulation = new Simulation();
		SimulationPK simulationpk = new SimulationPK();
		Simulation_Favoris smf = new Simulation_Favoris();

		// smf = Simulation_Favorisrepository.FindByData(refundPeriod).get();
		return new Object[] {insuranceSimulationFavorisRepository.FindByInsuranceOffer(Home.connectedUser)
				};
//insuranceSimulationFavorisRepository.FindByInsuranceOfferEmail(Home.connectedUser)
		// smf=Simulation_Favorisrepository.findById(id).get();

	}
	
    // URL : http://localhost:8084/getInsuranceSimulationsById
@GetMapping(value = "getInsuranceSimulationsById")
@ResponseBody
public List<InsuranceSimulation_Favoris> getSimulationsById(HttpServletResponse response,HttpServletRequest request) {

	//return iinsuranceSimulationService.getSimulationsById(Home.connectedUser);
	return iinsuranceSimulationService.getSimulationsById(Home.connectedUser);
}
	

@DeleteMapping("/deleteInsuranceSimulation_FavorisById/{id}")
@ResponseBody
public void deleteSimulation_FavorisById(@PathVariable("id") int id) {
	insuranceSimulationFavorisRepository.deleteById(id);
	// Simulation_Favorisrepository.deleteSimulation_FavorisById(id_simfav);

}
	
	
}
