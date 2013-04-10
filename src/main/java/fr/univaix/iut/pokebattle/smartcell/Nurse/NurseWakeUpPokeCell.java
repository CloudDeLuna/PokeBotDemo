package fr.univaix.iut.pokebattle.smartcell.Nurse;

import twitter4j.TwitterException;
import fr.univaix.iut.pokebattle.DAO.DAOFactory;
import fr.univaix.iut.pokebattle.DAO.DAOOwner;
import fr.univaix.iut.pokebattle.DAO.DAOPokemon;
import fr.univaix.iut.pokebattle.beans.Owner;
import fr.univaix.iut.pokebattle.beans.Pokemon;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class NurseWakeUpPokeCell implements SmartCell {

	public String ask(Tweet question) throws IllegalStateException, TwitterException {	
		
		if ( question.getText().contains("#DringDring")) 
		{
			DAOOwner daoOwn = DAOFactory.createDAOOwner();
			DAOPokemon daoPoke = DAOFactory.createDAOPokemon();
			
			String[] phrase = question.getText().split(" ");

			Pokemon poke = daoPoke.getByNom(phrase[4]);
			Owner owner = daoOwn.getByPokemon(poke);
			
			poke.setPV(Integer.parseInt(phrase[3]));
			daoPoke.persist(poke);
			
		   return owner.getPrenom() + " " + poke.getNom() + " is restored to full health";

		}
		return null;

	}//ask ()

}
