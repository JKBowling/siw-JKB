package it.uniroma3.siw.spring.jkb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Indirizzo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String via;
	
	
	@Column(nullable=false)
	private String citta;
	
	@Column(nullable=false)
	private String cap;
	
	@Column(nullable=false,unique=true)
	private String code;
	
	@OneToOne
	private SalaDaBowling sala;
	
	public Indirizzo() {
		
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Indirizzo other = (Indirizzo) obj;
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
		return "Indirizzo [via=" + via + ", citta=" + citta + ", cap=" + cap +
				", code=" + code+", sala=" + sala + "]";
	}
	
	
	
}
