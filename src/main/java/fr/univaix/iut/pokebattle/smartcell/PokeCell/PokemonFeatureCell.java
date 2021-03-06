package fr.univaix.iut.pokebattle.smartcell.PokeCell;

import fr.univaix.iut.pokebattle.DAO.DAOFactory;
import fr.univaix.iut.pokebattle.DAO.DAOPokemon;
import fr.univaix.iut.pokebattle.beans.Pokemon;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonFeatureCell implements SmartCell {
	
	  public String ask(Tweet question) 
	  {
		  	  
  		if ( question.getText().contains("#stat")) 
  		{
	  	      String[] phrase = question.getText().split(" ");
	  		  DAOPokemon daoPoke = DAOFactory.createDAOPokemon();
			  Pokemon poke = daoPoke.getByNom(phrase[0]);
			  
			  if( question.getText().contains("#race")) 
		  	  {
		  	   	return "@" + question.getScreenName() + " #race = " + poke.getRace() ;
		  	  }
			  
		  	  else if( question.getText().contains("#level")) 
		  	  {
		  	   	return "@" + question.getScreenName() + " #level = " + poke.getNiveau() ;
		  	  }
		  		
		  	  else if( question.getText().contains("#XP")) 
		  	  {
		  			return "@" + question.getScreenName() + " #XP = " + poke.getXP() ;
		  	  }
		  		
		  	  else if( question.getText().contains("#PV")) 
		  	  {
		  			return "@" + question.getScreenName() + " #PV = " + poke.getPV() + "/100";
		  	  }
		  	  
		  	  else if( question.getText().contains("#ATTSPE")) 
		  	  {
		  			return "@" + question.getScreenName() + " #ATTSPE = " + poke.getAttackSpecial();

		  	  }
		  	  
		  	  else if( question.getText().contains("#DEFSPE")) 
		  	  {
		  			return "@" + question.getScreenName() + " #DEFSPE = " + poke.getDefenseSpecial();
		  	  }
			  return ask2(question, poke);
  		}
	 	return null;
	 }//ask()
	  
	  public String ask2 (Tweet question, Pokemon poke)
	  {
	  	  if( question.getText().contains("#ATT")) 
	  	  {
	  			return "@" + question.getScreenName() + " #ATT = " + poke.getAttack();

	  	  }
	  	  else if( question.getText().contains("#DEF")) 
	  	  {
	  			return "@" + question.getScreenName() + " #DEF = " + poke.getDefense();

	  	  }

	  	  else if( question.getText().contains("#VIT")) 
	  	  {
	  			return "@" + question.getScreenName() + " #VIT = " + poke.getSpeed();
	  	  }
		  return null;
	  }
}//class
