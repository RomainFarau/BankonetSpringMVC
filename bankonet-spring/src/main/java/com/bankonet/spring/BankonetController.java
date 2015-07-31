package com.bankonet.spring;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import com.bankonet.metier.IClientMetier;
import com.bankonet.model.Client;

@Controller
public class BankonetController {
	
	//ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");

	@Autowired
	IClientMetier bankonetMetier; //(IBankonetMetier) context.getBean("bankonetMetier");
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public String index(Model model){
		
		final List<Client> listClients=bankonetMetier.listClients();
		
		model.addAttribute("client",new Client());
		model.addAttribute("listClients",listClients);
		
		return "clientsview";
	}
	
	@RequestMapping(value = "/saveClient", method=RequestMethod.POST)
	public String saveClient(@ModelAttribute("client") @Valid Client c, BindingResult bindingResult, Model model){
		if(bindingResult.hasErrors()){
			//System.out.println("HO MON DIEU");
			model.addAttribute("listClients", bankonetMetier.listClients());
			return "clientsview";
		}
		
		if(c.getId()==0){
			bankonetMetier.addClient(c);
			model.addAttribute("result","Le client n�"+c.getId()+" a bien �t� ajout�.");
		}else{
			bankonetMetier.updateClient(c);
			model.addAttribute("result","Le client n�"+c.getId()+" a bien �t� modifi�.");
		}
		
		
		model.addAttribute("listClients", bankonetMetier.listClients());
		model.addAttribute("client",new Client());
		return "clientsview";
	}
	
	@RequestMapping(value = "/deleteClient", method=RequestMethod.GET)
	public String deleteClient(@ModelAttribute("idClientDel") @Valid int idClient, BindingResult bindingResult, Model model){
		
		if(bindingResult.hasErrors()){
			//System.out.println("HO MON DIEU");
			model.addAttribute("client",new Client());
			model.addAttribute("listClients", bankonetMetier.listClients());
			return "clientsview";
		}
		bankonetMetier.deleteClient(idClient);
		model.addAttribute("listClients", bankonetMetier.listClients());
		model.addAttribute("client",new Client());
		model.addAttribute("result","Le client n�"+idClient+" a bien �t� supprim�.");
		return "clientsview";
	}
	
	
	@RequestMapping(value = "/editClient/{id}", method=RequestMethod.GET)
	public String editClient(@PathVariable("id") Integer idClient, Model model){
		
		model.addAttribute("listClients", bankonetMetier.listClients());
		model.addAttribute("client",bankonetMetier.editClient(idClient));
		
		return "clientsview";
	}
	
}
