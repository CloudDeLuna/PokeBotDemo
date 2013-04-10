package fr.univaix.iut.pokebattle.smartcell.JudgeCell;

import twitter4j.TwitterException;
import fr.univaix.iut.pokebattle.DAO.DAOCombat;
import fr.univaix.iut.pokebattle.DAO.DAOFactory;
import fr.univaix.iut.pokebattle.DAO.DAOPokemon;
import fr.univaix.iut.pokebattle.beans.Combat;
import fr.univaix.iut.pokebattle.beans.Pokemon;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeBotWinnerCell implements SmartCell {

	public String ask(Tweet question) throws IllegalStateException, TwitterException {	
		if ( question.getText().contains("#KO")) 
		{			
			DAOCombat daocombat = DAOFactory.createDAOCombat();
			DAOPokemon daoPoke = DAOFactory.createDAOPokemon();
			Pokemon poke = daoPoke.getByNom("@" + question.getScreenName());
			Combat combat = daocombat.getByPokemon(poke);
			
			Pokemon poke1 = combat.getPoke1();
			
			Pokemon poke2 = combat.getPoke2();
			
			daocombat.delete(combat);
			
			return (poke.equals(poke1) ? poke2.getNom() : poke1.getNom()) + " #win";
			
		}
		return null;
}
}