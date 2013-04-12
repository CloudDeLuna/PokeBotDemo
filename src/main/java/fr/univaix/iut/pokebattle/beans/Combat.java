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
    @NamedQuery(name = Combat.GET_MAX_NUM_CB, query = "SELECT MAX(cb.idCombat) FROM Combat cb"),
})
public class Combat implements Serializable {
	
	private static final long serialVersionUID = 4828367638072896806L;

	public static final String GET_BY_NOM = "findCombatEnCoursByPokemon";
	public static final String GET_BY_OWNER = "findCombatEnCoursByOwner";
	public static final String GET_MAX_NUM_CB = "GetMaxNumCB";
	
	
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
	
	
	public Combat() {
		super();
	}
	

	public Combat(int idCombatt, Pokemon poke1e, String owner1e, Pokemon poke2e,
			String owner2e) {
		super();
		idCombat = idCombatt;
		poke1 = poke1e;
		owner1 = owner1e;
		poke2 = poke2e;
		owner2 = owner2e;
	}


	public Pokemon getPoke1() {
		return poke1;
	}


	public void setPoke1(Pokemon poke1e) {
		poke1 = poke1e;
	}

	

	public Pokemon getPoke2() {
		return poke2;
	}


	public void setPoke2(Pokemon poke2e) {
		poke2 = poke2e;
	}


	public String getOwner1() {
		return owner1;
	}


	public void setOwner1(String owner1e) {
		owner1 = owner1e;
	}


	public String getOwner2() {
		return owner2;
	}


	public void setOwner2(String owner2e) {
		owner2 = owner2e;
	}


	public int getIdCombat() {
		return idCombat;
	}


	public void setIdCombat(int idCombatt) {
		idCombat = idCombatt;
	}


	@Override
	public int hashCode() {
		  assert false : "hashCode not designed";
		  return 42;
	}

	@Override
	public String toString() {
		return "Combat [IdCombat=" + idCombat + ", Poke_1=" + poke1.getNom()
				+ ", Owner_1=" + owner1 + ", Poke_2=" + poke2.getNom() + ", Owner_2="
				+ owner2 + "]";
	}
	
}