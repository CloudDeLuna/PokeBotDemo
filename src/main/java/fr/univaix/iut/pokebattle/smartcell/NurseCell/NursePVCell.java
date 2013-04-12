package fr.univaix.iut.pokebattle.smartcell.NurseCell;

import twitter4j.TwitterException;
import fr.univaix.iut.pokebattle.DAO.DAOFactory;
import fr.univaix.iut.pokebattle.DAO.DAOOwner;
import fr.univaix.iut.pokebattle.DAO.DAOPokemon;
import fr.univaix.iut.pokebattle.beans.Owner;
import fr.univaix.iut.pokebattle.beans.Pokemon;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class NursePVCell implements SmartCell {

	public String ask(Tweet question) throws TwitterException {	
		
		if ( question.getText().contains("#heal")) 
		{
			DAOOwner daoOwn = DAOFactory.createDAOOwner();
			DAOPokemon daoPoke = DAOFactory.createDAOPokemon();
			
			String[] phrase = question.getText().split(" ");
			Pokemon poke = daoPoke.getByNom(phrase[2]);
			Owner owner = daoOwn.getByPokemon(poke);

			if ( owner.getPrenom().equals("@" + question.getScreenName())) 
			{				
		        return phrase[2] + " #stat #PV ?" ;
			}

		}//if contains attack
		return null;

	}//ask ()

}
