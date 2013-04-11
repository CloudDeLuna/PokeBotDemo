package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.*;

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

import fr.univaix.iut.pokebattle.DAO.DAOFactory;
import fr.univaix.iut.pokebattle.smartcell.PokeCell.PokemonFeatureAttackCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonFeatureAttackCellTest {

	PokemonFeatureAttackCell cell = new PokemonFeatureAttackCell();
	
	   private static EntityManager entityManager;
	    private static FlatXmlDataSet dataset;
	    private static DatabaseConnection dbUnitConnection;
	    private static EntityManagerFactory entityManagerFactory;

	    @BeforeClass
	    public static void initTestFixture() throws Exception {
	        entityManagerFactory = Persistence.createEntityManagerFactory("PokemonPU");
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
	public void testPP() {
		System.out.println(cell.ask(new Tweet("CloudDeLuna", "@GwenGoupix #statAttack #PP #Hurlement ?")));
		assertEquals("@CloudDeLuna #Hurlement #PP=20/20", cell.ask(new Tweet("CloudDeLuna", "@GwenGoupix #statAttack #PP #Hurlement ?")));
	}
	
	@Test
	public void testPrecision() {
		System.out.println(cell.ask(new Tweet("CloudDeLuna", "@GwenGoupix #statAttack #PRECISION #Hurlement ?")));
		assertEquals("@CloudDeLuna #Hurlement #PRECISION=100", cell.ask(new Tweet("CloudDeLuna", "@GwenGoupix #statAttack #PRECISION #Hurlement ?")));
	}
	
	@Test
	public void testPuissance() {
		System.out.println(cell.ask(new Tweet("CloudDeLuna", "@GwenGoupix #statAttack #PUISSANCE #Hurlement ?")));
		assertEquals("@CloudDeLuna #Hurlement #PUISSANCE=-", cell.ask(new Tweet("CloudDeLuna", "@GwenGoupix #statAttack #PUISSANCE #Hurlement ?")));
	}
	
	@Test
	public void testNiveau() {
		System.out.println(cell.ask(new Tweet("CloudDeLuna", "@GwenGoupix #statAttack #NIVEAU #Hurlement ?")));
		assertEquals("@CloudDeLuna #Hurlement #NIVEAU=N.9", cell.ask(new Tweet("CloudDeLuna", "@GwenGoupix #statAttack #NIVEAU #Hurlement ?")));
	}

}
