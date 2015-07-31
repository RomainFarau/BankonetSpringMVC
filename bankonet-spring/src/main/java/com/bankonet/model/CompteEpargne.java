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
@DiscriminatorValue("CE")
@Component("compteEpargne")
@Scope("prototype")
public class CompteEpargne extends Compte {
	@NotNull
    private float tauxInteret;
	@NotNull
    private float plafond;

	public CompteEpargne(){
		
	}
	

    public CompteEpargne(int id, String libelle, float solde,
            float tauxInteret, float plafond) {
        super(id, libelle, solde);
        this.tauxInteret = tauxInteret;
        this.plafond = plafond;
    }

    public boolean creditAutorise(float montant){
        if (montant < getPlafond()) {
            return true;
        } 
        return false;
    }

    public boolean debitAutorise(float montant){
        if (getSolde() - montant >= 0) {
            return true;
        } 
        return false;
    }



    public float getPlafond() {
        return plafond;
    }


    public void setPlafond(float newPlafond) {
        plafond = newPlafond;
    }


    public float getTauxInteret() {
        return tauxInteret;
    }


    public void setTauxInteret(float newTauxInteret) {
        tauxInteret = newTauxInteret;
    }
}