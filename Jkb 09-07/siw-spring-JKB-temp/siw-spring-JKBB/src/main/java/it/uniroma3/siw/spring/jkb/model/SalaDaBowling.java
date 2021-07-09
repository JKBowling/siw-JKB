package it.uniroma3.siw.spring.jkb.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class SalaDaBowling {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private String descrizione;
	
	@Column(nullable=false,unique=true)
	private String code;
	
	@Column
	private String link;
	
	@OneToMany(mappedBy="sala",cascade=CascadeType.ALL)
	private List<Torneo> torneiOspitati;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Indirizzo indirizzo;
	
	public SalaDaBowling() {
		this.torneiOspitati=new ArrayList<>();
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalaDaBowling other = (SalaDaBowling) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "SalaDaBowling [nome=" + nome + ", descrizione=" + descrizione + ", code=" + code + ", indirizzo="
				+ indirizzo + "]";
	}

	/*
	public SalaDaBowling(String nome, String descrizione, String code, String link, Indirizzo indirizzo) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
		this.code = code;
		this.link = link;
		this.indirizzo = indirizzo;
	}
	*/
	
}
