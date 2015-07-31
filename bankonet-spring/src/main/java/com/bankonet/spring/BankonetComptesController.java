package com.bankonet.spring;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bankonet.metier.IClientMetier;
import com.bankonet.metier.ICompteMetier;
import com.bankonet.model.Client;
import com.bankonet.model.Compte;
import com.bankonet.model.CompteCourant;
import com.bankonet.model.CompteEpargne;
import com.bankonet.model.Virement;


/*
 Peut etre amélioré en factorisant les comptes et les urls. instance of
*/

@Controller
@RequestMapping(value="/comptes")
public class BankonetComptesController {
	
	@Autowired
	ICompteMetier bankonetMetier;
	
	@Autowired
	IClientMetier clientMetier;
	
	@RequestMapping(value="/courant/{idClient}/saveCompte", method=RequestMethod.POST)
	public String saveCompteCourant(@PathVariable("idClient") Integer idClient,
									@ModelAttribute("compte") @Valid CompteCourant c,
									BindingResult bindingResult,
									Model model){
		if(bindingResult.hasErrors()){
			model.addAttribute("compte",new CompteCourant());
			model.addAttribute("compteCourant",bankonetMetier.listComptesEpargne(idClient));
			return "comptescourantview";
		}
		if(c.getIdentifiant()==0){
			
			Client client=clientMetier.editClient(idClient);
			client.ajouterCompteCourant(c);
			clientMetier.updateClient(client);
			//A MODIFIER
			model.addAttribute("result","Le compte n°"+c.getIdentifiant()+" a bien été ajouté.");
		}else{
			Client client=clientMetier.editClient(idClient);
			c.setClient(client);
			client.modifierCompteCourant(c);
			//bankonetMetier.
			clientMetier.updateClient(client);
			model.addAttribute("result","Le compte n°"+c.getIdentifiant()+" a bien été modifié.");
		}
		
		model.addAttribute("compteCourant",clientMetier.editClient(idClient).getComptesCourantClient());
		model.addAttribute("clientId",idClient);
		model.addAttribute("compte",new CompteCourant());
		return "comptescourantview";
	}
	
	@RequestMapping(value="/courant/{idClient}", method=RequestMethod.GET)
	public String listComptesCourant(@PathVariable("idClient") Integer idClient, Model model){
		//final List<Compte> listeComptesEpargne=bankonetMetier.listComptesEpargne(idClient);
		final List<CompteCourant> listeComptesCourant=clientMetier.editClient(idClient).getComptesCourantClient();
		
		System.out.println(listeComptesCourant);
		
		model.addAttribute("clientId",idClient);
		model.addAttribute("compte",new CompteCourant());
		model.addAttribute("compteCourant",listeComptesCourant);
		
		return "comptescourantview";
	}
	
	@RequestMapping(value="/courant/{idClient}/deleteCompte", method=RequestMethod.GET)
	public String deleteCompteCourant(@PathVariable("idClient") Integer idClient,
							   @ModelAttribute("compteDel") @Valid Integer idCompte,
							   BindingResult bindingResult,
							   Model model){
		if(bindingResult.hasErrors()){
			model.addAttribute("compte",new CompteCourant());
			model.addAttribute("compteCourant",clientMetier.editClient(idClient).getComptesCourantClient());
			return "comptescourantview";
		}
		CompteCourant compte=bankonetMetier.editCompteCourant(idCompte);
		
		Client client=clientMetier.editClient(idClient);
		client.deleteCompteCourant(compte);
		//bankonetMetier.deleteCompte(idCompte);
		
		clientMetier.updateClient(client);
		
		
		model.addAttribute("clientId",idClient);
		model.addAttribute("compte",new CompteCourant());
		model.addAttribute("compteCourant",client.getComptesCourantClient());
		System.out.println(clientMetier.editClient(idClient).getComptesCourantClient());
		model.addAttribute("result","Le compte n°"+idCompte.toString()+" a bien été supprimé.");
		return "comptescourantview";
	}
	
	@RequestMapping(value="/courant/{idClient}/editCompte", method=RequestMethod.GET)
	public String editCompteCourant(@PathVariable("idClient") Integer idClient,
			   						@ModelAttribute("compteEdit") @Valid Integer idCompte,
			   						BindingResult bindingResult,
			   						Model model){
		if(bindingResult.hasErrors()){
			model.addAttribute("compte",new CompteCourant());
			model.addAttribute("compteEpargne",clientMetier.editClient(idClient).getComptesCourantClient());
			return "comptescourantview";
		}
		CompteCourant compte=bankonetMetier.editCompteCourant(idCompte);
		Client client=clientMetier.editClient(idClient);
		
		model.addAttribute("clientId",idClient);
		model.addAttribute("compte",compte);
		model.addAttribute("compteCourant",client.getComptesCourantClient());
		System.out.println(clientMetier.editClient(idClient).getComptesCourantClient());
		
		return "comptescourantview";
	}
	
	
	
	
	
	@RequestMapping(value="/epargne/{idClient}/saveCompte", method=RequestMethod.POST)
	public String saveCompteEpargne(@PathVariable("idClient") Integer idClient,
									@ModelAttribute("compte") @Valid CompteEpargne c,
									BindingResult bindingResult,
									Model model){
		if(bindingResult.hasErrors()){
			model.addAttribute("compte",new CompteEpargne());
			model.addAttribute("compteEpargne",bankonetMetier.listComptesEpargne(idClient));
			return "comptesepargneview";
		}
		if(c.getIdentifiant()==0){
			
			Client client=clientMetier.editClient(idClient);
			client.ajouterCompteEpargne(c);
			clientMetier.updateClient(client);
			//A MODIFIER
			model.addAttribute("result","Le compte n°"+c.getIdentifiant()+" a bien été ajouté.");
		}else{
			Client client=clientMetier.editClient(idClient);
			c.setClient(client);
			client.modifierCompteEpargne(c);
			//bankonetMetier.
			clientMetier.updateClient(client);
			model.addAttribute("result","Le compte n°"+c.getIdentifiant()+" a bien été modifié.");
		}
		
		model.addAttribute("compteEpargne",clientMetier.editClient(idClient).getComptesEpargneClient());
		model.addAttribute("clientId",idClient);
		model.addAttribute("compte",new CompteEpargne());
		return "comptesepargneview";
	}
	
	@RequestMapping(value="/epargne/{idClient}", method=RequestMethod.GET)
	public String listComptesEpargne(@PathVariable("idClient") Integer idClient, Model model){
		//final List<Compte> listeComptesEpargne=bankonetMetier.listComptesEpargne(idClient);
		final List<CompteEpargne> listeComptesEpargne=clientMetier.editClient(idClient).getComptesEpargneClient();
		
		System.out.println(listeComptesEpargne);
		
		model.addAttribute("clientId",idClient);
		model.addAttribute("compte",new CompteEpargne());
		model.addAttribute("compteEpargne",listeComptesEpargne);
		
		return "comptesepargneview";
	}
	
	@RequestMapping(value="/epargne/{idClient}/deleteCompte", method=RequestMethod.GET)
	public String deleteCompteEpargne(@PathVariable("idClient") Integer idClient,
							   @ModelAttribute("compteDel") @Valid Integer idCompte,
							   BindingResult bindingResult,
							   Model model){
		if(bindingResult.hasErrors()){
			model.addAttribute("compte",new CompteEpargne());
			model.addAttribute("compteEpargne",clientMetier.editClient(idClient).getComptesEpargneClient());
			return "comptesepargneview";
		}
		CompteEpargne compte=bankonetMetier.editCompteEpargne(idCompte);
		
		Client client=clientMetier.editClient(idClient);
		client.deleteCompteEpargne(compte);
		//bankonetMetier.deleteCompte(idCompte);
		
		clientMetier.updateClient(client);
		
		
		model.addAttribute("clientId",idClient);
		model.addAttribute("compte",new CompteEpargne());
		model.addAttribute("compteEpargne",client.getComptesEpargneClient());
		System.out.println(clientMetier.editClient(idClient).getComptesEpargneClient());
		model.addAttribute("result","Le compte n°"+idCompte.toString()+" a bien été supprimé.");
		return "comptesepargneview";
	}
	
	@RequestMapping(value="/epargne/{idClient}/editCompte", method=RequestMethod.GET)
	public String editCompteEpargne(@PathVariable("idClient") Integer idClient,
			   						@ModelAttribute("compteEdit") @Valid Integer idCompte,
			   						BindingResult bindingResult,
			   						Model model){
		if(bindingResult.hasErrors()){
			model.addAttribute("compte",new CompteEpargne());
			model.addAttribute("compteEpargne",clientMetier.editClient(idClient).getComptesEpargneClient());
			return "comptesepargneview";
		}
		CompteEpargne compte=bankonetMetier.editCompteEpargne(idCompte);
		Client client=clientMetier.editClient(idClient);
		
		model.addAttribute("clientId",idClient);
		model.addAttribute("compte",compte);
		model.addAttribute("compteEpargne",client.getComptesEpargneClient());
		System.out.println(clientMetier.editClient(idClient).getComptesEpargneClient());
		
		return "comptesepargneview";
	}
	
	@RequestMapping(value="/{idClient}/virement", method=RequestMethod.GET)
	public String listVirement(@PathVariable("idClient") Integer idClient, Model model){
		List<Compte> listComptes=new ArrayList<Compte>();
		Client client=clientMetier.editClient(idClient);
		listComptes.addAll(client.getComptesCourantClient());
		listComptes.addAll(client.getComptesEpargneClient());
		
		model.addAttribute("liste",listComptes);
		
		return "virementView";
	}
	
	@RequestMapping(value="/{idClient}/doVirement", method=RequestMethod.PUT)
	public String doVirement(@PathVariable("idClient") Integer idClient,
							@ModelAttribute("virement") @Valid Virement virement,
							Model model){
		List<Compte> listComptes=new ArrayList<Compte>();
		Client client=clientMetier.editClient(idClient);
		listComptes.addAll(client.getComptesCourantClient());
		listComptes.addAll(client.getComptesEpargneClient());
		
		model.addAttribute("liste",listComptes);
		
		return "virementView";
	}
	
}
