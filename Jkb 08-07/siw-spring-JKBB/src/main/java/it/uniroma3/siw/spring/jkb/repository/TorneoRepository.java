package it.uniroma3.siw.spring.jkb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.jkb.model.Torneo;

public interface TorneoRepository  extends CrudRepository<Torneo,Long> {
	
	public List<Torneo> findByNomeLike(String nome);
	
	@Query("SELECT max(code) FROM Torneo")
	public Integer findTorneoUltimo();
	
	public Torneo findByCode(Integer code);

}
