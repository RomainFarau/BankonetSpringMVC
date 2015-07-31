package com.bankonet.model;


import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * @author Sysdeo
 * Modifi� par Rfarau
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISCRIMINATOR")
@Component("compte")
@Scope("prototype")
public abstract class Compte {
	
	@TableGenerator(
			  name = "yourTableGenerator",
			  allocationSize = 1)
	@Id
	@GeneratedValue(
			  strategy=GenerationType.TABLE, 
			  generator="yourTableGenerator")
	private int identifiant;
	@NotNull
	private String libelle;
	@NotNull
	protected float solde;

	//@JoinColumn(name="clientId")
	private Client client;
	
	public Compte(){
		
	}
	
	Compte(int id, String libelle, float solde) {

		this.identifiant = id;
		this.libelle = libelle;
		this.solde = solde;

	}
	public abstract boolean creditAutorise(float montant);


	public abstract boolean debitAutorise(float montant);

	public String getLibelle() {
		return libelle;
	}
	
	public void setLibelle(String pLibelle) {
		this.libelle=pLibelle;
	}
	
	public void setIdentifiant(int pId){
		this.identifiant=pId;
	}

	public int getIdentifiant() {
		return identifiant;
	}

	public float getSolde() {
		return solde;
	}

	public void setSolde(float newSolde) {
		this.solde = newSolde;
	}
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
