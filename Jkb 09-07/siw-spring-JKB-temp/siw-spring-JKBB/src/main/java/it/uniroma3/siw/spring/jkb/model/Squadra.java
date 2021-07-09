package it.uniroma3.siw.spring.jkb.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
public class Squadra {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private String annoFondazione;
	
	@Column
	private Integer torneiVinti;
	
	@Column(nullable=false,unique=true)
	private String code;
	
	@OneToMany(mappedBy="squadra",cascade=CascadeType.ALL)
	private List<Giocatore> giocatori;
	
	@ManyToOne
	private Torneo torneo;
	
	public Squadra() {
		this.giocatori=new ArrayList<>();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Squadra other = (Squadra) obj;
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
		return "Squadra [nome=" + nome + ", annoFondazione=" + annoFondazione + ", torneiVinti=" + torneiVinti +", torneo=" + torneo
				+ ", code=" + code + "]";
	}
	
}
