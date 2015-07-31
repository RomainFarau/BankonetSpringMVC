package com.bankonet.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Sysdeo
 * Modifi� par Rfarau
 */
@Entity
@DiscriminatorValue("CC")
@Component("compteCourant")
@Scope("prototype")
public class CompteCourant extends Compte {
	@NotNull
    private float decouvertAutorise;



	public CompteCourant(){
	}
	
    public CompteCourant(int id, String libelle, float solde,
            float decouvertAutorise) {

        super(id, libelle, solde);

        this.decouvertAutorise = decouvertAutorise;
    }

    public boolean creditAutorise(float montant){
        return true;
    }

    public boolean debitAutorise(float montant){
        if (this.getSolde() + this.getDecouvertAutorise() >= montant) {
            return true;
        }
        return false;
    }

    public float getDecouvertAutorise() {
        return decouvertAutorise;
    }
    
	public void setDecouvertAutorise(float decouvertAutorise) {
		this.decouvertAutorise = decouvertAutorise;
	}
}