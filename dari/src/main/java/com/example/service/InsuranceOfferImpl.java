package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Category;
import com.example.entity.Insurance;
import com.example.entity.Insurance_Offer;
import com.example.entity.User;
import com.example.repository.InsuranceRepository;
import com.example.repository.Insurance_OfferRepository;
import com.example.repository.UserRepository;


@Service
public class InsuranceOfferImpl implements IInsuranceOfferService{

	@Autowired
	Insurance_OfferRepository insuranceofferRepository;
	
	@Autowired
	InsuranceRepository insuranceRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public int ajouterInsuranceOffer(Insurance_Offer insuranceOffer,String userName) {
		// TODO Auto-generated method stub
		Category category= null ;
		User user = userRepository.findByUserName(userName).get();
		//insuranceRepository.findByName(user.getExpert().getBank_name()).get(0).getId()
		boolean a = insuranceofferRepository.insuranceoffreid1(insuranceRepository.findByName(user.getExpert_insurance().getInsurance_name()).get(0).getId()).contains(category.Appartement);
		boolean b = insuranceofferRepository.insuranceoffreid1(insuranceRepository.findByName(user.getExpert_insurance().getInsurance_name()).get(0).getId()).contains(category.House);
		boolean c = insuranceofferRepository.insuranceoffreid1(insuranceRepository.findByName(user.getExpert_insurance().getInsurance_name()).get(0).getId()).contains(category.Commercial_Local);
		
			Insurance insurance1 = insuranceRepository.findById(insuranceRepository.findByName(user.getExpert_insurance().getInsurance_name()).get(0).getId()).get();
System.err.println(insurance1.getId());
	    	List<Category> list = new ArrayList<>();		 	
		 	System.err.println(user.getExpert_insurance().getInsurance_name());
		 	System.err.println(insurance1.getName());
				if (insurance1.getName().equals(user.getExpert_insurance().getInsurance_name()) && insuranceOffer.getCategory().equals(category.Appartement) && a==false && insuranceOffer.getInterest_category()<1 && insuranceOffer.getInterest_goods()<1) {
					insuranceOffer.setInsurance(insurance1);
					insuranceofferRepository.save(insuranceOffer);
				}
				if (insurance1.getName().equals(user.getExpert_insurance().getInsurance_name()) && insuranceOffer.getCategory().equals(category.House) && b==false && insuranceOffer.getInterest_category()<1 && insuranceOffer.getInterest_goods()<1) {
					insuranceOffer.setInsurance(insurance1);
					insuranceofferRepository.save(insuranceOffer);
				}
				if (insurance1.getName().equals(user.getExpert_insurance().getInsurance_name()) && insuranceOffer.getCategory().equals(category.Commercial_Local) && c==false && insuranceOffer.getInterest_category()<1 && insuranceOffer.getInterest_goods()<1) {
					insuranceOffer.setInsurance(insurance1);
					insuranceofferRepository.save(insuranceOffer);
				}
				

				System.err.println(a);
				System.err.println(b);
				System.err.println(c);
			

				
				return insuranceOffer.getId();
	}

	@Override
	public Insurance_Offer getInsuranceOfferById(int idOfferInsurance) {
		// TODO Auto-generated method stub
//		return insuranceofferRepository.findById(idOfferInsurance).get();
		return insuranceofferRepository.findById(idOfferInsurance).get();
	}

	@Override
	public void updateInsuranceOfferById(Insurance_Offer insuranceOffer, int idOfferInsurance,String userName) {
		System.err.println(userName);
		User user = userRepository.findByUserName(userName).get();
		System.err.println(user.getId());
		Insurance insurance1 = insuranceRepository.findById(insuranceRepository.findByName(user.getExpert_insurance().getInsurance_name()).get(0).getId()).get();
		// TODO Auto-generated method stub
		Insurance_Offer insuranceOffer1 = new Insurance_Offer();
		insuranceOffer1 = insuranceofferRepository.findById(idOfferInsurance).get();
		//insuranceOffer1.setCategory(insuranceOffer.getCategory());
		if (insurance1.getName().equals(user.getExpert_insurance().getInsurance_name())){
			insuranceOffer1.setInterest_category(insuranceOffer.getInterest_category());
			insuranceOffer1.setInterest_goods(insuranceOffer.getInterest_goods());
			
			insuranceofferRepository.save(insuranceOffer1);			
		}

	}

	@Override
	public void deleteInsuranceOfferById(int idOfferInsurance,String userName) {
		User user = userRepository.findByUserName(userName).get();
		Insurance insurance1 = insuranceRepository.findById(insuranceRepository.findByName(user.getExpert_insurance().getInsurance_name()).get(0).getId()).get();
		Insurance_Offer insuranceOffer1 = insuranceofferRepository.findById(idOfferInsurance).get();
		if (insurance1.getName().equals(user.getExpert_insurance().getInsurance_name())){
			insuranceofferRepository.delete(insuranceOffer1);	
		}
		
		// TODO Auto-generated method stub
		
	}

}
