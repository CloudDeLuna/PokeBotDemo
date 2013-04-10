package fr.univaix.iut.pokebattle.smartcell.JudgeCell;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import twitter4j.TwitterException;
import fr.univaix.iut.pokebattle.DAO.DAOCombat;
import fr.univaix.iut.pokebattle.DAO.DAOFactory;
import fr.univaix.iut.pokebattle.DAO.DAOOwner;
import fr.univaix.iut.pokebattle.DAO.DAOPokemon;
import fr.univaix.iut.pokebattle.beans.Combat;
import fr.univaix.iut.pokebattle.beans.Owner;
import fr.univaix.iut.pokebattle.beans.Pokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeBotRound {

	public String ask(Tweet question) throws IllegalStateException, TwitterException {
		
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("Pokemon");
	        EntityManager em = emf.createEntityManager();
			DAOFactory daof = new DAOFactory(em);
			DAOOwner daoOwn = daof.createDAOOwner();
			DAOCombat daoCb = daof.createDAOCombat();
			DAOPokemon daoPoke = daof.createDAOPokemon();
			

			
			Pattern pattern = Pattern.compile("(@[^ ]+) #fight #ok with (@[^ ]+) /cc (@[^ ]+)");
			Matcher matcher = pattern.matcher(question.getText());
			//"Round #1 /cc @nedseb @pikachuNyanNian @pcreux @bulbizare1"
			if(matcher.matches())
			{
				
			}
			
	
			return null;
			
				
		}

}
