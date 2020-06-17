package com.example.service;

import java.util.Date;
import java.util.List;

import com.example.entity.Bank;
import com.example.entity.Offre;




public interface IBankService {
	public int ajouterBank(Bank bank);
	void affecterOffreABank(int offreId, int bankId);
	public void deleteBankById(int bankId);
	public List<Bank> getBankById(String userName);
	public void updateBankById(Bank bank, int bankId);
	
	//public List<Employe> getAllEmployeByMission(int missionId);
	public List<Offre> getAllOffresByBank(String userName);
	//public void mettreAjourEmailByEmployeId(String email, int employeId);
	
	public int addOrUpdateBank(Bank bank);
}
