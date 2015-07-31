package com.bankonet.dao;

import java.util.List;

import com.bankonet.model.Compte;
import com.bankonet.model.CompteCourant;
import com.bankonet.model.CompteEpargne;

public interface ICompteDao { 
	public void addCompte(Compte c);
	public List<Compte> listCompte();
	public List<CompteEpargne> listCompteEpargne(int idClient);
	public List<Compte> listCompteCourant();
	public void deleteCompte(int idCompte);
	public void updateCompte(Compte c);
	public CompteEpargne editCompteEpargne(int idCompte);
	public CompteCourant editCompteCourant(int idCompte);

	
}
