package it.uniroma3.siw.spring.jkb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/*
import it.uniroma3.siw.spring.jkb.model.Indirizzo;
import it.uniroma3.siw.spring.jkb.model.SalaDaBowling;
import it.uniroma3.siw.spring.jkb.service.SalaDaBowlingService;
*/

@Controller
public class MainController {
	
	/*
	@Autowired
	private SalaDaBowlingService salaDaBowlingService;
	*/
	
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String index(Model model) {
		return "index.html";
	}
	
	@RequestMapping(value = "/informazioni", method = RequestMethod.GET)
	public String informazioni(Model model) {
		return "informazioni.html";
	}
	 /*
	@RequestMapping(value = "/salePartner", method = RequestMethod.GET)
	public String salePartner(Model model) {
		
		Indirizzo ind = new Indirizzo("Lungotevere dell'Acqua Acetosa", "10", "00197", "Roma", "RM", "Italia", "01");
		SalaDaBowling bruns = new SalaDaBowling("Brunswick Bowling", "bello", "01", "http://www.brunswick.it/", ind);
		
		this.salaDaBowlingService.inserisci(bruns);
		
		model.addAttribute("saleDaBowling", this.salaDaBowlingService.tutti());
		return "salePartner.html";
		
	}
	*/
}
