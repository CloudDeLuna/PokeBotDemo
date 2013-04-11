package fr.univaix.iut.pokebattle.run;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.univaix.iut.pokebattle.DAO.DAOFactory;
import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.bot.NurseBot;
import fr.univaix.iut.pokebattle.bot.PokeBot;

public final class PokemonMain {
	
    private PokemonMain() {
		super();
	}

	public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Pokemon");
        EntityManager em = emf.createEntityManager();
        DAOFactory.setEntityManager(em);
        
        BotRunner.runBot(new PokeBot(), "twitter4j-Smogogo.properties");
        BotRunner.runBot(new JudgeBot(),"twitter4j-Kyiio.properties");
        BotRunner.runBot(new PokeBot(), "twitter4j-Goupix.properties");
        
        BotRunner.runBot(new NurseBot(), "twitter4j.properties");
    }
}