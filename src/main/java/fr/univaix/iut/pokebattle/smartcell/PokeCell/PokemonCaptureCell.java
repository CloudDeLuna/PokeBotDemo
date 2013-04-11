package fr.univaix.iut.pokebattle.smartcell.PokeCell;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
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
public class PokemonCaptureCell implements SmartCell {

	@Override
	public String ask(Tweet question) throws TwitterException {
		
		if ( question.getText().contains("pokeball")) 
		{
			DAOOwner daoOwn = DAOFactory.createDAOOwner();
			DAOPokemon daoPoke = DAOFactory.createDAOPokemon();
			
			String[] phrase = question.getText().split(" ");
			Pokemon poke = daoPoke.getByNom(phrase[0]);
			Owner owner = daoOwn.getByPokemon(poke);
			
			
			if (owner == null) {
				Owner own = new Owner();
				
				own.setPrenom("@" + question.getScreenName());
				own.setPokemon(poke);
				
				Twitter twitter = TwitterFactory.getSingleton();
				String arg0 = poke.getNom();
				String arg1 = "";
				String arg2 = "";
				String arg3 = "#pokebattle - #pokemon - Owner: " + own.getPrenom() + " - Level: " + Poke.getNiveau();
				twitter.updateProfile(arg0, arg1, arg2, arg3);
						
				daoOwn.persist(own);
		        
				return "@" + question.getScreenName() + " @" + question.getScreenName() + " is my owner";
			}
			else
			{
				return "@" + question.getScreenName() + " " + owner.getPrenom() + " is my owner !";
			}
		}//if contains pokeball
		
		return null;
	}//ask()

}
