package com.bankonet.metier;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.bankonet.dao.IClientDao;
import com.bankonet.model.Client;

@Service("clientMetier")
@Scope("prototype")
public class ClientMetierImpl implements IClientMetier{
	
	TransactionTemplate transactionTemplate=new TransactionTemplate();

	
	@Resource(name="clientDao")
	private IClientDao clientDao;
	
	
	
	public ClientMetierImpl(){
		
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void addClient(Client c){	
		clientDao.addClient(c);
	}

	@Transactional(readOnly=true)
	public List<Client> listClients() {

		return clientDao.listClients();
	}

	@Transactional
	public void deleteClient(int idClient) {
		clientDao.deleteClient(idClient);
	}

	@Transactional
	public Client editClient(int idClient) {
		return clientDao.editClient(idClient);
	}

	@Transactional
	public void updateClient(Client c) {
		clientDao.updateClient(c);
	}

	@Transactional(readOnly=true)
	public List<Client> chercherClients(String motCle) {
		return clientDao.chercherClients(motCle);
	}

	@Transactional
	public List<Client> SupprimerClientDontLeNomContient(String mot_cle) {
		List<Client> clients= clientDao.chercherClients(mot_cle);
		if(clients!=null){
			for(Client client :clients){
				clientDao.deleteClient(client.getId());
			}
		}
		return clientDao.listClients();
	}

}
