package com.example.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.entity.Bank;
import com.example.entity.SimulationPK;
import com.example.entity.Simulation_Favoris;



public interface ISimulationService {
	public void ajouterSimulation(int idUser, int idOffre, Date date, float monthleyPayBack, float creditAmount,
			int refundPeriod, float salary, float price,float monthlyCapacity);
	public void Simuler(int idUser, int idOffre, SimulationPK simulationpk);
	public void deleteSimulationById(SimulationPK simulationpk);
	public void Simuler2(int idUser, int idOffre, SimulationPK simulationpk);
	public void ajoutSimulation(float creditAmount,int refundPeriod,String name,int interest_rate,String userName,int idOffre,float monthlyCapacity,float self_finance);
	public void saveSimulation(float creditAmount,int refundPeriod,String name,int interest_rate,String userName,int idOffre,float monthlyCapacity,float self_finance);
	public Simulation_Favoris getSimulationById(int id_simfav,String userName);
	public void deleteSimulation_FavorisById(int id_simfav);
	public List<Simulation_Favoris> getSimulationsById(String userName);
	//public List<String> FindByData(int refundPeriod,float creditAmount,float monthly_capacity);
	public String FindByData(int refundPeriod,float creditAmount,float monthly_capacity);
	public String selfFinanceComparaison(float self_finance,int refundPeriod,float creditAmount,String name,int self_finance_rate);
	public String monthlyPaybackCompraison(int refundPeriod,float creditAmount,String name,String name2, int interest_rate, int interest_rate2);
	public String priceCompraison(int refundPeriod,float creditAmount,String name,String name2, int interest_rate, int interest_rate2);
	public String documentFeesCompraison(int refundPeriod,float creditAmount,String name,String name2, int interest_rate, int interest_rate2);
	public String selfFinanceBanksComparaison(String name, String name2,int idOffre,int idOffre2);
	public String FindByOffer(int idOffre);
	public List<Simulation_Favoris> getAllSimulations();
	public List<String> findByName();
	public float calculerTotal(float refundPeriod,float creditAmount,String name);
}
