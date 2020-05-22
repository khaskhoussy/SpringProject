package com.example.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.example.restcontroller.Home;



@Service
public class SimulationServiceImpl implements ISimulationService {

	@Autowired
	SimulationRepository simulationRepository;
	
	@Autowired
	OffreRepository offreRepository;
	@Autowired
	UserRepository userRepository ;
	
	@Autowired
	BankRepository bankrepository;
	
//	@Autowired
//	Simulation_FavorisRepository Simulation_FavorisRepository;
	
	@Autowired
	Simulation_FavorisRepository Simulation_FavorisRepository;
	

		@Override
		public void ajouterSimulation(int idUser, int idOffre, Date date, float monthleyPayBack, float creditAmount,
				int refundPeriod, float salary, float price,float monthlyCapacity) {
			// TODO Auto-generated method stub
			SimulationPK simulationPK = new SimulationPK();
			simulationPK.setCreditAmount(creditAmount);
			simulationPK.setDate(date);
			simulationPK.setMonthleyPayBack(monthleyPayBack);
			simulationPK.setPrice(price);
			simulationPK.setRefundPeriod(refundPeriod);
			simulationPK.setMonthlyCapacity(monthlyCapacity);
			simulationPK.setIdUser(idUser);
			simulationPK.setIdOffre(idOffre);
			
			Simulation simulation = new Simulation();
			
			
			simulation.setSimulationpk(simulationPK);
			//simulation.setValide(false); //par defaut non valide
			simulationRepository.save(simulation);
			
			
		}



		@Override
		public void Simuler(int idUser, int idOffre, SimulationPK simulationpk) {
			
			Offre offre = offreRepository.findById(idOffre).get();
			User user = userRepository.findById(idUser).get();
			Simulation simulation1 = new Simulation();
			/*SimulationPK simulationPKK = new SimulationPK();
			simulation.setSimulationpk(simulationPKK);
			simulationRepository.save(simulation);*/
			
			//SimulationPK simulationPKK = new SimulationPK();
			//SimulationPK simulationPKK1 = new SimulationPK();
			simulationpk.setIdUser(idUser);
			simulationpk.setIdOffre(idOffre);
			
			simulation1.setSimulationpk(simulationpk);
			simulationRepository.save(simulation1);
			
			// TODO Auto-generated method stub
			
		}



		@Override
		public void deleteSimulationById(SimulationPK SimulationPK) {
			// TODO Auto-generated method stub
			//Simulation simulation = simulationRepository.findById(SimulationPK);
			
		}



		@Override
		public void Simuler2(int idUser, int idOffre, SimulationPK simulationpk) {
			// TODO Auto-generated method stub
			/*User user = userRepository.findById(idUser).get();
			Offre offre = offreRepository.findById(idOffre).get();
			SimulationPK simulationpk1 = new SimulationPK();
			int creditAmount = cred
			simulationpk1 = 
			simulationpk.setIdUser(idUser);
			simulationpk.setIdOffre(idOffre);
			
			
			Simulation simulation1 = new Simulation();
			simulation1.setSimulationpk(simulationpk);
			simulationRepository.save(simulation1);*/
		}




		@Override
		public void ajoutSimulation(float creditAmount, int refundPeriod, String name, int interest_rate,String userName,int idOffre,float monthlyCapacity,float self_finance) {
			
			Simulation simulation = new Simulation();
			SimulationPK simulationpk = new SimulationPK();
			Bank bank = new Bank();
			//bank = bankrepository.findByName(name).;
			for (int i = 0; i < bankrepository.findByName(name).size(); i++) {
				bank = bankrepository.findByName(name).get(i);
//				System.err.println("voila");
//				System.err.println(bank.getDocument_fees_interest());
			}
			
			simulationpk.setMonthlyCapacity(monthlyCapacity);
			simulationpk.setCreditAmount(creditAmount);
			simulationpk.setRefundPeriod(refundPeriod);
			simulationpk.setSelf_finance(self_finance);
			//simulationpk.setPrice(((simulationpk.getCreditAmount()*interest_rate)/100)+simulationpk.getCreditAmount());
			float c =(float)(interest_rate/(float)100*1.0);
			float a = ((simulationpk.getCreditAmount()*c)/12);
			float b =(float)(1-Math.pow((1+((float)c/(float)12*1.0)),-refundPeriod*12));
//			System.err.println(c);
//			System.err.println(a);
//			System.err.println(b);
			
			//simulationpk.setPrice(Math.abs(((simulationpk.getCreditAmount()*interest_rate)/12)/(1-(1+(interest_rate/12))*(float)Math.sqrt(refundPeriod*12))));
			simulationpk.setMonthleyPayBack((float)Math.abs((float)a/(float)b));
			//simulationpk.setMonthleyPayBack(Math.abs(((simulationpk.getCreditAmount()*interest_rate)/12)/(1-(float)Math.pow((1+(interest_rate/12)),refundPeriod*12))));
			
			User user = new User();

			user=userRepository.findByUserName(userName).get();
			simulationpk.setIdUser(user.getId());
			simulationpk.setIdOffre(idOffre);
			
			
			//(float)(interest_rate/(float)100*1.0);

//			System.err.println(simulationpk.getMonthleyPayBack());
			Date date=java.util.Calendar.getInstance().getTime();  
			simulationpk.setDate(date);
			//simulationpk.setDate(new Date(12-1-2020));
			simulationpk.setPrice((simulationpk.getMonthleyPayBack()*12*refundPeriod));
//			System.err.println("document_fees");
//			System.err.println(simulationpk.getPrice());
//		
//			System.err.println(bank.getDocument_fees_interest());
			
			simulationpk.setDocument_fees(simulationpk.getPrice()*(bank.getDocument_fees_interest()/(float)100));
//			System.err.println(simulationpk.getPrice()*bank.getDocument_fees_interest()/(float)100);
			simulation.setSimulationpk(simulationpk);
			System.out.println(user.getId());
			System.out.println(monthlyCapacity);
			System.out.println(creditAmount);
			System.out.println(refundPeriod);
			System.out.println(self_finance);
			System.out.println(name);
			System.out.println(idOffre);
			System.out.println(userName);
			
			
			simulationRepository.save(simulation);
			
			
		}



		@Override
		public void saveSimulation(float creditAmount, int refundPeriod, String name, int interest_rate,String userName,int idOffre,float monthlyCapacity,float self_finance) {
			// TODO Auto-generated method stub
			Simulation_Favoris sim_favoris = new Simulation_Favoris();
			User user1 = userRepository.findByUserName(userName).get();
			
			Bank bank = new Bank();
			//bank = bankrepository.findByName(name).;
			for (int i = 0; i < bankrepository.findByName(name).size(); i++) {
				bank = bankrepository.findByName(name).get(i);
//				System.err.println("voila");
//				System.err.println(bank.getDocument_fees_interest());
			}
			
			
			sim_favoris.setUser(user1);
			sim_favoris.setMonthlyCapacity(monthlyCapacity);
			sim_favoris.setCreditAmount(creditAmount);
			sim_favoris.setRefundPeriod(refundPeriod);
			sim_favoris.setPrice(((sim_favoris.getCreditAmount()*interest_rate)/100)+sim_favoris.getCreditAmount());
			float c =(float)(interest_rate/(float)100*1.0);
			float a = ((sim_favoris.getCreditAmount()*c)/12);
			float b =(float)(1-Math.pow((1+((float)c/(float)12*1.0)),-refundPeriod*12));
			sim_favoris.setMonthleyPayBack((float)Math.abs((float)a/(float)b));
			sim_favoris.setId_offre(idOffre);
			sim_favoris.setDocument_fees(sim_favoris.getPrice()*(bank.getDocument_fees_interest()/(float)100));
			sim_favoris.setSelf_finance(self_finance);
			sim_favoris.setBankName(name);
			Date date=java.util.Calendar.getInstance().getTime();  
			sim_favoris.setDate(date);
			
			
			Duration durations = null;
			String otf ="ONE_TO_FIVE";
			String ftt ="FIVE_TO_TEN";
			String tp ="TEN_PLUS";

			//Duration durationparam = null;
			if (refundPeriod <= 5) {
				sim_favoris.setDuration(otf);
				//sim = durations.ONE_TO_FIVE;
			}
			
			else if (refundPeriod > 5 && refundPeriod <= 10) {

				sim_favoris.setDuration(ftt);
			}

			else if (refundPeriod > 10) {

				sim_favoris.setDuration(tp);
			}
			
			
			Simulation_FavorisRepository.save(sim_favoris);
		}



		@Override
		public Simulation_Favoris getSimulationById(int id_simfav,String userName) {
			// TODO Auto-generated method stub
			
			return Simulation_FavorisRepository.findById(id_simfav).get();
		}



		@Override
		public void deleteSimulation_FavorisById(int id_simfav) {
			// TODO Auto-generated method stub
			Simulation_Favoris sim_fav = Simulation_FavorisRepository.findById(id_simfav).get();
			Simulation_FavorisRepository.delete(sim_fav);
			
		}



		/*@Override
		public List<String> FindByData(int refundPeriod,float creditAmount,float monthlyCapacity) {
			// TODO Auto-generated method stub
			 return Simulation_Favorisrepository.FindByData(refundPeriod,creditAmount,monthlyCapacity);
		}*/


		@Override
		public String FindByData(int refund_period,float credit_amount,float monthly_capacity) {
			// TODO Auto-generated method stub
			List<String> list = Simulation_FavorisRepository.FindByData(refund_period, credit_amount, monthly_capacity);
			List<Integer> list1;
			Map<String, Integer> map = new HashMap<>();
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
		}
		
		@Transactional
		public String selfFinanceComparaison(float self_finance,int refundPeriod,float creditAmount,String name,int self_finance_rate){
			Duration durations = null ;
			Bank bank = new Bank();
	    	Duration durationparam= null;
	     	if (refundPeriod <=5){
	     		durationparam = durations.ONE_TO_FIVE;	
	     	}
	     	
	     	else if (refundPeriod>5 && refundPeriod<=10){
	     		durationparam = durations.FIVE_TO_TEN;
	     	}
	     	
	     	else if(refundPeriod>10){
	     		durationparam = durations.TEN_PLUS;
	     	}

			Offre offre = new Offre();
			//OffreRepository.findBySelf_finance_rate(bank.getId(), durationparam);
//			System.err.println(self_finance);
//			System.err.println(self_finance_rate);
//			System.err.println(creditAmount);
//			System.err.println(self_finance_rate*creditAmount);
			if (self_finance>=(self_finance_rate/(float)100)*creditAmount){
				return "The auto-finance submits to the banks criterias for a credit";
				
		}
			//else if (self_finance<offreRepository.findBySelf_finance_rate(bank.getId(), durationparam)*creditAmount)
			else if (self_finance<(self_finance_rate/(float)100)*creditAmount){
				return "Its impossible to take the credit with the autofinance submitted";
			}
			
		return "nothing";
		}



		@Override
		public String monthlyPaybackCompraison(int refundPeriod, float creditAmount, String name, String name2, int interest_rate, int interest_rate2) {
			// TODO Auto-generated method stub
			
			float c =(float)(interest_rate/(float)100*1.0);
			float a = ((creditAmount*c)/12);
			float b =(float)(1-Math.pow((1+((float)c/(float)12*1.0)),-refundPeriod*12));
//			System.err.println(c);
//			System.err.println(a);
//			System.err.println(b);
			float d=(float)Math.abs((float)a/(float)b);
			
			
			float c1 =(float)(interest_rate2/(float)100*1.0);
			float a1 = ((creditAmount*c1)/12);
			float b1 =(float)(1-Math.pow((1+((float)c1/(float)12*1.0)),-refundPeriod*12));
//			System.err.println(c1);
//			System.err.println(a1);
//			System.err.println(b1);
			float d1=(float)Math.abs((float)a1/(float)b1);
//			System.err.println(d);
//			System.err.println(d1);
			if (d<d1){
				return "The first bank provides a more optimal monthly payback";
			}
			else if (d>d1){
				return "The second bank provides a more optimal monthly payback";
			}

			
			
			
			
			return "They both provide a credit with the same monthly payback amount";
		}



		@Override
		public String priceCompraison(int refundPeriod, float creditAmount, String name, String name2,
				int interest_rate, int interest_rate2) {
			
			
			// TODO Auto-generated method stub
			float c =(float)(interest_rate/(float)100*1.0);
			float a = ((creditAmount*c)/12);
			float b =(float)(1-Math.pow((1+((float)c/(float)12*1.0)),-refundPeriod*12));
//			System.err.println(c);
//			System.err.println(a);
//			System.err.println(b);
			float d=(float)Math.abs((float)a/(float)b);
			
			
			float c1 =(float)(interest_rate2/(float)100*1.0);
			float a1 = ((creditAmount*c1)/12);
			float b1 =(float)(1-Math.pow((1+((float)c1/(float)12*1.0)),-refundPeriod*12));
//			System.err.println(c1);
//			System.err.println(a1);
//			System.err.println(b1);
			float d1=(float)Math.abs((float)a1/(float)b1);

			float e=d*12*refundPeriod;
			float e1=d1*12*refundPeriod;
//			System.err.println(e);
//			System.err.println(e1);
			if (e<e1){
				return "The first bank provides an overall less total cost then the second bank";
			}
			else if (e>e1){
				return "The second bank provides an overall less total cost then the first bank";
			}

			
			
			
			
			return "They both provide a credit with the same total cost";
		}



		@Override
		public String documentFeesCompraison(int refundPeriod, float creditAmount, String name, String name2,
				int interest_rate, int interest_rate2) {
			// TODO Auto-generated method stub
			//return null;
			Bank bank = new Bank();
			Bank bank1 = new Bank();
			//bank = bankrepository.findByName(name).;
			for (int i = 0; i < bankrepository.findByName(name).size(); i++) {
				bank = bankrepository.findByName(name).get(i);
//				System.err.println("voila");
//				System.err.println(bank.getDocument_fees_interest());
			}
			
			for (int i = 0; i < bankrepository.findByName(name2).size(); i++) {
				bank1 = bankrepository.findByName(name2).get(i);
//				System.err.println("voila");
//				System.err.println(bank1.getDocument_fees_interest());
			}
			
			
			float c =(float)(interest_rate/(float)100*1.0);
			float a = ((creditAmount*c)/12);
			float b =(float)(1-Math.pow((1+((float)c/(float)12*1.0)),-refundPeriod*12));
//			System.err.println(c);
//			System.err.println(a);
//			System.err.println(b);
			float d=(float)Math.abs((float)a/(float)b);
			
			
			float c1 =(float)(interest_rate2/(float)100*1.0);
			float a1 = ((creditAmount*c1)/12);
			float b1 =(float)(1-Math.pow((1+((float)c1/(float)12*1.0)),-refundPeriod*12));
//			System.err.println(c1);
//			System.err.println(a1);
//			System.err.println(b1);
			float d1=(float)Math.abs((float)a1/(float)b1);

			float e=d*12*refundPeriod;
			float e1=d1*12*refundPeriod;
			float f=e*bank.getDocument_fees_interest()/(float)100;
			float f1=e1*bank1.getDocument_fees_interest()/(float)100;
//			System.err.println(f);
//			System.err.println(f1);
			//simulationpk.setDocument_fees(simulationpk.getPrice()*(bank.getDocument_fees_interest()/(float)100));
			if (f<f1){
				return "The first bank provides an overall less document fees cost then the second bank";
			}
			else if (f>f1){
				return "The second bank provides an overall less document fees cost then the first bank";
			}

			
		
			return "They both provide a credit with the document fees cost";
			
			
		}



		@Override
		public String selfFinanceBanksComparaison(String name, String name2,int idOffre,int idOffre2) {
			// TODO Auto-generated method stub
			//return null;
			Offre offre = offreRepository.findById(idOffre).get();
			Offre offre2 = offreRepository.findById(idOffre2).get();
			System.err.println(offre.getSelf_finance_rate());
			System.err.println(offre2.getSelf_finance_rate());
			
			if (offre.getSelf_finance_rate()<offre2.getSelf_finance_rate()){
				return "The first bank demands less self finance amount of money then the second bank ";
			}
			else if (offre.getSelf_finance_rate()>offre2.getSelf_finance_rate()){
				return "The second bank demands less self finance amount of money then the first bank";
			}

			
		
			return "Both banks demand the same self finance amount of money";
			
			
		}



		@Override
		public String FindByOffer(int idOffre) {
			// TODO Auto-generated method stub
//			
//			
//			List<String> list = Simulation_FavorisRepository.FindByOffer();
//			List<Integer> list1;
//			Map<String, Integer> map = new HashMap<>();
//			for (int i = 0; i < list.size(); i++) {
//				if (map.containsKey(list.get(i)))
//					map.replace(list.get(i), map.get(list.get(i)) + 1);
//				// Sinon on l'ajoute
//				else {
//					map.put(list.get(i), 1);
//				}
//			}
//			Set set = map.entrySet();// Converting to Set so that we can traverse
//			Iterator itr = set.iterator();
//			while (itr.hasNext()) {
//				// Converting to Map.Entry so that we can get key and value
//				// separately
//				Map.Entry entry = (Map.Entry) itr.next();
//				System.err.println(entry.getKey() + " " + entry.getValue());
//			}
			return "eerzrz";
//		}
		}



		@Override
		public List<Simulation_Favoris> getSimulationsById(String userName) {
			// TODO Auto-generated method stub
			
			User user = new User();

//			user=userRepository.findByUserName(userName).get();
			user=userRepository.findByUserName(userName).get();
//			simulationpk.setIdUser(user.getId());
			
			System.err.println(user.getId());
			return Simulation_FavorisRepository.getSimulationsById(user.getId());
		}
		
		@Override
		public List<Simulation_Favoris> getAllSimulations() {
			// TODO Auto-generated method stub
			
//			User user = new User();
//
////			user=userRepository.findByUserName(userName).get();
//			user=userRepository.findByUserName(userName).get();
////			simulationpk.setIdUser(user.getId());
//			
//			System.err.println(user.getId());
//			return Simulation_FavorisRepository.getSimulationsById(user.getId());
			return (List<Simulation_Favoris>) Simulation_FavorisRepository.findAll();
		}



		@Override
		public List <String> findByName() {
//			System.err.println(bankrepository.findByName());
			// TODO Auto-generated method stub
			return bankrepository.findBankNames();
		}



		@Override
		public float calculerTotal(float refundPeriod, float creditAmount, String name) {
			int interest_rate;
			float total;
			float monthlyPayback;
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
			Simulation simulation = new Simulation();
			SimulationPK simulationpk = new SimulationPK();
			Bank bank = new Bank();
			//bank = bankrepository.findByName(name).;
			for (int i = 0; i < bankrepository.findByName(name).size(); i++) {
				bank = bankrepository.findByName(name).get(i);

			}
			
/*
		isimulationservice.ajoutSimulation(creditAmount, refundPeriod, name,
				OffreRepository.FindByInterest(bank.getId(), durationparam), Home.connectedUser,
				OffreRepository.offreid(bank.getId(), durationparam), monthlyCapacity, self_finance);
 */
			
			interest_rate=offreRepository.FindByInterest(bank.getId(), durationparam);
			
//			simulationpk.setCreditAmount(creditAmount);
//			simulationpk.setRefundPeriod(refundPeriod);

			float c =(float)(interest_rate/(float)100*1.0);
			float a = ((creditAmount*c)/12);
			float b =(float)(1-Math.pow((1+((float)c/(float)12*1.0)),-refundPeriod*12));


			
			
			simulationpk.setPrice((simulationpk.getMonthleyPayBack()*12*refundPeriod));
//			simulationpk.setMonthleyPayBack((float)Math.abs((float)a/(float)b));

			monthlyPayback=(float)Math.abs((float)a/(float)b);
			

			System.out.println(creditAmount);
			System.out.println(refundPeriod);
			System.out.println(name);
			total=monthlyPayback*12*refundPeriod;
			System.out.println(total);
			
			System.err.println(creditAmount);
			System.err.println(refundPeriod);
			System.err.println(name);

			System.err.println(total);
			
			// TODO Auto-generated method stub
//			return 0;
			//simulationpk.setMonthleyPayBack((float)Math.abs((float)a/(float)b));
			return total;
//			return refundPeriod+creditAmount+bank.getDocument_fees_interest();
		}
//}
//		
//		
//		
//		
//		
//		
//		//simulationpk.setPrice((simulationpk.getMonthleyPayBack()*12*refundPeriod));
		
}
