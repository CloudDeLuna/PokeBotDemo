package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import twitter4j.TwitterException;
import fr.univaix.iut.pokebattle.DAO.DAOFactory;
import fr.univaix.iut.pokebattle.smartcell.JudgeCell.JudgeBattleCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeBattleCellTest {
	
	JudgeBattleCell cell = new JudgeBattleCell();
    
	@BeforeClass
    public static void initTestFixture() throws Exception {
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Pokemon");
        EntityManager em = emf.createEntityManager();
        DAOFactory.setEntityManager(em);
        
    }
	
	@Test
	public void test() throws IllegalStateException, TwitterException {
		assertEquals( "skip" ,cell.ask(new Tweet("cybsip","@CloudDeLuna #fight with @Smogogo13 /cc @Kyiio") ));
		
		
		assertEquals( null ,cell.ask(new Tweet("CloudDeLuna","@cybsip #fight #ok with @GwenGoupix /cc @Kyiio") ));
	}

}
