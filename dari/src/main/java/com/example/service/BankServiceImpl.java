package com.example.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Bank;
import com.example.entity.Offre;
import com.example.repository.BankRepository;

//import tn.esprit.spring.entities.Bank;
//import tn.esprit.spring.entities.Offre;
//import tn.esprit.spring.repository.BankRepository;

@Service
public class BankServiceImpl implements IBankService {
	
	
	@Autowired
	BankRepository ibankrepository;
	
	

	@Override
	public int ajouterBank(Bank bank) {
		ibankrepository.save(bank);
		// TODO Auto-generated method stub
		return bank.getId();
	}

	@Override
	public void affecterOffreABank(int offreId, int bankId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBankById(int bankId) {
		// TODO Auto-generated method stub
		Bank bank = ibankrepository.findById(bankId).get();

		//Desaffecter l'employe de tous les departements
		//c'est le bout master qui permet de mettre a jour
		//la table d'association
		/*for(Departement dep : employe.getDepartements()){
			dep.getEmployes().remove(employe);
		}*/

		ibankrepository.delete(bank);
		
	}

	@Override
	public Bank getBankById(int BankId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Offre> getAllOffresByBank(int bankId) {
		// TODO Auto-generated method stub
		//return null;
		return ibankrepository.getAllOffresByBankRepo(bankId);
	}

	@Override
	public void updateBankById(Bank bank, int bankId) {
		Bank bank1 = new Bank();
		bank1 = ibankrepository.findById(bankId).get();
		bank1.setName(bank.getName());
		bank1.setAdress(bank.getAdress());
		bank1.setEmail(bank.getEmail());
		bank1.setPhone(bank.getPhone());
		/*bank.setAdress(adress);
		bank.setName(name);
		bank.setPhone(phone);
		bank.setEmail(email);*/
		ibankrepository.save(bank1);
		 
		// TODO Auto-generated method stub
		
	}

/*
	public List<Employe> getAllEmployeByMission(int missionId) {
		return timesheetRepository.getAllEmployeByMission(missionId);
	} 
 */

	/*@Override
	public List<Offre> getAllOffresByBank(Bank bank) {
		// TODO Auto-generated method stub
		//return ibankrepository.getAllOffresByBankRepo(bank);
		return null;
	}
	*/
/*
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	} 
 */
	
	@Override
	public int addOrUpdateBank(Bank bank) {
		ibankrepository.save(bank);
		return bank.getId();
	}
	
	
}
