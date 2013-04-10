package fr.univaix.iut.pokebattle.smartcell.PokeCell;

import twitter4j.TwitterException;
import fr.univaix.iut.pokebattle.DAO.DAOFactory;
import fr.univaix.iut.pokebattle.DAO.DAOOwner;
import fr.univaix.iut.pokebattle.DAO.DAOPokemon;
import fr.univaix.iut.pokebattle.beans.Owner;
import fr.univaix.iut.pokebattle.beans.Pokemon;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

/**
 * Reply to all.
 */
public class PokemonOwnerCell implements SmartCell {

	@Override
	public String ask(Tweet question) throws IllegalStateException, TwitterException {
		
		if (question.getText().contains("owner")) 
		{
			DAOOwner daoOwner = DAOFactory.createDAOOwner();
			
			DAOPokemon daoPoke = DAOFactory.createDAOPokemon();
			
			String[] phrase = question.getText().split(" ");
			Pokemon poke = daoPoke.getByNom(phrase[0]);
			
			Owner owner = daoOwner.getByPokemon(poke);
			
			if (owner != null) {
				return "@" + question.getScreenName() + ' ' + owner.getPrenom() + " is my owner"  ;
			}
			return "@" + question.getScreenName() + " No owner";

		}
		return null;
	}

}
