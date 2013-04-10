package fr.univaix.iut.pokebattle.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
    @NamedQuery(name = Combat.GET_BY_NOM, query = "SELECT cb FROM Combat cb WHERE  ( cb.poke1 = :nom OR cb.poke2 = :nom )"),
    @NamedQuery(name = Combat.GET_BY_OWNER, query = "SELECT cb FROM Combat cb WHERE  ( cb.owner1 = :nom OR cb.owner2 = :nom )"),
    @NamedQuery(name = Combat.GET_MAX_NUM_CB, query = "SELECT cb FROM Combat cb WHERE cb.idCombat IN ( SELECT MAX(cb2.idCombat) FROM Combat cb2 )"),
})
public class Combat implements Serializable {
	
	private static final long serialVersionUID = 4828367638072896806L;

	public static final String GET_BY_NOM = "findCombatEnCoursByPokemon";
	public static final String GET_BY_OWNER = "findCombatEnCoursByOwner";
	public static final String GET_MAX_NUM_CB = "findMaxNumCB";
	
	
	@Id 
	@Column( name = "NUM_CB")
	private int idCombat ;
	
	
	@OneToOne 
	@JoinColumn( name = "POKE_1")
	private Pokemon poke1;
	
	@Column( name = "OWNER_1")
	private String owner1;

	@OneToOne 
	@JoinColumn( name = "POKE_2")
	private Pokemon poke2;
	
	@Column( name = "OWNER_2")
	private String owner2;
	
	@Column ( name = "NUM_ROUND")
	private int numRound;
	
	public Combat() {
		super();
	}

	public Combat(int idCombat, Pokemon poke1, String owner1, Pokemon poke2,
			String owner2, int numRound) {
		super();
		this.idCombat = idCombat;
		this.poke1 = poke1;
		this.owner1 = owner1;
		this.poke2 = poke2;
		this.owner2 = owner2;
		this.numRound = numRound;
	}
	
	public int getIdCombat() {
		return idCombat;
	}

	public void setIdCombat(int idCombat) {
		this.idCombat = idCombat;
	}

	public Pokemon getPoke1() {
		return poke1;
	}

	public void setPoke1(Pokemon poke1) {
		this.poke1 = poke1;
	}

	public String getOwner1() {
		return owner1;
	}

	public void setOwner1(String owner1) {
		this.owner1 = owner1;
	}

	public Pokemon getPoke2() {
		return poke2;
	}

	public void setPoke2(Pokemon poke2) {
		this.poke2 = poke2;
	}

	public String getOwner2() {
		return owner2;
	}

	public void setOwner2(String owner2) {
		this.owner2 = owner2;
	}

	public int getNumRound() {
		return numRound;
	}

	public void setNumRound(int numRound) {
		this.numRound = numRound;
	}

	@Override
	public String toString() {
		return "Combat [idCombat=" + idCombat + ", poke1=" + poke1.getNom()
				+ ", owner1=" + owner1 + ", poke2=" + poke2.getNom() + ", owner2="
				+ owner2 + ", numRound=" + numRound + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCombat;
		result = prime * result + numRound;
		result = prime * result + ((owner1 == null) ? 0 : owner1.hashCode());
		result = prime * result + ((owner2 == null) ? 0 : owner2.hashCode());
		result = prime * result + ((poke1 == null) ? 0 : poke1.hashCode());
		result = prime * result + ((poke2 == null) ? 0 : poke2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Combat other = (Combat) obj;
		if (idCombat != other.idCombat)
			return false;
		if (numRound != other.numRound)
			return false;
		if (owner1 == null) {
			if (other.owner1 != null)
				return false;
		} else if (!owner1.equals(other.owner1))
			return false;
		if (owner2 == null) {
			if (other.owner2 != null)
				return false;
		} else if (!owner2.equals(other.owner2))
			return false;
		if (poke1 == null) {
			if (other.poke1 != null)
				return false;
		} else if (!poke1.equals(other.poke1))
			return false;
		if (poke2 == null) {
			if (other.poke2 != null)
				return false;
		} else if (!poke2.equals(other.poke2))
			return false;
		return true;
	}
	
	
	

	


	
	
}