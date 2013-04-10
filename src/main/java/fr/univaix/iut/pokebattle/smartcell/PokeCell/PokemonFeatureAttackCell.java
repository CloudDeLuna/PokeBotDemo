package fr.univaix.iut.pokebattle.smartcell.PokeCell;

import java.util.List;

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
		  	      String[] phrase = question.getText().split(" ");
		  	      
		  		  DAOPokemon daoPoke = DAOFactory.createDAOPokemon();
				  Pokemon poke = daoPoke.getByNom(phrase[0]);
				  
				  DAOAttacks daoAtt = DAOFactory.createDAOAttacks();
				  List<Attacks> listAtt = daoAtt.findByPokemon(poke);
				  
				  final int trois = 3;
				  String [] var = phrase[trois].split("#");
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
	  		}
	  
	 	return null;
	  	
	 }//ask()
}//class()
	
