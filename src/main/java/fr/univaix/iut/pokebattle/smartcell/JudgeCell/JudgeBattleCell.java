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
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;


public class JudgeBattleCell implements SmartCell {
	public String ask(Tweet question) throws IllegalStateException, TwitterException {	

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Pokemon");
        EntityManager em = emf.createEntityManager();
		DAOFactory daof = new DAOFactory(em);
		DAOOwner daoOwn = daof.createDAOOwner();
		DAOCombat daoCb = daof.createDAOCombat();
		DAOPokemon daoPoke = daof.createDAOPokemon();
		
		//"@nedseb #fight with @bulbizare1 /cc @viviane"
		Pattern pattern1 = Pattern.compile("(@[^ ]+) #fight with (@[^ ]+) /cc (@[^ ]+) #[^ ]");
		Matcher matcher1 = pattern1.matcher(question.getText());
		
		Pattern pattern2 = Pattern.compile("(@[^ ]+) #fight #ok with (@[^ ]+) /cc (@[^ ]+) #[^ ]");
		Matcher matcher2 = pattern2.matcher(question.getText());
		
		if(matcher1.matches())
		{
			String nomDresseurAdversaire = matcher1.group(1);
			String nomPokemon = matcher1.group(2);
			String nomJuge = matcher1.group(3);
			String nomDresseur = "@"+question.getScreenName();
			
			Pokemon pokemon1 = daoPoke.getByNom(nomPokemon);
			if ( pokemon1 == null )
			{
				return "Poke inexistant";
			}
			Owner ow1 = daoOwn.getByPokemon(pokemon1);
			
			Pokemon pokemon2 = daoPoke.getByNom("INCONNU");			
			
			if ( ow1.getPrenom().equals(nomDresseur) )
			{
				
				Combat cb = new Combat ();
				cb = daoCb.getByJuge(nomJuge);
				
				if ( cb != null )
				{
					Combat cbJuste = new Combat ( nomJuge , pokemon1 , ow1.getPrenom(),
												pokemon2 , nomDresseurAdversaire , 0 , 0 );
					daoCb.insert(cbJuste);
					return "skip";
				}
			}
			else
			{
				return  "@"+question.getScreenName() + " " + ow1.getPrenom() + " is my owner";
			}
		}
		else if (matcher2.matches())
		{

			String nomDresseur = matcher2.group(1);
			String nomPokemonAdversaire = matcher2.group(2);
			
			String nomDresseurAdversaire = "@"+question.getScreenName();
			
			Pokemon pokemon2 = daoPoke.getByNom(nomPokemonAdversaire);
			Owner ow2 = daoOwn.getByPokemon(pokemon2) ;
			if ( ow2.getPrenom().equals(nomDresseurAdversaire) )
			{
				Combat cb = daoCb.getByOwner(ow2.getPrenom());
				cb.setPoke2(pokemon2);
				cb.setNumRound(1);
				daoCb.update(cb);
				return "Round #"+cb.getNumRound() +" /cc "+cb.getOwner1() + " " + cb.getPoke1().getNom()
						+ " " + cb.getOwner2() + " " + cb.getPoke2().getNom();
			}
			else 
			{
				Owner ow1 = new Owner (null , nomDresseur ) ;
				Combat cb = daoCb.getByOwner(ow1.getPrenom());
				daoCb.delete(cb);
				return "Le pokemon séléctionné ne vous appartient pas, fin du combat !!!";
			}
		}
		return null;
		
			
	}

}