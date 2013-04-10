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

		public String ask(Tweet question) throws TwitterException {	
			if ( question.getText().contains("#attack")) 
			{
				DAOOwner daoOwn = DAOFactory.createDAOOwner();
				DAOPokemon daoPoke = DAOFactory.createDAOPokemon();
				
				final int deux = 2;
				final int trois = 3;
				final int cinq = 5;
				final int six = 6;
				
				String[] phrase = question.getText().split(" ");
				Pokemon poke = daoPoke.getByNom(phrase[0]);
				Owner owner = daoOwn.getByPokemon(poke);
									
	
					if ( owner.getPrenom().equals("@" + question.getScreenName()) && phrase[trois].contains("@")) 
					{

				        return phrase[trois] + " #attack " + phrase[deux] + " /cc " + phrase[cinq] + " " + owner.getPrenom() + " " + phrase[six];  
					}
					else
					{
						return "@" + question.getScreenName() + " " + owner.getPrenom() + " is my owner";
					}

			}//if contains attack
			return null;

		}//ask ()

}
