package fr.univaix.iut.pokebattle.smartcell.NurseCell;

import twitter4j.TwitterException;
import fr.univaix.iut.pokebattle.DAO.DAOFactory;
import fr.univaix.iut.pokebattle.DAO.DAOOwner;
import fr.univaix.iut.pokebattle.DAO.DAOPokemon;
import fr.univaix.iut.pokebattle.beans.Owner;
import fr.univaix.iut.pokebattle.beans.Pokemon;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class NurseWakeUpPokeCell implements SmartCell {

	public String ask(Tweet question) throws TwitterException {	
		
		if ( question.getText().contains("#DringDring")) 
		{
			DAOOwner daoOwn = DAOFactory.createDAOOwner();
			DAOPokemon daoPoke = DAOFactory.createDAOPokemon();
			
			String[] phrase = question.getText().split(" ");
			final int trois = 3;
			final int quatre = 4;
			Pokemon poke = daoPoke.getByNom(phrase[quatre]);
			Owner owner = daoOwn.getByPokemon(poke);
			
			poke.setPV(Integer.parseInt(phrase[trois]));
			daoPoke.persist(poke);
			
		   return owner.getPrenom() + " " + poke.getNom() + " is restored to full health";

		}
		return null;

	}//ask ()

}
