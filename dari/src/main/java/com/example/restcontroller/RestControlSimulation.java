package com.example.restcontroller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import javafx.util.Pair;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Bank;
import com.example.entity.Duration;
import com.example.entity.Offre;
import com.example.entity.Simulation;
import com.example.entity.SimulationPK;
import com.example.entity.Simulation_Favoris;
import com.example.entity.User;
import com.example.repository.BankRepository;
import com.example.repository.OffreRepository;
import com.example.repository.SimulationRepository;
import com.example.repository.Simulation_FavorisRepository;
import com.example.repository.UserRepository;
import com.example.service.ISimulationService;


@RestController
@RequestMapping("/user/simulation")
public class RestControlSimulation {

	@Autowired
	ISimulationService isimulationservice;

	@Autowired
	BankRepository bankrepository;

//	@Autowired
//	OffreRepository OffreRepository;
	
	@Autowired
	OffreRepository OffreRepository;

	@Autowired
	SimulationRepository simulationRepository;

	@Autowired
	Simulation_FavorisRepository Simulation_Favorisrepository;

	
	@Autowired
	UserRepository userRepository ;
	
	// http://localhost:8081/dari/servlet/ajouterSimulation
	// {"missionId":1,"employeId":2,"dateDebut":"2020-03-01","dateFin":"2021-03-01"}

	@PostMapping("/ajouterSimulation/iduser/idoffre/date/monthleyPayBack/creditAmount/refundPeriod/salary/price")
	@ResponseBody
	public void ajouterSimulation(@PathVariable("iduser") int idUser, @PathVariable("idoffre") int idOffre,
			@PathVariable("date") Date date, @PathVariable("monthleyPayBack") float monthleyPayBack,
			@PathVariable("creditAmount") float creditAmount, @PathVariable("refundPeriod") int refundPeriod,
			@PathVariable("salary") float salary, @PathVariable("price") float price,
			@PathVariable("monthlyCapacity") float monthlyCapacity) {
		isimulationservice.ajouterSimulation(idUser, idOffre, date, monthleyPayBack, creditAmount, refundPeriod, salary,
				price, monthlyCapacity);

	}

	// http://localhost:8081/dari/servlet/Simuler/1/2
	@PostMapping(value = "/Simuler/{iduser}/{idoffre}")
	public void Simuler(@RequestBody SimulationPK simulationpk, @PathVariable("iduser") int idUser,
			@PathVariable("idoffre") int idOffre) {
		// isimulationservice.Simuler(simulation,idUser, idOffre);
		isimulationservice.Simuler(idUser, idOffre, simulationpk);
	}

	/*
	 * add request parm(value égal credit amount monthly payback namebank
	 * namebank (@RequestBody SimulationPK simulationpk,) offre new offre();
	 * f.offrereposi.findbyDuration select offre.id from offre join bank where
	 * bank fel repo mte3 el bank findByName getDuration select o.duration from
	 * offre o join o.bank b where b.id=bankId;
	 */

	// PostMapping(value="/Simuler/{monthleyPayBack"}/{"creditAmount"}/{"creditAmount"}

	// @PostMapping(value = "/Simuler2")
	@PostMapping(value = "/Simuler")
//	@ResponseBody
	public Object [] Simuler(@RequestParam(value = "creditAmount") float creditAmount,
			@RequestParam(value = "refundPeriod") int refundPeriod, @RequestParam(value = "name") String name,
			@RequestParam("monthlyCapacity") float monthlyCapacity,
			@RequestParam("self_finance") float self_finance,HttpServletResponse response,HttpServletRequest request) {
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
		/*
		 * for (int i=0;i<OffreRepository.findById(bank.getId()){
		 * offre=OffreRepository.FindByInterest(bankId, Duration); }
		 */
		OffreRepository.FindByInterest(bank.getId(), durationparam);
		OffreRepository.findBySelf_finance_rate(bank.getId(), durationparam);
		System.err.print(bank.getId());
		int total_due_date = 12*refundPeriod;
		System.err.println("erzerezrzr");
//		User user = new User();
//		user=userRepository.findByUserName(userName).get();
//		System.err.println(user.getUserName());
		
		
		
		isimulationservice.ajoutSimulation(creditAmount, refundPeriod, name,
				OffreRepository.FindByInterest(bank.getId(), durationparam), Home.connectedUser,
				OffreRepository.offreid(bank.getId(), durationparam), monthlyCapacity, self_finance);
		isimulationservice.selfFinanceComparaison(self_finance, refundPeriod, creditAmount, name,
				OffreRepository.findBySelf_finance_rate(bank.getId(), durationparam));
		// isimulationservice.selfFinanceComparaison(self_finance, refundPeriod,
		// creditAmount, name,
		// OffreRepository.findBySelf_finance_rate(bank.getId(),
		// durationparam));
		System.err.println(OffreRepository.findBySelf_finance_rate(bank.getId(), durationparam));
		float rest = ((OffreRepository.findBySelf_finance_rate(bank.getId(), durationparam)/(float)100)*creditAmount)-self_finance;

		// return (simulationRepository.findAll(),"ae");
//		Pair[] p = new Pair[] { new Pair<>(simulationRepository.findAll(),
//				isimulationservice.selfFinanceComparaison(self_finance, refundPeriod, creditAmount, name,
//						OffreRepository.findBySelf_finance_rate(bank.getId(), durationparam))) };
//		

		return new Object[] {simulationRepository.findAll(),
				"number of due dates :",total_due_date,
				isimulationservice.selfFinanceComparaison(self_finance, refundPeriod, creditAmount, name,
				OffreRepository.findBySelf_finance_rate(bank.getId(), durationparam)),
				"you should add this amount to your self finance to get the credit :",
				rest,
				"you can contact the expert with email :"};

		// return isimulationservice.selfFinanceComparaison(self_finance,
		// refundPeriod, creditAmount, name,
		// OffreRepository.findBySelf_finance_rate(bank.getId(),
		// durationparam));
		// isimulationservice.Simuler(simulation,idUser, idOffre);
		// isimulationservice.Simuler(idUser, idOffre, simulationpk);

	}

	@PostMapping(value = "/Simulersave")
	public String Simulersave(@RequestParam(value = "creditAmount") float creditAmount,
			@RequestParam(value = "refundPeriod") int refundPeriod, @RequestParam(value = "name") String name,
			@RequestParam("monthlyCapacity") float monthlyCapacity,
			@RequestParam("self_finance") float self_finance,HttpServletResponse response,HttpServletRequest request){

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
		/*
		 * for (int i=0;i<OffreRepository.findById(bank.getId()){
		 * offre=OffreRepository.FindByInterest(bankId, Duration); }
		 */
		OffreRepository.FindByInterest(bank.getId(), durationparam);
		System.out.print(bank.getId());

		// isimulationservice.saveSimulation(creditAmount, refundPeriod, name,
		// OffreRepository.FindByInterest(bank.getId(),durationparam),idUser,OffreRepository.offreid(bank.getId(),durationparam));
		isimulationservice.saveSimulation(creditAmount, refundPeriod, name,
				OffreRepository.FindByInterest(bank.getId(), durationparam), Home.connectedUser,
				OffreRepository.offreid(bank.getId(), durationparam), monthlyCapacity,self_finance);

		// if ()
		return "votre simulation est enregistré : vous pouvez consulter la liste des simulations enregistrés pour trouvez plus de détails";
		// isimulationservice.Simuler(simulation,idUser, idOffre);
		// isimulationservice.Simuler(idUser, idOffre, simulationpk);

	}

	@PostMapping(value = "/Simuler2")
	public Object [] Simuler2(@RequestParam(value = "creditAmount") float creditAmount,
			@RequestParam(value = "refundPeriod") int refundPeriod, @RequestParam(value = "name") String name,
			@RequestParam(value = "name2") String name2,
			@RequestParam("monthlyCapacity") float monthlyCapacity, @RequestParam("self_finance") float self_finance,
			HttpServletResponse response,HttpServletRequest request) {

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
		int total_due_date = 12*refundPeriod;
		float rest = ((OffreRepository.findBySelf_finance_rate(bank.getId(), durationparam)/(float)100)*creditAmount)-self_finance;
		float rest1 = ((OffreRepository.findBySelf_finance_rate(bank1.getId(), durationparam)/(float)100)*creditAmount)-self_finance;
//		System.err.print("voici");
//		System.err.println(OffreRepository.FindByInterest(bank.getId(), durationparam));
//		System.err.println(OffreRepository.FindByInterest(bank1.getId(), durationparam));
//		System.out.print(bank.getId());

		isimulationservice.ajoutSimulation(creditAmount, refundPeriod, name,
				OffreRepository.FindByInterest(bank.getId(), durationparam), Home.connectedUser,
				OffreRepository.offreid(bank.getId(), durationparam), monthlyCapacity, self_finance);

		isimulationservice.ajoutSimulation(creditAmount, refundPeriod, name2,
				OffreRepository.FindByInterest(bank1.getId(), durationparam), Home.connectedUser,
				OffreRepository.offreid(bank1.getId(), durationparam), monthlyCapacity, self_finance);
		
		isimulationservice.monthlyPaybackCompraison(refundPeriod,creditAmount,name,name2,OffreRepository.FindByInterest(bank.getId(), durationparam),OffreRepository.FindByInterest(bank1.getId(), durationparam));
		// isimulationservice.saveSimulation(creditAmount, refundPeriod, name2,
		// OffreRepository.FindByInterest(bank1.getId(),durationparam), idUser);
		
		return new Object[] {simulationRepository.findAll(),isimulationservice.monthlyPaybackCompraison(refundPeriod,creditAmount,name,name2,OffreRepository.FindByInterest(bank.getId(), durationparam),
				OffreRepository.FindByInterest(bank1.getId(), durationparam)),
				isimulationservice.priceCompraison(refundPeriod,creditAmount,name,name2,
						OffreRepository.FindByInterest(bank.getId(), durationparam),
						OffreRepository.FindByInterest(bank1.getId(), durationparam)),
				isimulationservice.documentFeesCompraison(refundPeriod, creditAmount, name, name2,
						OffreRepository.FindByInterest(bank.getId(), durationparam), 
						OffreRepository.FindByInterest(bank1.getId(), durationparam)),
				isimulationservice.selfFinanceBanksComparaison(name, name2, 
						OffreRepository.offreid(bank.getId(), durationparam),
						OffreRepository.offreid(bank1.getId(), durationparam)),
				"number of due dates :",total_due_date,
				"for the first bank :",
				isimulationservice.selfFinanceComparaison(self_finance, refundPeriod, creditAmount, name,
						OffreRepository.findBySelf_finance_rate(bank.getId(), durationparam)),
				"for the second bank :",
				isimulationservice.selfFinanceComparaison(self_finance, refundPeriod, creditAmount, name2,
						OffreRepository.findBySelf_finance_rate(bank1.getId(), durationparam)),				
				"you should add this amount to your self finance to get the credit from the first bank :",
				rest,
				"you should add this amount to your self finance to get the credit from the second bank :",
				rest1};
		//return simulationRepository.findAll();
		// isimulationservice.Simuler(simulation,idUser, idOffre);
		// isimulationservice.Simuler(idUser, idOffre, simulationpk);

	}

	@PostMapping(value = "/Simuler2save")
	public String Simuler2save(@RequestParam(value = "creditAmount") float creditAmount,
			@RequestParam(value = "refundPeriod") int refundPeriod, @RequestParam(value = "name") String name,
			@RequestParam(value = "name2") String name2,
			@RequestParam("monthlyCapacity") float monthlyCapacity,
			@RequestParam("self_finance") float self_finance){

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
				OffreRepository.FindByInterest(bank.getId(), durationparam), Home.connectedUser,
				OffreRepository.offreid(bank.getId(), durationparam), monthlyCapacity,self_finance);
		isimulationservice.saveSimulation(creditAmount, refundPeriod, name2,
				OffreRepository.FindByInterest(bank1.getId(), durationparam), Home.connectedUser,
				OffreRepository.offreid(bank.getId(), durationparam), monthlyCapacity,self_finance);

		return "votre simulation est enregistré : vous pouvez consulter la liste des simulations enregistrés pour trouvez plus de détails";
		// isimulationservice.Simuler(simulation,idUser, idOffre);
		// isimulationservice.Simuler(idUser, idOffre, simulationpk);

	}

	@GetMapping(value = "getSimulationById/{id_simfav}")
	@ResponseBody
	public Simulation_Favoris getSimulationById(@PathVariable("id_simfav") int id_simfav) {

		Simulation simulation = new Simulation();
		SimulationPK simulationpk = new SimulationPK();
		Simulation_Favoris smf = new Simulation_Favoris();
		smf = Simulation_Favorisrepository.findById(id_simfav).get();

		if (smf.getMonthleyPayBack() > smf.getMonthlyCapacity()) {
			System.err.println("This loan exceeds your payback capacity");
		} else {
			System.err.println("This loan is suitable for your capacity");

		}

		// smf=Simulation_Favorisrepository.findById(id).get();

		return isimulationservice.getSimulationById(id_simfav,Home.connectedUser);
	}

	// URL : http://localhost:8081/dari/servlet/deleteSimulation_FavorisById/1
	@DeleteMapping("/deleteSimulation_FavorisById/{id_simfav}")
	@ResponseBody
	public void deleteSimulation_FavorisById(@PathVariable("id_simfav") int id_simfav) {
		Simulation_Favorisrepository.deleteById(id_simfav);
		// Simulation_Favorisrepository.deleteSimulation_FavorisById(id_simfav);

	}

	// http://localhost:8081/dari/servlet/FindByData
	@GetMapping(value = "/FindByData")
	@ResponseBody
	// public String FindByData(@RequestParam(value="refundPeriod") Integer
	// refundPeriod) {
	public String FindByData(@RequestParam(value = "refundPeriod") int refundPeriod,
			@RequestParam(value = "creditAmount") float creditAmount,
			@RequestParam(value = "monthlyCapacity") float monthlyCapacity) {
		Simulation simulation = new Simulation();
		SimulationPK simulationpk = new SimulationPK();
		Simulation_Favoris smf = new Simulation_Favoris();

		// smf = Simulation_Favorisrepository.FindByData(refundPeriod).get();
		return isimulationservice.FindByData(refundPeriod, creditAmount, monthlyCapacity);

		// smf=Simulation_Favorisrepository.findById(id).get();

	}
	
	
	
	// URL : http://localhost:8081/dari/servlet/FindByOffer
	@GetMapping(value = "/FindByOffer")
	@ResponseBody
	// public String FindByData(@RequestParam(value="refundPeriod") Integer
	// refundPeriod) {
	public Object[] FindByOffer(HttpServletResponse response,HttpServletRequest request){
		Simulation simulation = new Simulation();
		SimulationPK simulationpk = new SimulationPK();
		Simulation_Favoris smf = new Simulation_Favoris();

		// smf = Simulation_Favorisrepository.FindByData(refundPeriod).get();
		return new Object[] {Simulation_Favorisrepository.FindByOffer(Home.connectedUser)};

		// smf=Simulation_Favorisrepository.findById(id).get();

	}

	
	     // URL : http://localhost:8081/dari/servlet/getSimulationsById/1
    @GetMapping(value = "getSimulationsById")
    @ResponseBody
	public List<Simulation_Favoris> getSimulationsById(HttpServletResponse response,HttpServletRequest request) {

		return isimulationservice.getSimulationsById(Home.connectedUser);
	}
    @GetMapping(value = "getBankNames")
    @ResponseBody	
	public List<String> findByName(){
		System.err.println();
		return isimulationservice.findByName();
	}
	
}
