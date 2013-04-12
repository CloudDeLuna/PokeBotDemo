package fr.univaix.iut.pokebattle.smartcell.JudgeCell;

import twitter4j.TwitterException;
import fr.univaix.iut.pokebattle.DAO.DAOCombat;
import fr.univaix.iut.pokebattle.DAO.DAOFactory;
import fr.univaix.iut.pokebattle.DAO.DAOPokemon;
import fr.univaix.iut.pokebattle.beans.Combat;
import fr.univaix.iut.pokebattle.beans.DataObjectPokemon;
import fr.univaix.iut.pokebattle.beans.Pokedex;
import fr.univaix.iut.pokebattle.beans.Pokemon;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeBotWinnerCell implements SmartCell {

	public String ask(Tweet question) throws TwitterException {	
		if ( question.getText().contains("#KO")) 
		{			
			Pokedex pok = Pokedex.getInstance();
		    DataObjectPokemon[] pokeListe = pok.getData();
			
			DAOCombat daocombat = DAOFactory.createDAOCombat();
			DAOPokemon daoPoke = DAOFactory.createDAOPokemon();
			Pokemon poke = daoPoke.getByNom("@" + question.getScreenName());
			Combat combat = daocombat.getByPokemon(poke);

			Pokemon poke1 = combat.getPoke1();
			Pokemon poke2 = combat.getPoke2();
			
			daocombat.delete(combat);

			String vainqueur = (poke.equals(poke1) ? poke2.getNom() : poke1.getNom());
			int niveau = poke.getNiveau();
			
	        Pokemon pokeVainq = daoPoke.getByNom(vainqueur);
	        int expval = 0;
	        for (DataObjectPokemon j : pokeListe)
	        {
	        	if (pokeVainq.getRace().equals(j.getNom()))
	        	{
	        		expval = j.getExpval();
	        	}
	        }
	        
	        final int sept = 7;
	        int exp = expval*niveau/sept;
	       
	        
			return vainqueur + " #Win +" + exp + "xp";			
		}
		return null;
	}
}