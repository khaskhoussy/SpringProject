package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Bank;
import com.example.entity.Insurance;
import com.example.repository.InsuranceRepository;



@Service
public class InsuranceServiceImpl implements IInsuranceService {

	
	@Autowired
	InsuranceRepository iinsurancerepository;
	
	
	@Override
	public int ajouterInsurance(Insurance insurance) {
		if(insurance.getInterest_age()<1 && insurance.getInterest_firesafety()<1 && insurance.getInterest_robbery()<1 && insurance.getInterest_waterDamage()<1){
			iinsurancerepository.save(insurance);	
		}
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
		return insurance.getId();
	}

	@Override
	public void deleteInsuranceById(int insuranceId) {
		Insurance insurance = iinsurancerepository.findById(insuranceId).get();
		iinsurancerepository.delete(insurance);
		
	}

	@Override
	public Bank getInsuranceById(int insuranceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateInsuranceById(Insurance insurance, int insuranceId) {
		// TODO Auto-generated method stub
		Insurance insurance1 = new Insurance();
		insurance1 = iinsurancerepository.findById(insuranceId).get();
		insurance1.setAdress(insurance.getAdress());
		insurance1.setDescription(insurance.getDescription());
		insurance1.setEmail(insurance.getEmail());
		insurance1.setInterest_age(insurance.getInterest_age());
		insurance1.setInterest_firesafety(insurance.getInterest_firesafety());
		insurance1.setInterest_robbery(insurance.getInterest_robbery());
		insurance1.setInterest_waterDamage(insurance.getInterest_waterDamage());
		insurance1.setName(insurance.getName());
		insurance1.setPhone(insurance.getPhone());
		
		if(insurance1.getInterest_age()<1 && insurance1.getInterest_firesafety()<1 && insurance1.getInterest_robbery()<1 && insurance1.getInterest_waterDamage()<1){
			iinsurancerepository.save(insurance1);	
		}

	}



}
