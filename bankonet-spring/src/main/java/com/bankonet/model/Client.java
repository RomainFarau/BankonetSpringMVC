package com.bankonet.model;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.eclipse.persistence.annotations.PrivateOwned;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@DiscriminatorValue("C")
@Component("client")
@Scope("prototype")
public class Client extends Personne{
	
	@Autowired
	@Embedded
	private Adresse adresse;
	@NotNull
	@Size(min=2,max=16)
	private String login;
	@NotNull
	@Size(min=6,max=50)
	private String motDePasse;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="client", cascade=CascadeType.ALL)
	@PrivateOwned //Pour liaison bdd
	private List<CompteEpargne> comptesEpargneClient=new ArrayList<CompteEpargne>();
	  
	@OneToMany(fetch=FetchType.EAGER, mappedBy="client", cascade=CascadeType.ALL)
	@PrivateOwned
	private List<CompteCourant> comptesCourantClient=new ArrayList<CompteCourant>();
	
	

	public Client(){}
	
	public Client(Adresse pAdresse){
		this.adresse=pAdresse;
		
	}
	
	public Client(String pNom, String pPrenom, Adresse pAdresse, String pLogin, String pMdp){
		super(pNom,pPrenom);
		this.adresse=pAdresse;
		this.login=pLogin;
		this.motDePasse=pMdp;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	
	
	public String toString(){
		return "id : "+Integer.toString(getId())+
				", nom : "+getNom()+
				", prenom : "+getPrenom()+
				", "+adresse.toString()+
				", "+getLogin()+
				", "+getMotDePasse()+"\n";
	}
	
	public List<CompteCourant> getComptesCourantClient() {
		return comptesCourantClient;
	}

	public void setComptesCourantClient(List<CompteCourant> comptesClient) {
		this.comptesCourantClient = comptesClient;
	}
	
	public void ajouterCompteCourant(CompteCourant c){
		c.setClient(this);
		this.comptesCourantClient.add(c);
	}
	
	public void deleteCompteCourant(CompteCourant c){
		Iterator<CompteCourant> it=comptesCourantClient.iterator();
		while(it.hasNext()){
			CompteCourant item=it.next();
			if(item.getIdentifiant()==c.getIdentifiant()){
				it.remove();
			}
		}
		
	}
	
	public void modifierCompteCourant(CompteCourant c){
		int supr=-1;
		for(CompteEpargne compte: comptesEpargneClient){
			
			if(c.getIdentifiant()==compte.getIdentifiant()){
				supr++;
				break;
			}
			supr++;
		}
		comptesCourantClient.set(supr, c);
	}
	
	public Compte getCompteCourantById(int id){
		return comptesCourantClient.get(id);
	}
	
	public List<CompteEpargne> getComptesEpargneClient() {
		return comptesEpargneClient;
	}

	public void setComptesEpargneClient(List<CompteEpargne> comptesClient) {
		this.comptesEpargneClient = comptesClient;
	}
	
	public void ajouterCompteEpargne(CompteEpargne c){
		c.setClient(this);
		this.comptesEpargneClient.add(c);
	}
	
	public void deleteCompteEpargne(CompteEpargne c){
		//Not sames OID, so foreach
		Iterator<CompteEpargne> it=comptesEpargneClient.iterator();
		while(it.hasNext()){
			CompteEpargne item=it.next();
			if(item.getIdentifiant()==c.getIdentifiant()){
				it.remove();
			}
		}
	}
	
	public void modifierCompteEpargne(CompteEpargne c){
		int supr=-1;
		for(CompteEpargne compte: comptesEpargneClient){
			
			if(c.getIdentifiant()==compte.getIdentifiant()){
				supr++;
				break;
			}
			supr++;
		}
		comptesEpargneClient.set(supr, c);
	}
	
	public CompteEpargne getCompteEpargneById(int id){
		return comptesEpargneClient.get(id);
	}
	
	
	
	
}
