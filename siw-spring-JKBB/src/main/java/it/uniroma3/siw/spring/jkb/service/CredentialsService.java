package it.uniroma3.siw.spring.jkb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.spring.jkb.repository.CredentialsRepository;
import it.uniroma3.siw.spring.jkb.model.Credentials;

public class CredentialsService {
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	private CredentialsRepository credentialsRepository;
	
	@Transactional
	public Credentials getCredentials(Long id) {
		
		Optional<Credentials> result = this.credentialsRepository.findById(id);
		return result.orElse(null);
		
	}
	
	@Transactional
	public Credentials getCredentials(String username) {
		
		Optional<Credentials> result = this.credentialsRepository.findByUsername(username);
		return result.orElse(null);
		
	}
	
	@Transactional
	public Credentials saveCredentials(Credentials credential) {
		
		credential.setRole(Credentials.DEFAULT_ROLE);
		
		credential.setPassword(this.passwordEncoder.encode(credential.getPassword()));
		return this.credentialsRepository.save(credential);
		
	}
}
