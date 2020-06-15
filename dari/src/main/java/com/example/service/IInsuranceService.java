package com.example.service;

import com.example.entity.Bank;
import com.example.entity.Insurance;

public interface IInsuranceService {
	public int ajouterInsurance(Insurance insurance);

	public void deleteInsuranceById(int insuranceId);
	public Bank getInsuranceById(int insuranceId);
	public void updateInsuranceById(Insurance insurance, int insuranceId);
}
