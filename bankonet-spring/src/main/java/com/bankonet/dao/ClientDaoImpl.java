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


@Repository("clientDao")
public class ClientDaoImpl implements IClientDao{

	
	@PersistenceContext
	private EntityManager em;


	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Transactional(propagation=Propagation.MANDATORY)
	public void addClient(Client c){
		System.out.println("Dans ClientDaoImpl.addClient : client ajouté!");
		em.persist(c);
	}

	@Transactional(propagation=Propagation.MANDATORY)
	public List<Client> listClients() {
		System.out.println("Dans ClientDaoImpl.listClients : liste de clients retournée!");
		List<Client> employes=new ArrayList<Client>();
		try{
			String textQuery="Select c From Client as c ORDER BY c.id";
			TypedQuery<Client> query=em.createQuery(textQuery, Client.class);

			employes=query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return employes;
	}

	@Transactional(propagation=Propagation.MANDATORY)
	public void deleteClient(int idClient) {
		System.out.println("Dans ClientDaoImpl.deleteClient : client supprimé!");
		Client c=em.find(Client.class, idClient);
		em.remove(c);

	}

	@Transactional(propagation=Propagation.MANDATORY)
	public Client editClient(int idClient) {
		System.out.println("Dans ClientDaoImpl.editClient : client modifié!");
		Client c=em.find(Client.class, idClient);
		return c;
	}

	@Transactional(propagation=Propagation.MANDATORY)
	public void updateClient(Client c) {
		System.out.println("Dans ClientDaoImpl.updateClient : client modifié!");
		em.merge(c);
	}

	@Transactional(propagation=Propagation.MANDATORY)
	public List<Client> chercherClients(String motCle) {
		System.out.println("Dans ClientDaoImpl.chercherClients : recherche de client en cours!");
		List<Client> employes=new ArrayList<Client>();
		try{
			String textQuery="Select c From Client as c WHERE c.nom LIKE :motCle OR c.prenom LIKE :motCle";

			TypedQuery<Client> query=em.createQuery(textQuery, Client.class);
			query.setParameter("motCle", motCle);

			employes=query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return employes;
	}

}
