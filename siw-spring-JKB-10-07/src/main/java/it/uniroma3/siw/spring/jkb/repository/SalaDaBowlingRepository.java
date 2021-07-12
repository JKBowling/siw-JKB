package it.uniroma3.siw.spring.jkb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.jkb.model.SalaDaBowling;

public interface SalaDaBowlingRepository  extends CrudRepository<SalaDaBowling,Long> {
	
	@Query("SELECT max(id) FROM SalaDaBowling")
	public Long findSalaUltima();

	public List<SalaDaBowling> findByNomeLike(String nomeSala);
}
