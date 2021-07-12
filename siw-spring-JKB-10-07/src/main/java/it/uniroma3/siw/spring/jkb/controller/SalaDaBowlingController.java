package it.uniroma3.siw.spring.jkb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.spring.jkb.model.Indirizzo;
import it.uniroma3.siw.spring.jkb.model.SalaDaBowling;
import it.uniroma3.siw.spring.jkb.model.Torneo;
import it.uniroma3.siw.spring.jkb.service.SalaDaBowlingService;
import it.uniroma3.siw.spring.jkb.validator.SalaDaBowlingValidator;

@Controller
public class SalaDaBowlingController {
	
	@Autowired
	private SalaDaBowlingService salaDaBowlingService;
	
	@Autowired
	private SalaDaBowlingValidator salaDaBowlingValidator;

	@RequestMapping(value="/admin/addSala",method= RequestMethod.GET)
	public String sala(Model model) {
		if(model.getAttribute("sala")!=null) {
			model.addAttribute("sala",model.getAttribute("sala"));
			return "admin/sala/salaDaBowlingForm.html";
		}
		model.addAttribute("sala",new SalaDaBowling());
		model.addAttribute("indirizzo",new Indirizzo());
		if(this.salaDaBowlingService.salaUltima()!=null)
			model.addAttribute("lastCode",this.salaDaBowlingService.salaUltima().toString());
		return "admin/sala/salaDaBowlingForm.html";
	}
	
	@RequestMapping(value = "/admin/deleteSala", method = RequestMethod.GET)
	public String eliminaSala(@RequestParam("sala") String nome, Model model) {
		SalaDaBowling salaDelete = this.salaDaBowlingService.salaPerNome(nome).get(0);
		this.salaDaBowlingService.eliminaSala(salaDelete);
		model.addAttribute("sale",this.salaDaBowlingService.tutti());
		return "admin/sala/sale.html";
	}
	
	@RequestMapping(value="/admin/salePartner",method= RequestMethod.GET)
	public String saleAdmin(Model model) {
		model.addAttribute("sale",this.salaDaBowlingService.tutti());
		return "admin/sala/sale.html";
	}
	
	@RequestMapping(value = "/admin/nuovaSala", method = RequestMethod.POST)
	public String newSala(@ModelAttribute("sala") SalaDaBowling sala,@ModelAttribute("indirizzo") Indirizzo indirizzo,
			Model model, BindingResult bindingResult) {
		this.salaDaBowlingValidator.validate(sala, bindingResult);

		
		if(!bindingResult.hasErrors()) {
			sala.setIndirizzo(indirizzo);
			this.salaDaBowlingService.inserisci(sala);
			model.addAttribute("sale", this.salaDaBowlingService.tutti());
			return "admin/sala/sale.html";
		}
		return "admin/sala/salaDaBowlingForm.html";
	} 
	
	@RequestMapping(value="/controlloSala", method=RequestMethod.POST)
	public String dmTorneo(@RequestParam(value = "modifica", required = false) String modifica,
			@RequestParam(value = "elimina", required = false) String elimina,
			@RequestParam("sala") String sala,
			Model model) {

		if(modifica != null && elimina == null) {
			model.addAttribute("vecchiaSala", this.salaDaBowlingService.salaPerNome(sala));
			this.salaDaBowlingService.eliminaSala(this.salaDaBowlingService.salaPerNome(sala).get(0));
			model.addAttribute("sala",new SalaDaBowling());
			return "admin/sala/salaDaBowlingForm.html";
		}

		SalaDaBowling salaDelete = this.salaDaBowlingService.salaPerNome(sala).get(0);
		this.salaDaBowlingService.eliminaSala(salaDelete);
		model.addAttribute("tornei",this.salaDaBowlingService.tutti());
		return "admin/sala/sale.html";

	}
}
