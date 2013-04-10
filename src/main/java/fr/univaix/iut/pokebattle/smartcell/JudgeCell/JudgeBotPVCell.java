package fr.univaix.iut.pokebattle.smartcell.JudgeCell;

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

public class JudgeBotPVCell implements SmartCell{

	@Override
	public String ask(Tweet question) throws IllegalStateException, TwitterException {
		
		if ( question.getText().contains("#attack")) 
		{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("Pokemon");
	        EntityManager em = emf.createEntityManager();
			DAOFactory daof = new DAOFactory(em);
			DAOOwner daoOwn = daof.createDAOOwner();
			DAOPokemon daoPoke = daof.createDAOPokemon();
			
			String[] phrase = question.getText().split(" ");
			
			Pokemon poke = daoPoke.getByNom(phrase[0]);
			Owner owner = daoOwn.getByPokemon(poke);
			
			if (phrase[3].contains("@") )
			{
				return "salut";
			}
			else 
			{
				
				int pVPoke = poke.getPV();
				poke.setPV(pVPoke-10);
				em.getTransaction().begin();
				em.persist(poke);
				em.getTransaction().commit();
				
				return phrase[0] + " -10pv /cc " + owner.getPrenom();
			}
		}
		return null;
	}

}
