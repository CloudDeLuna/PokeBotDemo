package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;


import twitter4j.TwitterException;
import fr.univaix.iut.pokebattle.DAO.DAOFactory;
import fr.univaix.iut.pokebattle.smartcell.PokeCell.PokemonAttackCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonAttackCellTest {

	PokemonAttackCell cell = new PokemonAttackCell();

	@BeforeClass
    public static void initTestFixture() throws Exception {
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Pokemon");
        EntityManager em = emf.createEntityManager();
        DAOFactory.setEntityManager(em);
        
    }
	
	@Test
	public void testAttack() throws IllegalStateException, TwitterException {		
		assertEquals(
				"@Smogogo13 #attack #charge /cc @cybsip @CloudDeLuna @Kyiio", (cell.ask(new Tweet("CloudDeLuna",
				"@Dracaufeu13 #attack #charge @Smogogo13 /cc @cybsip @Kyiio"))));
	}
//	pcreux: "@bulbizare1 #attack #charge @pikachuNyanNian /cc @nedseb"
	//	bulbizare1: "@pikachuNyanNian #attack #charge /cc @nedseb @pcreux"
}