package com.bankonet.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bankonet.model.Client;
import com.bankonet.model.Compte;
import com.bankonet.model.CompteCourant;
import com.bankonet.model.CompteEpargne;


@Repository("compteDao")
public class CompteDaoImpl implements ICompteDao{

	@PersistenceContext
	private EntityManager em;
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	@Transactional(propagation=Propagation.MANDATORY)
	public void addCompte(Compte c){
		System.out.println("Dans CompteDaoImpl.addCompte : compte ajout�!");
		em.persist(c);
	}
	
	@Transactional(propagation=Propagation.MANDATORY)
	public List<Compte> listCompte() {
		System.out.println("Dans CompteDaoImpl.listCompte : liste de comptes retourn�e!");
		List<Compte> comptes=new ArrayList<Compte>();
		try{
			String textQuery="Select c From Compte as c ORDER BY c.identifiant";
			TypedQuery<Compte> query=em.createQuery(textQuery, Compte.class);
			comptes=query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return comptes;
	}

	public List<CompteEpargne> listCompteEpargne(int idClient) {
		System.out.println("Dans CompteDaoImpl.listCompteEpargne : liste de comptes epargne retourn�e!");
		List<CompteEpargne> comptes=new ArrayList<CompteEpargne>();
		try{
			String textQuery="Select c From CompteEpargne as c JOIN Compte as co where type(co)=CompteEpargne and co.client.id=:client";
			/* TODOD*/
			TypedQuery<CompteEpargne> query=em.createQuery(textQuery, CompteEpargne.class);
			query.setParameter("client",idClient);
			comptes=query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return comptes;
	}

	@Transactional(propagation=Propagation.MANDATORY)
	public List<Compte> listCompteCourant() {
		System.out.println("Dans CompteDaoImpl.listCompteCourant : liste de comptes courant retourn�e!");
		List<Compte> comptes=new ArrayList<Compte>();
		try{
			String textQuery="Select c From CompteCourant as c ORDER BY c.identifiant";
			TypedQuery<Compte> query=em.createQuery(textQuery, Compte.class);
			comptes=query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return comptes;
	}

	@Transactional(propagation=Propagation.MANDATORY)
	public void deleteCompte(int idCompte) {
		System.out.println("Dans CompteDaoImpl.deleteCompte : compte supprim�!");
		Compte c=em.find(Compte.class, idCompte);
		em.remove(c);
		em.flush();
	}

	@Transactional(propagation=Propagation.MANDATORY)
	public CompteEpargne editCompteEpargne(int idCompte) {
		System.out.println("Dans CompteDaoImpl.editCompte : compte modifi�!");
		CompteEpargne c=em.find(CompteEpargne.class, idCompte);
		return c;
	}

	@Transactional(propagation=Propagation.MANDATORY)
	public CompteCourant editCompteCourant(int idCompte) {
		System.out.println("Dans CompteDaoImpl.editCompte : compte modifi�!");
		CompteCourant c=em.find(CompteCourant.class, idCompte);
		return c;
	}
	
	
	@Transactional(propagation=Propagation.MANDATORY)
	public void updateCompte(Compte c) {
		System.out.println("Dans CompteDaoImpl.updateCompte : compte modifi�!");
		em.merge(c);
	}

}
