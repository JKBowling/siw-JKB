package it.uniroma3.siw.spring.jkb.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
public class Giocatore {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private String cognome;
	
	@Column(nullable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataNascita;
	
	@Column(nullable=true)
	private String soprannome;
	
	@Column(nullable=false,unique=true)
	private String code;
	
	@ManyToOne
	private Squadra squadra;
	
	public Giocatore() {
		
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Giocatore other = (Giocatore) obj;
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
		return "Giocatore [nome=" + nome + ", cognome=" + cognome + ", dataNascita=" + dataNascita + ", soprannome="
				+ soprannome + ", code=" + code + ", squadra=" + squadra + "]";
	}
	
	
	
}
