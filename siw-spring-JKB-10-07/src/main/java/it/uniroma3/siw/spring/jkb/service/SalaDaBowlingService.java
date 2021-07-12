package it.uniroma3.siw.spring.jkb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.spring.jkb.model.SalaDaBowling;
import it.uniroma3.siw.spring.jkb.model.Torneo;
import it.uniroma3.siw.spring.jkb.repository.SalaDaBowlingRepository;

@Service
public class SalaDaBowlingService {
	
	@Autowired
	private SalaDaBowlingRepository salaDaBowlingRepository;
	
	@Transactional
	public SalaDaBowling inserisci(SalaDaBowling sala) {
		List<SalaDaBowling> sale = (List<SalaDaBowling>) this.salaDaBowlingRepository.findAll();
		if(sale.size()!=0) {
			if(this.getSala(this.salaUltima()).getYes()!=null) {
				sala.setYes(null);
			}
		}
		return this.salaDaBowlingRepository.save(sala);
	}
	
	@Transactional
	public List<SalaDaBowling> salaPerNome(String nome) {
		return this.salaDaBowlingRepository.findByNomeLike(nome);
	}
	
	@Transactional
	public SalaDaBowling getSala(Long id) {
		
		Optional<SalaDaBowling> result = this.salaDaBowlingRepository.findById(id);
		return result.orElse(null);
		
	}
	
	@Transactional
	public void eliminaSala(SalaDaBowling sala) {
		this.salaDaBowlingRepository.deleteById(sala.getId());
	}
	
	@Transactional
	public List<SalaDaBowling> tutti(){
		return (List<SalaDaBowling>) this.salaDaBowlingRepository.findAll();
	}
	
	@Transactional
	public boolean alreadyExists(SalaDaBowling sala) {
		List<SalaDaBowling> sale = this.salaDaBowlingRepository.findByNomeLike(sala.getNome());
		if (sale.size() > 0)
			return true;
		else 
			return false;
	} 
	
	@Transactional
	public Long salaUltima() {
		return this.salaDaBowlingRepository.findSalaUltima();
	}

}
