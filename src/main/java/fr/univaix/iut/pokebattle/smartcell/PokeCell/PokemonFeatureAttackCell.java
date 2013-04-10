package fr.univaix.iut.pokebattle.smartcell.PokeCell;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.univaix.iut.pokebattle.DAO.DAOAttacks;
import fr.univaix.iut.pokebattle.DAO.DAOFactory;
import fr.univaix.iut.pokebattle.DAO.DAOPokemon;
import fr.univaix.iut.pokebattle.beans.Attacks;
import fr.univaix.iut.pokebattle.beans.Pokemon;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;


public class PokemonFeatureAttackCell implements SmartCell {
	
	 public String ask(Tweet question) {
	  	  
	  		if ( question.getText().contains("#statAttack")) 
	  		{
		  		  EntityManagerFactory emf = Persistence.createEntityManagerFactory("Pokemon");
		  	      EntityManager em = emf.createEntityManager();
		  	      
		  	      String[] phrase = question.getText().split(" ");
		  	      
		  	      DAOFactory daoF = new DAOFactory(em);
		  	      
		  		  DAOPokemon daoPoke = daoF.createDAOPokemon();
				  Pokemon poke = daoPoke.getByNom(phrase[0]);
				  
				  DAOAttacks daoAtt = daoF.createDAOAttacks();
				  List<Attacks> listAtt = daoAtt.findByPokemon(poke);
				  
				  String [] var = phrase[3].split("#");
				  String attack = var[1];
				  Attacks attEnCours = null;
				  
				  boolean know = false;
				  
				  for (Attacks i : listAtt)
				  {
					  if (i.getAttack().equals(attack))
					  {
						  know = true;
						  attEnCours = i;
						  break;
					  }
					  else
					  {
						  know = false;
					  }
				  }
				  if (!know)
				  {
					  return "@" + question.getScreenName() + " O_o ?";
				  }
					  
				  if ( question.getText().contains("#PP")) 
			  	  { 
					  return "@" + question.getScreenName() + " #" + attEnCours.getAttack() +  " #PP=" 
							  + attEnCours.getPP() + "/" + attEnCours.getPPMAX();
			  	  }
				  else if ( question.getText().contains("#PRECISION") )
				  {
					  return "@" + question.getScreenName() + " #" + attEnCours.getAttack() +  " #PRECISION=" 
							  + attEnCours.getPrecision();
				  }
				  else if ( question.getText().contains("#PUISSANCE") )
				  {
					  return "@" + question.getScreenName() + " #" + attEnCours.getAttack() +  " #PUISSANCE=" 
							  + attEnCours.getPuissance();
				  }
				  else if ( question.getText().contains("#NIVEAU") )
				  {
					  return "@" + question.getScreenName() + " #" + attEnCours.getAttack() +  " #NIVEAU=" 
							  + attEnCours.getNiveau();
				  }
	  		}//if ( question.getText().contains("#statAttack")) 
	  
	 	return null;
	  	
	 }//ask()
}//class()
	
