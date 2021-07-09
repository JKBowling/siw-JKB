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

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
public class Torneo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column//(nullable=false)
	private String nome;
	
	@Column//(nullable=false,length=20000)
	private String descrizione;
	
	@Column//(nullable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataInizio;
	
	@Column//(nullable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataFine;
	
	@Column//(nullable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataInizioIscrizioni;
	
	@Column//(nullable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataFineIscrizioni;
	
	@Column
	private String quotaIscrizione;
	
	@Column//(nullable=false,unique=true)
	private Integer code;
	
	@Column
	private String yes;
	
	@OneToMany(mappedBy="torneo")
	private List<Squadra> squadreIscritte;
	
	@ManyToOne
	private SalaDaBowling sala;
	
	public Torneo() {
		this.squadreIscritte=new ArrayList<>();
		this.yes="y";
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


	@lombok.experimental.Tolerate
	public Torneo(String nome, String descrizione,Integer code) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.code=code;
		this.yes="y";
	}
	
	
	
	
}
