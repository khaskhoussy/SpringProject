package com.example.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Category;
import com.example.entity.Insurance;
import com.example.entity.InsuranceSimulation_Favoris;
import com.example.entity.Insurance_Simulation;
import com.example.entity.Insurance_SimulationPK;
import com.example.entity.Simulation_Favoris;
import com.example.entity.User;
import com.example.repository.InsuranceRepository;
import com.example.repository.Insurance_SimulationFavorisRepository;
import com.example.repository.Insurance_SimulationRepository;
import com.example.repository.UserRepository;


@Service
public class InsuranceSimulationServiceImpl implements IInsuranceSimulationService{
	
	@Autowired
	InsuranceRepository insurancerepository;
	
	@Autowired
	Insurance_SimulationRepository insuranceSimulationRepository;
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Insurance_SimulationFavorisRepository insuranceSimulationFavorisRepository;

	@Override
	public void ajoutSimulation(int number_rooms, int number_floors, int house_age,
			int house_value,int goods_value,Category category,String name,boolean fireSafety,boolean waterDamage,boolean robbery,String userName,int idOffre,
	 		float interest_category, float interest_goods) {
		
		
		Insurance_Simulation insurance_simulation = new Insurance_Simulation();
		Insurance_SimulationPK insurance_simulationPK = new Insurance_SimulationPK();
		Insurance insurance = new Insurance();
		
		System.err.println(interest_category);
		System.err.println(interest_goods);
		for (int i = 0; i < insurancerepository.findByName(name).size(); i++) {

			insurance = insurancerepository.findByName(name).get(i);

		}
		
		Date date=java.util.Calendar.getInstance().getTime(); 
		insurance_simulationPK.setDate(date);
		
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
		
		float robbery_total =(float)((insurance.getInterest_robbery()*(house_value+goods_value))/(float)100*1.0);
		float fireSafety_total =(float)((insurance.getInterest_firesafety()*(house_value+goods_value))/(float)100*1.0);
		float waterDamage_total =(float)((insurance.getInterest_waterDamage()*(house_value+goods_value))/(float)100*1.0);
		
		float category_total=(float)((interest_category*(house_value+goods_value))/(float)100*1.0);
		float goods_total=(float)((interest_goods*(house_value+goods_value))/(float)100*1.0);
		
		
		
		System.err.println(robbery_total);
		System.err.println(fireSafety_total);
		System.err.println(waterDamage_total);
		insurance_simulationPK.setHouse_age(house_age);
		insurance_simulationPK.setNumber_floors(number_floors);
		insurance_simulationPK.setNumber_rooms(number_rooms);
		insurance_simulationPK.setGoods_value(goods_value);
		insurance_simulationPK.setHouse_value(house_value);
		insurance_simulationPK.setTotal((robbery_total+fireSafety_total+waterDamage_total+category_total+goods_total)*(rooms_interest*floors_interest*age_interest));
		float total =(robbery_total+fireSafety_total+waterDamage_total+category_total+goods_total)*(rooms_interest*floors_interest*age_interest);
		insurance_simulationPK.setMonthlyPayback(total/12);

		
		User user = new User();
		user=userRepository.findByUserName(userName).get();
		insurance_simulationPK.setIdUser(user.getId());
		insurance_simulationPK.setIdOffre(idOffre);


		
		insurance_simulation.setInsurance_simulationPK(insurance_simulationPK);
		insuranceSimulationRepository.save(insurance_simulation);
		

	}
	
	
	@Override
	public void saveSimulation(int number_rooms, int number_floors, int house_age,
			int house_value,int goods_value,Category category,String name,boolean fireSafety,boolean waterDamage,boolean robbery,String userName,int idOffre,
	 		float interest_category, float interest_goods) {
		
		
		Insurance_Simulation insurance_simulation = new Insurance_Simulation();
		Insurance_SimulationPK insurance_simulationPK = new Insurance_SimulationPK();
		Insurance insurance = new Insurance();
		InsuranceSimulation_Favoris insuranceSimulation_favoris = new InsuranceSimulation_Favoris();
		
		System.err.println(interest_category);
		System.err.println(interest_goods);
		for (int i = 0; i < insurancerepository.findByName(name).size(); i++) {

			insurance = insurancerepository.findByName(name).get(i);

		}
		
		Date date=java.util.Calendar.getInstance().getTime(); 
		insuranceSimulation_favoris.setDate(date);
		
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
		
		float robbery_total =(float)((insurance.getInterest_robbery()*(house_value+goods_value))/(float)100*1.0);
		float fireSafety_total =(float)((insurance.getInterest_firesafety()*(house_value+goods_value))/(float)100*1.0);
		float waterDamage_total =(float)((insurance.getInterest_waterDamage()*(house_value+goods_value))/(float)100*1.0);
		
		float category_total=(float)((interest_category*(house_value+goods_value))/(float)100*1.0);
		float goods_total=(float)((interest_goods*(house_value+goods_value))/(float)100*1.0);
		
		
		
		System.err.println(robbery_total);
		System.err.println(fireSafety_total);
		System.err.println(waterDamage_total);
		insuranceSimulation_favoris.setHouse_age(house_age);
		insuranceSimulation_favoris.setNumber_floors(number_floors);
		insuranceSimulation_favoris.setNumber_rooms(number_rooms);
		insuranceSimulation_favoris.setGoods_value(goods_value);
		insuranceSimulation_favoris.setHouse_value(house_value);
		insuranceSimulation_favoris.setTotal((robbery_total+fireSafety_total+waterDamage_total+category_total+goods_total)*(rooms_interest*floors_interest*age_interest));
		float total =(robbery_total+fireSafety_total+waterDamage_total+category_total+goods_total)*(rooms_interest*floors_interest*age_interest);
		insuranceSimulation_favoris.setMonthlyPayback(total/12);
		insuranceSimulation_favoris.setInsuranceName(name);

		User user1 = userRepository.findByUserName(userName).get();
		//insuranceSimulation_favoris.setIdUser(idUser);
		insuranceSimulation_favoris.setUser(user1);
		//insurance_simulationPK.setIdOffre(idOffre);


		
		//insurance_simulation.setInsurance_simulationPK(insurance_simulationPK);
		insuranceSimulationFavorisRepository.save(insuranceSimulation_favoris);
		

	}


	@Override
	public InsuranceSimulation_Favoris getSimulationById(int id_simfav, String userName) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<InsuranceSimulation_Favoris> getSimulationsById(String userName){
		User user = new User();
		user=userRepository.findByUserName(userName).get();
		System.err.println(user.getId());
		// TODO Auto-generated method stub
		return insuranceSimulationFavorisRepository.getSimulationsById(user.getId());
	}
	
	@Override
	public void deleteSimulation_FavorisById(int id) {
		// TODO Auto-generated method stub
		InsuranceSimulation_Favoris sim_fav = insuranceSimulationFavorisRepository.findById(id).get();
		insuranceSimulationFavorisRepository.delete(sim_fav);
		
	}
}
