package it.uniroma3.siw.spring.jkb.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.spring.jkb.model.Giocatore;
import it.uniroma3.siw.spring.jkb.model.SalaDaBowling;
import it.uniroma3.siw.spring.jkb.model.Squadra;
import it.uniroma3.siw.spring.jkb.model.Torneo;
import it.uniroma3.siw.spring.jkb.service.SalaDaBowlingService;
import it.uniroma3.siw.spring.jkb.service.TorneoService;
import it.uniroma3.siw.spring.jkb.validator.TorneoValidator;

@Controller
public class TorneoController {

	Integer cont=0;

	@Autowired
	private TorneoService torneoService;

	@Autowired
	private SalaDaBowlingService salaDaBowlingService;

	@Autowired
	private TorneoValidator torneoValidator;

	@RequestMapping(value ="/admin/addTorneo",method=RequestMethod.GET)
	public String addTorneo(Model model) {
		if(model.getAttribute("torneo")!=null) {
			model.addAttribute("torneo",model.getAttribute("torneo"));
			return "admin/torneo/torneoForm.html";
		}
		model.addAttribute("torneo",new Torneo());
		model.addAttribute("sale",this.salaDaBowlingService.tutti());
		return "admin/torneo/torneoForm.html";
	}

	@RequestMapping(value = "/torneo/{id}", method = RequestMethod.GET)
	public String getOpera(@PathVariable("id") Long id, Model model) {
		Torneo t=this.torneoService.getTorneo(id);
		this.torneoService.controlloDate(t, t.getDataInizio(),t.getDataFine(),t.getDataInizioIscrizioni(),t.getDataFineIscrizioni());
		model.addAttribute("torneo", this.torneoService.getTorneo(id));
		return "torneo.html";

	}

	//	@RequestMapping(value = "/admin/modificaTorneo", method = RequestMethod.GET)
	//	public String changeTorneo(@RequestParam("torneo")String torneo,Model model) {
	//		model.addAttribute("torneoVecchio", this.torneoService.torneoPerNome(torneo).get(0));
	//		model.addAttribute("sale",this.salaDaBowlingService.tutti());
	//		this.torneoService.eliminaTorneo(this.torneoService.torneoPerNome(torneo).get(0));
	//		model.addAttribute("torneo",new Torneo());
	//		return "admin/torneo/torneoForm.html";
	//	}
	//	
//		@RequestMapping(value = "/admin/deleteTorneo", method = RequestMethod.GET)
//		public String eliminaTorneo(@RequestParam("torneo") String nome, Model model) {
//			Torneo torneoDelete = this.torneoService.torneoPerNome(nome).get(0);
//			this.torneoService.eliminaTorneo(torneoDelete);
//			model.addAttribute("tornei",this.torneoService.tutti());
//			return "admin/torneo/tornei.html";
//		}

	@RequestMapping(value = "/admin/tornei", method = RequestMethod.GET)
	public String torneiAdmin(Model model) {
		List<Torneo> tornei = this.torneoService.tutti();
		for(Torneo t :tornei) {
			
		this.torneoService.controlloDate(t, t.getDataInizio(),t.getDataFine(),t.getDataInizioIscrizioni(),t.getDataFineIscrizioni());
		
		}
		model.addAttribute("tornei",tornei);
		model.addAttribute("torn", new String());
				
		return "admin/torneo/tornei.html";

	}

		@RequestMapping(value = "/admin/nuovoTorneo", method = { RequestMethod.GET,RequestMethod.PUT, RequestMethod.POST })
		public String newTorneo(@ModelAttribute("torneo") Torneo torneo,@RequestParam("sal")String sala,
				Model model, BindingResult bindingResult) {
			this.torneoValidator.validate(torneo, bindingResult);
	
			if(!bindingResult.hasErrors()) {
				torneo.setSala(this.salaDaBowlingService.salaPerNome(sala).get(0));
				this.torneoService.saveTorneo(torneo);
				model.addAttribute("tornei", this.torneoService.tutti());
				return "admin/torneo/tornei.html";
			}
			model.addAttribute("sale",this.salaDaBowlingService.tutti());
			return "admin/torneo/torneoForm.html";
		}

	@RequestMapping(value="/controlloTorneo",method = { RequestMethod.GET,RequestMethod.PUT })
	public String dmTorneo(@RequestParam(value = "modifica", required = false) String modifica,
			@RequestParam(value = "elimina", required = false) String elimina,
			@RequestParam("torn") String torneo,
			Model model) {

		if(modifica != null && elimina == null) {
			Torneo t = this.torneoService.torneoPerNome(torneo).get(0);
			model.addAttribute("torneo", this.torneoService.getTorneo(t.getId()));
			model.addAttribute("sale",this.salaDaBowlingService.tutti());
			return "admin/torneo/torneoForm.html";
		}

		Torneo torneoDelete = this.torneoService.torneoPerNome(torneo).get(0);
		this.torneoService.eliminaTorneo(torneoDelete);
		model.addAttribute("tornei",this.torneoService.tutti());
		return "admin/torneo/tornei.html";

	}
//
//	@RequestMapping(value="/controlloModificaTorneo",method= {RequestMethod.POST,RequestMethod.PUT,RequestMethod.GET})
//	public String controlloModificheTorneo(@ModelAttribute("torneo")Torneo torneo,
//			@RequestParam("nome")String nome,
//			@RequestParam("descrizione")String descrizione,
//			@RequestParam("dataInizioIscrizioni")String dataInizioIscrizioni,
//			@RequestParam("dataFineIscrizioni")String dataFineIscrizioni,
//			@RequestParam("dataInizio")String dataInizio,
//			@RequestParam("dataInizio")String dataFine,
//			@RequestParam("quotaIscrizione")String quota,
//			@RequestParam("sal")String sala,
//			@RequestParam(value = "modifica", required = false)String modifica,
//			@RequestParam(value = "inserisci", required = false)String inserisci,Model model,BindingResult bindingResult) {
//		
//		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyy-MM-dd");
//		LocalDate localDateInizio = LocalDate.parse(dataInizio, formatter);
//		LocalDate localDateInizioIscrizioni = LocalDate.parse(dataInizioIscrizioni,formatter);
//		LocalDate localDateFineIscrizioni = LocalDate.parse(dataFineIscrizioni,formatter);
//		LocalDate localDateFine = LocalDate.parse(dataFine,formatter);
//		
//		if(modifica!=null && inserisci==null) {
//			this.torneoService.modificaTorneo(nome,descrizione,localDateInizio ,
//					 localDateFine ,localDateInizioIscrizioni,localDateFineIscrizioni,quota,
//					torneo.getSala(),torneo.getId());
//			model.addAttribute("tornei",this.torneoService.tutti());
//			return "/admin/torneo/tornei.html";
//		}
//
//		this.torneoValidator.validate(torneo, bindingResult);
//		if(!bindingResult.hasErrors()) {
//			torneo.setSala(this.salaDaBowlingService.salaPerNome(sala).get(0));
//			this.torneoService.saveTorneo(torneo);
//			model.addAttribute("tornei", this.torneoService.tutti());
//			return "admin/torneo/tornei.html";
//		}
//		model.addAttribute("sale",this.salaDaBowlingService.tutti());
//		return "admin/torneo/torneoForm.html";
//
//	}

}

