package fr.univaix.iut.pokebattle.beans;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class DataObjectPokemonTest {

	@Test
	public void testConstructAndGetters() {
		Pokedex pokedex = Pokedex.getInstance();
		List<DataObjectAttack> attaques = pokedex.getPokemon("Goupix").getAttaques();
		DataObjectAttack [] atta = (DataObjectAttack[]) attaques.toArray();
		DataObjectPokemon poke = new DataObjectPokemon("Poussifeu", "Poussin", (float)0.4, (float)2.5, 
											(float)0.125, "+1Att.Spé", "Feu",null, 64, 1059860, 
												45, "Brasier", "ForceSoleil", "Rouge", 8, atta);
		
		assertEquals(45,poke.getCaptureval());
		assertEquals("Brasier",poke.getCapspe1());
		assertEquals("ForceSoleil",poke.getCapspe2());
		assertEquals("Rouge",poke.getCouleur());
		assertEquals("+1Att.Spé",poke.getEffortval());
		assertEquals("Poussin",poke.getEspece());
		assertEquals(1059860,poke.getExpmax());
		assertEquals(64,poke.getExpval());
		assertEquals(0.125,poke.getFmratio(), 0.0);
		assertEquals(8,poke.getForme());
		assertEquals("Poussifeu",poke.getNom());
		assertEquals(2.5,poke.getPoids(), 0.0);
		assertEquals(0.4,poke.getTaille(), 0.01);
		assertEquals("Feu",poke.getType1());
		assertEquals(null,poke.getType2());
		System.out.println(poke.toString());
	}
}
