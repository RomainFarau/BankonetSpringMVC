package com.bankonet.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Scope;

public class Virement {

	private Compte compteSource;
	private Compte compteDest;
	private float montant;
	
	public Virement(){
		
	}
	
	public Virement(Compte pSource, Compte pDest, float pMontant){
		this.compteDest=pDest;
		this.compteSource=pSource;
		this.montant=pMontant;
	}
	
	public void run(){
		this.compteDest.setSolde(this.compteDest.getSolde()+this.montant);
		this.compteSource.setSolde(this.compteSource.getSolde()-this.montant);
	}
	
	public Compte getCompteSource() {
		return compteSource;
	}
	public void setCompteSource(Compte compteSource) {
		this.compteSource = compteSource;
	}
	public Compte getCompteDest() {
		return compteDest;
	}
	public void setCompteDest(Compte compteDest) {
		this.compteDest = compteDest;
	}
	public float getMontant() {
		return montant;
	}
	public void setMontant(float montant) {
		this.montant = montant;
	}
	
}
