package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


import twitter4j.TwitterException;
import fr.univaix.iut.pokebattle.smartcell.PokemonAttackCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonAttackCellTest {

	PokemonAttackCell cell = new PokemonAttackCell();

	@Test
	public void testAttack() throws IllegalStateException, TwitterException {		
		assertEquals(
				"J'attaque @Smogogo13 du dresseur @Cybsip avec #Flammeche!," +
				" sur ordre de mon dresseur qui est @CloudDeLuna", (cell.ask(new Tweet("CloudDeLuna",
				"@GwenGoupix #attack #Flammeche @Smogogo13 /cc @Cybsip"))));
	}

}