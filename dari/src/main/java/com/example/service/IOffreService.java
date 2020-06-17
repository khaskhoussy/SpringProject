package com.example.service;

import java.util.List;

import com.example.entity.Offre;



public interface IOffreService {
	public int ajouterOffre(Offre offre,String userName);
	void affecterOffreABank(int offId, int bankId);
	public Offre getOffreById(int OffreId);
	public void updateOffreById(Offre offre, int offreId,String userName);
	public void deleteOffreById(int offreId,String userName);
	public int addOrUpdateOffer(Offre offre);
	//public List<Offre> getAllOffresByBank(Bank bank);
	//public List<Offre> getAllOffres();
}
