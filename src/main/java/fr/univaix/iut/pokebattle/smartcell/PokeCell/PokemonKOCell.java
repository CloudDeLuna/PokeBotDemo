package fr.univaix.iut.pokebattle.smartcell.PokeCell;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.univaix.iut.pokebattle.DAO.DAOCombat;
import fr.univaix.iut.pokebattle.DAO.DAOFactory;
import fr.univaix.iut.pokebattle.DAO.DAOOwner;
import fr.univaix.iut.pokebattle.DAO.DAOPokemon;
import fr.univaix.iut.pokebattle.beans.Combat;
import fr.univaix.iut.pokebattle.beans.Owner;
import fr.univaix.iut.pokebattle.beans.Pokemon;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonKOCell implements SmartCell {

	@Override
	public String ask(Tweet question){
		if (question.getText().contains("pv")) 
		{
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("Pokemon");
			EntityManager em = emf.createEntityManager();
			
			DAOFactory daof = new DAOFactory(em);
			
			DAOPokemon daoPoke = daof.createDAOPokemon();
			DAOCombat daocombat = daof.createDAOCombat();
			
			String[] phrase = question.getText().split(" ");
			Pokemon poke = daoPoke.getByNom(phrase[0]);
			Combat cb = daocombat.getByPokemon(poke);
			Pokemon poke1 = cb.getPoke1();
			Pokemon poke2 = cb.getPoke2();
			
			
			
			DAOOwner daoow = daof.createDAOOwner();
			Owner ow1 = daoow.getByPokemon(poke1);
			Owner ow2 = daoow.getByPokemon(poke2);
			
			
			int pVPoke = poke.getPV();
			
			if (pVPoke == 0 || pVPoke < 0 )
			{
				return "#KO /cc @" + question.getScreenName() + " " 
			+ (poke.equals(poke1) ? ow2.getPrenom() : ow1.getPrenom()) + " " + (poke.equals(poke1) ? ow1.getPrenom() : ow2.getPrenom()) ;
			}
			else
			{
				return null;
			}
		}
		return null;
	}
}
