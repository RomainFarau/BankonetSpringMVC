package com.bankonet.metier;

import java.util.List;

import com.bankonet.model.Compte;
import com.bankonet.model.CompteCourant;
import com.bankonet.model.CompteEpargne;

public interface ICompteMetier {

	public void addCompte(Compte c); 
	public List<Compte> listComptes();
	public List<CompteEpargne> listComptesEpargne(int idClient);
	public List<Compte> listComptesCourant();
	public void deleteCompte(int idCompte);
	public void updateCompte(Compte c);
	public List<Compte> chercherComptes(String	motCle);
	public List<Compte> SupprimerCompteDontLeNomContient(String mot_cle);
	public CompteEpargne editCompteEpargne(int idCompte);
	public CompteCourant editCompteCourant(int idCompte);
}
