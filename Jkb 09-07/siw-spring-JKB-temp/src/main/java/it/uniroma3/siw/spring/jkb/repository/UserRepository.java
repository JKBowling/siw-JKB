package it.uniroma3.siw.spring.jkb.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.jkb.model.User;

public interface UserRepository  extends CrudRepository<User,Long> {
	
	public List<User> findByNomeAndCognomeIgnoreCaseContaining(String nome,String cognome);

}
