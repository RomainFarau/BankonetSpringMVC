package com.bankonet.metier;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.bankonet.dao.ICompteDao;
import com.bankonet.model.Compte;
import com.bankonet.model.CompteCourant;
import com.bankonet.model.CompteEpargne;

@Service("compteMetier")
@Scope("prototype")
public class CompteMetierImpl implements ICompteMetier{

	TransactionTemplate transactionTemplate=new TransactionTemplate();
	
	@Resource(name="compteDao")
	private ICompteDao compteDao;
	
	public CompteMetierImpl(){
		
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void addCompte(Compte c) {
		compteDao.addCompte(c);
	}

	@Transactional(readOnly=true)
	public List<Compte> listComptes() {
		return compteDao.listCompte();
	}

	public List<CompteEpargne> listComptesEpargne(int idClient) {
		return compteDao.listCompteEpargne(idClient);
	}

	@Transactional(readOnly=true)
	public List<Compte> listComptesCourant() {
		return compteDao.listCompteCourant();
	}

	@Transactional
	public void deleteCompte(int idCompte) {
		compteDao.deleteCompte(idCompte);
		
	}

	@Transactional
	public CompteEpargne editCompteEpargne(int idCompte) {
		return compteDao.editCompteEpargne(idCompte);
	}

	@Transactional
	public CompteCourant editCompteCourant(int idCompte) {
		return compteDao.editCompteCourant(idCompte);
	}
	
	@Transactional
	public void updateCompte(Compte c) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public List<Compte> chercherComptes(String motCle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<Compte> SupprimerCompteDontLeNomContient(String mot_cle) {
		// TODO Auto-generated method stub
		return null;
	}

}
