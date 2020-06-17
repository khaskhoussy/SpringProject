package com.example.service;

import java.util.List;

import com.example.entity.Bank;
import com.example.entity.Insurance;
import com.example.entity.Insurance_Offer;


public interface IInsuranceService {
	public int ajouterInsurance(Insurance insurance);

	public void deleteInsuranceById(int insuranceId);
	public Bank getInsuranceById(int insuranceId);
	public void updateInsuranceById(Insurance insurance, int insuranceId);
	public List<Insurance> getInsuranceById(String userName);
	public int addOrUpdateInsurance(Insurance insurance);
	public List<Insurance_Offer> getAllOffresByInsurance(String userName);
}
