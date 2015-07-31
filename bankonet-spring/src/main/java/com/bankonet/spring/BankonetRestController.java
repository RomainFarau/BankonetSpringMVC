package com.bankonet.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bankonet.metier.IClientMetier;
import com.bankonet.model.Client;

@Controller
@RequestMapping(value="/clients")
public class BankonetRestController {

	@Autowired
	IClientMetier bankonetMetier;

	@RequestMapping(value="listClients/{keyWord}", method=RequestMethod.GET)
	@ResponseBody
	public List<Client> searchByKeyWord(@PathVariable("keyWord") String keyWord){
		return bankonetMetier.chercherClients(keyWord);
	}

	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void addClient(@RequestBody Client c){
		bankonetMetier.addClient(c);
	}

	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteClient(@PathVariable("id") int id) {
		bankonetMetier.deleteClient(id);
	}


}
