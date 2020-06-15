package com.example.service;

import com.example.entity.Insurance_Offer;


public interface IInsuranceOfferService {
	public int ajouterInsuranceOffer(Insurance_Offer insuranceOffer,String userName);
//	void affecterOffreABank(int offId, int bankId);
	public Insurance_Offer getInsuranceOfferById(int idOfferInsurance);
	public void updateInsuranceOfferById(Insurance_Offer insuranceOffer, int idOfferInsurance,String userName);
	public void deleteInsuranceOfferById(int idOfferInsurance,String userName);

}
