package fr.univaix.iut.pokebattle.smartcell;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//import twitter4j.Twitter;
import twitter4j.TwitterException;
//import twitter4j.TwitterFactory;
import fr.univaix.iut.pokebattle.DAO.DAOFactory;
import fr.univaix.iut.pokebattle.DAO.DAOOwner;
import fr.univaix.iut.pokebattle.DAO.DAOPokemon;
import fr.univaix.iut.pokebattle.beans.DataObjectPokemon;
import fr.univaix.iut.pokebattle.beans.Owner;
import fr.univaix.iut.pokebattle.beans.Pokedex;
import fr.univaix.iut.pokebattle.beans.Pokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonAttackCell implements SmartCell {

		public String ask(Tweet question) throws IllegalStateException, TwitterException {	
			if ( question.getText().contains("#attack")) 
			{
				//Twitter twitter = TwitterFactory.getSingleton();
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("Pokemon");
		        EntityManager em = emf.createEntityManager();
				DAOFactory daof = new DAOFactory(em);
				DAOOwner daoOwn = daof.createDAOOwner();
				DAOPokemon daoPoke = daof.createDAOPokemon();
				
				String[] phrase = question.getText().split(" ");
				Pokemon Poke = daoPoke.getByNom(phrase[0]);
				Owner owner = daoOwn.getByPokemon(Poke);
									
	
				if ( owner.getPrenom().equals("@" + question.getScreenName())) 
				{
					Poke = daoPoke.getByNom(phrase[3]);
					
					Pokedex dex = Pokedex.getInstance();
					
					DataObjectPokemon Goupix = dex.getPokemon("Goupix");
					
					if (Goupix.getAttaques().);
					
					int PVPoke = Poke.getPV();
					Poke.setPV(PVPoke-10);
					em.getTransaction().begin();
					em.persist(Poke);
					em.getTransaction().commit();
					
					
			        return "J'attaque " + phrase[3] + " du dresseur " + phrase[5] + " avec " + phrase [2] 
			        		+ "!"  + ", sur ordre de mon dresseur qui est " + owner.getPrenom();  
					//return "@" + question.getScreenName() + ' ' + "@" + question.getScreenName() + "is my owner";
				}
				else
				{
					return "@" + question.getScreenName() + " " + owner.getPrenom() + " is my owner";
				}

			}//if contains attack
			return null;

		}//ask ()

}
