package fr.univaix.iut.pokebattle.smartcell.PokeCell;

import twitter4j.TwitterException;
import fr.univaix.iut.pokebattle.DAO.DAOFactory;
import fr.univaix.iut.pokebattle.DAO.DAOOwner;
import fr.univaix.iut.pokebattle.DAO.DAOPokemon;
import fr.univaix.iut.pokebattle.beans.Owner;
import fr.univaix.iut.pokebattle.beans.Pokemon;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonAttackCell implements SmartCell {

		public String ask(Tweet question) throws IllegalStateException, TwitterException {	
			if ( question.getText().contains("#attack")) 
			{
				DAOOwner daoOwn = DAOFactory.createDAOOwner();
				DAOPokemon daoPoke = DAOFactory.createDAOPokemon();
				
				String[] phrase = question.getText().split(" ");
				Pokemon poke = daoPoke.getByNom(phrase[0]);
				Owner owner = daoOwn.getByPokemon(poke);
									
	
					if ( owner.getPrenom().equals("@" + question.getScreenName()) && phrase[3].contains("@")) 
					{

				        return phrase[3] + " #attack " + phrase[2] + " /cc " + phrase[5] + " " + owner.getPrenom() + " " + phrase[6];  
					}
					else
					{
						return "@" + question.getScreenName() + " " + owner.getPrenom() + " is my owner";
					}

			}//if contains attack
			return null;

		}//ask ()

}
