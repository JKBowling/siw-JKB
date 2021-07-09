package it.uniroma3.siw.spring.jkb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.jkb.model.Torneo;
import it.uniroma3.siw.spring.jkb.service.TorneoService;

@Controller
public class TorneoController {

	Integer cont=0;

	@Autowired
	private TorneoService torneoService;

	@RequestMapping(value= "/tournament" ,method=RequestMethod.GET)
	public String singoloTorneo(Model model) {
		return "torneo.html";
	}

	@RequestMapping(value= "/tornei" ,method=RequestMethod.GET)
	public String tornei(Model model) {
		model.addAttribute("tornei",this.torneoService.tutti());
		return "tornei.html";
	}

	@RequestMapping(value = "/nuovoTorneo",method=RequestMethod.GET)
	public String nuovoTorneo(Model model) {
		this.torneoService.saveTorneo(new Torneo("BELMONTE","Jason Belmonte", cont+1));
		this.torneoService.saveTorneo(new Torneo("BELMONTE","Jason Belmonte", cont+2));
		cont=cont+2;
		model.addAttribute("tornei",this.torneoService.tutti());
		return "tornei.html";
	}
}
