package fr.univaix.iut.pokebattle.beans;

public class AttacksPK {
	private String poke; 
	private String attack;
	
	public AttacksPK() {
		super();
	}
	
	public AttacksPK(String poke, String attack) {
		super();
		this.poke = poke;
		this.attack = attack;
	}

	public String getPoke() {
		return poke;
	}
	public void setPoke(String poke) {
		this.poke = poke;
	}
	public String getAttack() {
		return attack;
	}
	public void setAttack(String attack) {
		this.attack = attack;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attack == null) ? 0 : attack.hashCode());
		result = prime * result + ((poke == null) ? 0 : poke.hashCode());
		return result;
	}

	public String toString() {
		return "AttacksPK [poke=" + poke + ", attack=" + attack + "]";
	}
	
	
}
