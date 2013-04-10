package fr.univaix.iut.pokebattle.smartcell.Nurse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import twitter4j.TwitterException;
import fr.univaix.iut.pokebattle.DAO.DAOFactory;
import fr.univaix.iut.pokebattle.DAO.DAOOwner;
import fr.univaix.iut.pokebattle.DAO.DAOPokemon;
import fr.univaix.iut.pokebattle.beans.Owner;
import fr.univaix.iut.pokebattle.beans.Pokemon;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class NursePVCell implements SmartCell {

	public String ask(Tweet question) throws IllegalStateException, TwitterException {	
		
		if ( question.getText().contains("#heal")) 
		{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("Pokemon");
	        EntityManager em = emf.createEntityManager();
			DAOFactory daof = new DAOFactory(em);
			DAOOwner daoOwn = daof.createDAOOwner();
			DAOPokemon daoPoke = daof.createDAOPokemon();
			
			String[] phrase = question.getText().split(" ");
			Pokemon poke = daoPoke.getByNom(phrase[2]);
			Owner owner = daoOwn.getByPokemon(poke);

			if ( owner.getPrenom().equals("@" + question.getScreenName())) 
			{
				poke = daoPoke.getByNom(phrase[2]);
				
		        return phrase[2] + " #stat #PV ?" ;
			}

		}//if contains attack
		return null;

	}//ask ()

}
