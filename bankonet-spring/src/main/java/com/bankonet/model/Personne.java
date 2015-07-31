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
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISCRIMINATOR")
@Component("personne")
@Scope("prototype")
public class Personne {
	
	@TableGenerator(
			  name = "yourTableGenerator",
			  allocationSize = 1)
	@Id
	@GeneratedValue(
			  strategy=GenerationType.TABLE, 
			  generator="yourTableGenerator")
	private int id;
	@NotNull
	@Size(min=3,max=60)
	private String nom;
	@Size(min=3,max=60)
	private String prenom;
	
	public Personne(){}
	
	public Personne(String pNom, String pPrenom){
		this.nom=pNom;
		this.prenom=pPrenom;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
}
