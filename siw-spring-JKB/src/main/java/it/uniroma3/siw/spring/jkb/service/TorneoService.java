package it.uniroma3.siw.spring.jkb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.spring.jkb.model.Torneo;
import it.uniroma3.siw.spring.jkb.repository.TorneoRepository;

@Service
public class TorneoService {

	@Autowired
	private TorneoRepository torneoRepository;
	
	@Transactional
	public Torneo getTorneo(Long id) {
		
		Optional<Torneo> result = this.torneoRepository.findById(id);
		return result.orElse(null);
		
	}
	
	@Transactional
	public Torneo saveTorneo(Torneo torneo) {
		return this.torneoRepository.save(torneo);
	}
	
	@Transactional
	public List<Torneo> tutti(){
		return (List<Torneo>) this.torneoRepository.findAll();
	}
}
