package fr.univaix.iut.pokebattle.bot;

import org.junit.Test;

import fr.univaix.iut.pokebattle.run.BotRunner;
import fr.univaix.iut.pokebattle.run.PokemonMain;

public class BotRunnerTest {

	@Test
	public void test() {
		BotRunner.runBot(new PokeBot(), "twitter4j-Smogogo.properties");
	}
	
	@Test
	public void tests() {
		PokemonMain.main(null);
	}

}
