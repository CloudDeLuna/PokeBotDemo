package fr.univaix.iut.pokebattle.smartcell.NurseCell;

import java.util.Random;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import fr.univaix.iut.pokebattle.DAO.DAOFactory;
import fr.univaix.iut.pokebattle.DAO.DAOOwner;
import fr.univaix.iut.pokebattle.DAO.DAOPokemon;
import fr.univaix.iut.pokebattle.beans.Owner;
import fr.univaix.iut.pokebattle.beans.Pokemon;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class NursePokeCenterCell implements SmartCell {
	
	public String ask(Tweet question) throws TwitterException {	
		
		if ( question.getText().contains("#PV")) 
		{
			Twitter twitter = TwitterFactory.getSingleton();

			DAOOwner daoOwn = DAOFactory.createDAOOwner();
			DAOPokemon daoPoke = DAOFactory.createDAOPokemon();
			
			String pokeName = question.getScreenName();
	
			Pokemon poke = daoPoke.getByNom(pokeName);
			Owner owner = daoOwn.getByPokemon(poke);
			
			if ( question.getText().contains("100/100"))
			{
				return pokeName + " full life /cc " + owner.getPrenom();
			}

			String[] dieze = question.getText().split("#");
			String[] egale = dieze[1].split("=");
			String[] nombre = egale[1].split("/");
			
			final int dix = 10;
			
			int timewu = (Integer.parseInt(nombre[1]) - Integer.parseInt(nombre[0]))/dix;
			Random r = new Random();
			int valeur = 0 + r.nextInt(100000000 - 0);

			
			twitter.updateStatus("@PokeTimer #WakeMeUp "+ timewu +" Min #MaxHealth " 
									+ nombre[1] 
											+ " " 
									+ pokeName + " " + valeur);			
			
		    return pokeName + " come in the #pokecenter /cc " + owner.getPrenom();

		}
		return null;

	}//ask ()


}
