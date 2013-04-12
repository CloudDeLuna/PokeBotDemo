package fr.univaix.iut.pokebattle.smartcell.PokeCell;

import twitter4j.TwitterException;
import fr.univaix.iut.pokebattle.DAO.DAOFactory;
import fr.univaix.iut.pokebattle.DAO.DAOPokemon;
import fr.univaix.iut.pokebattle.beans.Pokemon;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonWinXPCell implements SmartCell {

	public String ask(Tweet question) throws TwitterException 
	{	
		if ( question.getText().contains("#Win")) 
		{
			DAOPokemon daoPoke = DAOFactory.createDAOPokemon();
			String[] phrase = question.getText().split(" ");
			
			Pokemon poke = daoPoke.getByNom(phrase[0]);
			
			String experience = phrase[2].split("xp")[0].replace("+", "");
			
			int niveau = poke.getNiveau();
			int exp = Integer.parseInt(experience) + poke.getXP();
			int expNeed = niveau*niveau*niveau;

			while (expNeed <= exp)
			{
				++niveau;
				poke.setNiveau(niveau);
				exp = exp - expNeed;
				poke.setXP(exp);
				expNeed = niveau*niveau*niveau;
			}
			
			if (expNeed > exp)
			{
				poke.setXP(exp);
			}
			daoPoke.persist(poke);
		}
		
		return null;
	}
}
