package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.eclipse.persistence.internal.jpa.EntityManagerImpl;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import twitter4j.TwitterException;
import fr.univaix.iut.pokebattle.DAO.DAOFactory;
import fr.univaix.iut.pokebattle.smartcell.PokeCell.PokemonAttackCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonAttackCellTest {

	PokemonAttackCell cell = new PokemonAttackCell();

	   private static EntityManager entityManager;
	    private static FlatXmlDataSet dataset;
	    private static DatabaseConnection dbUnitConnection;
	    private static EntityManagerFactory entityManagerFactory;

	    @BeforeClass
	    public static void initTestFixture() throws Exception {
	        entityManagerFactory = Persistence.createEntityManagerFactory("Pokemon");
	        entityManager = entityManagerFactory.createEntityManager();

	        Connection connection = ((EntityManagerImpl) (entityManager.getDelegate())).getServerSession().getAccessor().getConnection();

	        dbUnitConnection = new DatabaseConnection(connection);
	        //Loads the data set from a file

	        dataset = new FlatXmlDataSetBuilder().build(Thread.currentThread()
	                .getContextClassLoader()
	                .getResourceAsStream("pokemonMortDataSet.xml"));
	        
	        DAOFactory.setEntityManager(entityManager);
	    }

	    @AfterClass
	    public static void finishTestFixture() throws Exception {
	        entityManager.close();
	        entityManagerFactory.close();
	    }

	    @Before
	    public void setUp() throws Exception {
	        DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection, dataset);
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