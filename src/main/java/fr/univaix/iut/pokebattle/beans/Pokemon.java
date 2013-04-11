package fr.univaix.iut.pokebattle.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = Pokemon.FIND_BY_NOM, query = "SELECT poke FROM Pokemon poke WHERE poke.nom = :nom"),
    @NamedQuery(name = Pokemon.FIND_BY_RACE, query = "SELECT poke FROM Pokemon poke WHERE poke.race = :race"),
    @NamedQuery(name = Pokemon.FIND_ALL, query = "SELECT poke FROM Pokemon poke "),
})

public class Pokemon implements Serializable{
	
	private static final long serialVersionUID = 1318904490487933271L;
	
	public static final String FIND_BY_NOM = "findBynom";
	public static final String FIND_BY_RACE = "findByRace";
	public static final String FIND_ALL = "findAllPoke";
	
	@Id
    @JoinColumn ( name = "Nom")
	private String nom;
	private String race;
	private int niveau;
	private int pV;
	private int xP;
	@Column( name = "ATTS")
    private int attack;
	@Column( name = "DEFS")
    private int defense;
	@Column( name = "ATTSPE")
    private int attackSpecial;
	@Column( name = "DEFSPE")
    private int defenseSpecial;
	@Column( name = "VIT")
    private int speed;
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nomm) {
		nom = nomm;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String racee) {
		race = racee;
	}
	public int getNiveau() {
		return niveau;
	}
	public void setNiveau(int niveauu) {
		niveau = niveauu;
	}
	public int getPV() {
		return pV;
	}
	public void setPV(int pVV) {
		pV = pVV;
	}	
	public int getXP() {
		return xP;
	}
	public void setXP(int xPP) {
		xP = xPP;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public int getAttackSpecial() {
		return attackSpecial;
	}
	public void setAttackSpecial(int attackSpecial) {
		this.attackSpecial = attackSpecial;
	}
	public int getDefenseSpecial() {
		return defenseSpecial;
	}
	public void setDefenseSpecial(int defenseSpecial) {
		this.defenseSpecial = defenseSpecial;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	@Override
	public String toString() {
		return "Pokemon [nom=" + nom + ", race=" + race + ", niveau=" + niveau
				+ ", pV=" + pV + ", xP=" + xP + ", attack=" + attack
				+ ", defense=" + defense + ", attackSpecial=" + attackSpecial
				+ ", defenseSpecial=" + defenseSpecial + ", speed=" + speed
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + attack;
		result = prime * result + attackSpecial;
		result = prime * result + defense;
		result = prime * result + defenseSpecial;
		result = prime * result + niveau;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + pV;
		result = prime * result + ((race == null) ? 0 : race.hashCode());
		result = prime * result + speed;
		result = prime * result + xP;
		return result;
	}
}
