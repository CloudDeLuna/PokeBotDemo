package fr.univaix.iut.pokebattle.beans;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class DataObjectAttackTest {

	@Test
	public void testConstructAndGetters() {
		Pokedex pokedex = Pokedex.getInstance();
		List<DataObjectAttack> attaques = pokedex.getPokemon("Goupix").getAttaques();
		DataObjectAttack attaque = attaques.get(0);

		assertEquals("Départ",attaque.getNiveau());
		assertEquals("Flammeche",attaque.getNom());
		assertEquals("25",attaque.getPp());
		assertEquals("100",attaque.getPrecision());
		assertEquals("40",attaque.getPuissance());
	}
}
