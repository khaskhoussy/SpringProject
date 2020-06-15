package com.example.service;

import java.util.List;

import com.example.entity.Category;
import com.example.entity.InsuranceSimulation_Favoris;
import com.example.entity.Simulation_Favoris;

public interface IInsuranceSimulationService {
	
	public void ajoutSimulation(int number_rooms, int number_floors, int house_age,
			int house_value,int goods_value,Category category,String name,boolean fireSafety,boolean waterDamage,boolean robbery,String userName,int idOffre,
	 		float interest_category, float interest_goods);
	
	public void saveSimulation(int number_rooms, int number_floors, int house_age,
			int house_value,int goods_value,Category category,String name,boolean fireSafety,boolean waterDamage,boolean robbery,String userName,int idOffre,
	 		float interest_category, float interest_goods);

	public InsuranceSimulation_Favoris getSimulationById(int id_simfav,String userName);
	public List<InsuranceSimulation_Favoris> getSimulationsById(String userName);
	public void deleteSimulation_FavorisById(int id);
}
