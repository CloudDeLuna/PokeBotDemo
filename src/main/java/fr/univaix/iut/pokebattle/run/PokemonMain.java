package fr.univaix.iut.pokebattle.run;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.bot.PokeBot;

public final class PokemonMain {
	
    private PokemonMain() {
		super();
	}

	public static void main(String[] args) {
    	
        BotRunner.runBot(new PokeBot(), "twitter4j-Smogogo.properties");
        BotRunner.runBot(new JudgeBot(),"twitter4j-Kyiio.properties");
        BotRunner.runBot(new PokeBot(), "twitter4j-Goupix.properties");
        
    }
}