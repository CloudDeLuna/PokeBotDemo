package fr.univaix.iut.pokebattle.smartcell.JudgeCell;

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
	public String ask(Tweet question) throws TwitterException {
		
		if ( question.getText().contains("#attack")) 
		{
			DAOOwner daoOwn = DAOFactory.createDAOOwner();
			DAOPokemon daoPoke = DAOFactory.createDAOPokemon();
			
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
				daoPoke.persist(poke);
				
				return phrase[0] + " -10pv /cc " + owner.getPrenom();
			}
		}
		return null;
	}

}
