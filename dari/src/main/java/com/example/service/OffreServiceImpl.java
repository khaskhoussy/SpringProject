package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Bank;
import com.example.entity.Duration;
import com.example.entity.Offre;
import com.example.entity.User;
import com.example.repository.BankRepository;
import com.example.repository.OffreRepository;
import com.example.repository.UserRepository;




@Service
public class OffreServiceImpl implements IOffreService {
	
	@Autowired
	OffreRepository offreRepository;
	@Autowired
	BankRepository bankRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public int ajouterOffre(Offre offre,String userName) {
//		Offre offre = offreRepository.findById(idOffre).get();
		User user = userRepository.findByUserName(userName).get();
	
		Duration durations= null ;
		boolean a = offreRepository.offreid1(bankRepository.findByName(user.getExpert().getBank_name()).get(0).getId()).contains(durations.ONE_TO_FIVE);
		boolean b = offreRepository.offreid1(bankRepository.findByName(user.getExpert().getBank_name()).get(0).getId()).contains(durations.FIVE_TO_TEN);
		boolean c = offreRepository.offreid1(bankRepository.findByName(user.getExpert().getBank_name()).get(0).getId()).contains(durations.TEN_PLUS);
		
			Bank bank1 = bankRepository.findById(bankRepository.findByName(user.getExpert().getBank_name()).get(0).getId()).get();
			System.err.println(bank1.getId());
	    	List<Duration> list = new ArrayList<>();		 	
		 	System.err.println(bank1.getName());
		 	System.err.println(user.getExpert().getBank_name());
				if (bank1.getName().equals(user.getExpert().getBank_name())&& offre.getDuration().equals(durations.ONE_TO_FIVE) && a==false) {
					offre.setBank(bank1);
					offreRepository.save(offre);
				}
				
				else if (bank1.getName().equals(user.getExpert().getBank_name())&& offre.getDuration().equals(durations.FIVE_TO_TEN) && b==false) {
					offre.setBank(bank1);
					offreRepository.save(offre);
				}
				
				else if (bank1.getName().equals(user.getExpert().getBank_name())&& offre.getDuration().equals(durations.TEN_PLUS) && c==false) {
					offre.setBank(bank1);
					offreRepository.save(offre);
				}
				System.err.println(a);
				System.err.println(b);
				System.err.println(c);
			

				
				return offre.getId();

			
		// TODO Auto-generated method stub
		
	}

	//@Override
	public void affecterOffreABank(int offId, int bankId) {
		// TODO Auto-generated method stub
		Bank entrepriseManagedEntity = bankRepository.findById(bankId).get();
		Offre depManagedEntity = offreRepository.findById(offId).get();
		
		depManagedEntity.setBank(entrepriseManagedEntity);
		offreRepository.save(depManagedEntity);
		
	}

	@Override
	public void updateOffreById(Offre offre, int offreId,String userName) {
		System.err.println(userName);
		Offre offre1 = new Offre();
		offre1 = offreRepository.findById(offreId).get();
		User user = userRepository.findByUserName(userName).get();
		Bank bank1 = bankRepository.findById(bankRepository.findByName(user.getExpert().getBank_name()).get(0).getId()).get();
		System.err.println(bank1.getName());
		System.err.println(bank1.getId());
		System.err.println(user.getExpert().getBank_name());
		if (bank1.getName().equals(user.getExpert().getBank_name())){
			offre1.setDescription(offre.getDescription());
			//offre1.setDuration(offre.getDuration());
			offre1.setInterest_rate(offre.getInterest_rate());
			offre1.setSelf_finance_rate(offre.getSelf_finance_rate());
			
			offreRepository.save(offre1);
		}

		// TODO Auto-generated method stub
		
	}

	@Override
	public Offre getOffreById(int OffreId) {
		// TODO Auto-generated method stub
		return offreRepository.findById(OffreId).get();
	}

	@Override
	public void deleteOffreById(int offreId,String userName) {
		User user = userRepository.findByUserName(userName).get();
		Bank bank1 = bankRepository.findById(bankRepository.findByName(user.getExpert().getBank_name()).get(0).getId()).get();
		
		Offre offre = offreRepository.findById(offreId).get();
		if (bank1.getName().equals(user.getExpert().getBank_name())){
			offreRepository.delete(offre);
		}
		
		// TODO Auto-generated method stub
		
	}

	/*@Override
	public List<Offre> getAllOffres() {
		// TODO Auto-generated method stub
		return (List<Offre>) offreRepository.findAll();
	}
*/
	/*@Override
	public List<Offre> getAllOffresByBank(Bank bank) {
		// TODO Auto-generated method stub
		//return null;
		return offreRepository.getAllOffersByBankRepo(bank);
	}*/
	
	
	
	@Override
	public int addOrUpdateOffer(Offre offre) {
		offreRepository.save(offre);
//		ibankrepository.save(bank);
		return offre.getId();
	}
	
}
