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
    @NamedQuery(name = Combat.GET_BY_JUDGE, query = "SELECT cb FROM Combat cb WHERE cb.nomJuge = :nom"),
})
public class Combat implements Serializable {
	
	private static final long serialVersionUID = 4828367638072896806L;

	public static final String GET_BY_NOM = "findCombatEnCoursByPokemon";
	public static final String GET_BY_OWNER = "findCombatEnCoursByOwner";
	public static final String GET_BY_JUDGE = "findCombatByJuge";
	
	
	@Id 
	@Column( name = "NOM_JUGE")
	private String nomJuge;
	
	
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
	
	@Column ( name = "CPT_ATT")
	private int compteurAtt;
	
	public Combat() {
		super();
	}

	public Combat(String nomJuge, Pokemon poke1, String owner1, Pokemon poke2,
			String owner2, int numRound, int compteurAtt) {
		super();
		this.nomJuge = nomJuge;
		this.poke1 = poke1;
		this.owner1 = owner1;
		this.poke2 = poke2;
		this.owner2 = owner2;
		this.numRound = numRound;
		this.compteurAtt = compteurAtt;
	}

	public String getNomJuge() {
		return nomJuge;
	}

	public void setNomJuge(String nomJuge) {
		this.nomJuge = nomJuge;
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

	public int getCompteurAtt() {
		return compteurAtt;
	}

	public void setCompteurAtt(int compteurAtt) {
		this.compteurAtt = compteurAtt;
	}

	@Override
	public String toString() {
		return "Combat [nomJuge=" + nomJuge + ", poke1=" + poke1.getNom() + ", owner1="
				+ owner1 + ", poke2=" + poke2.getNom() + ", owner2=" + owner2
				+ ", numRound=" + numRound + ", compteurAtt=" + compteurAtt
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + compteurAtt;
		result = prime * result + ((nomJuge == null) ? 0 : nomJuge.hashCode());
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
		if (compteurAtt != other.compteurAtt)
			return false;
		if (nomJuge == null) {
			if (other.nomJuge != null)
				return false;
		} else if (!nomJuge.equals(other.nomJuge))
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