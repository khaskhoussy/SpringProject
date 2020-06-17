package com.example.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Bank;
import com.example.entity.Offre;
import com.example.entity.User;
import com.example.repository.BankRepository;
import com.example.repository.UserRepository;

//import tn.esprit.spring.entities.Bank;
//import tn.esprit.spring.entities.Offre;
//import tn.esprit.spring.repository.BankRepository;

@Service
public class BankServiceImpl implements IBankService {
	
	
	@Autowired
	BankRepository ibankrepository;
	
	@Autowired
	UserRepository userRepository;

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
	public List<Bank> getBankById(String userName) {
		User user = new User();
		user=userRepository.findByUserName(userName).get();
		Bank bank = ibankrepository.findById(ibankrepository.findByName(user.getExpert().getBank_name()).get(0).getId()).get();
		System.err.println(bank.getName());
		if (bank.getName().equals(user.getExpert().getBank_name())){
			return ibankrepository.findByName(bank.getName());
		}
		else {
			return null;
		}
//		String test =ibankrepository.findByExpertBank(user.getExpert().getBank_name();
		// TODO Auto-generated method stub
//		return ibankrepository.findByExpertBank(user.getExpert().getBank_name());
//		return ibankrepository.findById(BankId).get();
	}

	@Override
	public List<Offre> getAllOffresByBank(String userName) {
		// TODO Auto-generated method stub
		//return null;
		User user = new User();
		user=userRepository.findByUserName(userName).get();
		Bank bank = ibankrepository.findById(ibankrepository.findByName(user.getExpert().getBank_name()).get(0).getId()).get();
		System.err.println(bank.getId());
		System.err.println(bank.getName());
		return ibankrepository.getAllOffresByBankRepo(bank.getId());
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
