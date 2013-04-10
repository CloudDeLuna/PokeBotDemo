package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import twitter4j.TwitterException;
import fr.univaix.iut.pokebattle.DAO.DAOFactory;
import fr.univaix.iut.pokebattle.smartcell.PokeCell.PokemonCaptureCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonCaptureCellTest {

    PokemonCaptureCell cell = new PokemonCaptureCell();
    
	@BeforeClass
    public static void initTestFixture() throws Exception {
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Pokemon");
        EntityManager em = emf.createEntityManager();
        DAOFactory.setEntityManager(em);
        
    }
    
    @Test
    public void testCaptureFalse() throws IllegalStateException, TwitterException 
    {
    	assertEquals("@CloudDeLuna @CloudDeLuna is my owner !", cell.ask(new Tweet("CloudDeLuna","@GwenGoupix pokeball goo !")));
    }
}