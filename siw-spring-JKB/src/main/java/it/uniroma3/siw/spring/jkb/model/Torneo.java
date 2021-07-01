package it.uniroma3.siw.spring.jkb.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Torneo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private String descrizione;
	
	@Column(nullable=false)
	private LocalDate dataInizio;
	
	@Column(nullable=false)
	private LocalDate dataFine;
	
	@Column(nullable=false)
	private LocalDate dataInizioIscrizioni;
	
	@Column(nullable=false)
	private LocalDate dataFineIscrizioni;
	
	@Column
	private String quotaIscrizione;
	
	@Column(nullable=false,unique=true)
	private String code;
	
	@OneToMany(mappedBy="torneo")
	private List<Squadra> squadreIscritte;
	
	@ManyToOne
	private SalaDaBowling sala;
	
	public Torneo() {
		this.squadreIscritte=new ArrayList<>();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Torneo other = (Torneo) obj;
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
		return "Torneo [nome=" + nome + ", descrizione=" + descrizione + ", dataInizio=" + dataInizio + ", dataFine="
				+ dataFine + ", dataInizioIscrizioni=" + dataInizioIscrizioni + ", dataFineIscrizioni="
				+ dataFineIscrizioni + ", quotaIscrizione=" + quotaIscrizione + ", code=" + code + ", sala=" + sala
				+ "]";
	}
	
	
	
	
}
