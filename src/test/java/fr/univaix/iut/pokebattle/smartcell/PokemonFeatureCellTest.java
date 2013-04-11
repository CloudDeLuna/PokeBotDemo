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
import fr.univaix.iut.pokebattle.smartcell.PokeCell.PokemonFeatureCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonFeatureCellTest {

    PokemonFeatureCell cell = new PokemonFeatureCell();
    
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
	public void testRace() {
		assertEquals("@CloudDeLuna #race = Goupix", cell.ask(new Tweet("CloudDeLuna", "@GwenGoupix #stat #race ?")));
		System.out.println(cell.ask(new Tweet("CloudDeLuna", "@GwenGoupix #stat #race ?")));
	}
	
	@Test
	public void testLevel() {
		assertEquals("@CloudDeLuna #level = N.1", cell.ask(new Tweet("CloudDeLuna", "@GwenGoupix #stat #level ?")));
		System.out.println(cell.ask(new Tweet("CloudDeLuna", "@GwenGoupix #stat #level ?")));
	}
	
	@Test
	public void testXP() {
		System.out.println(cell.ask(new Tweet("CloudDeLuna", "@GwenGoupix #stat #XP ?")));
		assertEquals("@CloudDeLuna #XP = XP.0", cell.ask(new Tweet("CloudDeLuna", "@GwenGoupix #stat #XP ?")));
	}
	
	/*@Test
	public void testPV() {
		System.out.println(cell.ask(new Tweet("CloudDeLuna", "@GwenGoupix #stat #PV ?")));
		assertEquals("@CloudDeLuna #PV = 70/100", cell.ask(new Tweet("CloudDeLuna", "@GwenGoupix #stat #PV ?")));
	}*/
	
	@Test
	public void testAtt() {
		System.out.println(cell.ask(new Tweet("CloudDeLuna", "@GwenGoupix #stat #ATT ?")));
		assertEquals("@CloudDeLuna #ATT = 2", cell.ask(new Tweet("CloudDeLuna", "@GwenGoupix #stat #ATT ?")));
	}
	
	@Test
	public void testDef() {
		System.out.println(cell.ask(new Tweet("CloudDeLuna", "@GwenGoupix #stat #DEF ?")));
		assertEquals("@CloudDeLuna #DEF = 0", cell.ask(new Tweet("CloudDeLuna", "@GwenGoupix #stat #DEF ?")));
	}
	
	@Test
	public void testAttSpe() {
		System.out.println(cell.ask(new Tweet("CloudDeLuna", "@GwenGoupix #stat #ATTSPE ?")));
		assertEquals("@CloudDeLuna #ATTSPE = 0", cell.ask(new Tweet("CloudDeLuna", "@GwenGoupix #stat #ATTSPE ?")));
	}
	
	@Test
	public void testDefSpe() {
		System.out.println(cell.ask(new Tweet("CloudDeLuna", "@GwenGoupix #stat #DEFSPE ?")));
		assertEquals("@CloudDeLuna #DEFSPE = 0", cell.ask(new Tweet("CloudDeLuna", "@GwenGoupix #stat #DEFSPE ?")));
	}
	
	@Test
	public void testVit() {
		System.out.println(cell.ask(new Tweet("CloudDeLuna", "@GwenGoupix #stat #VIT ?")));
		assertEquals("@CloudDeLuna #VIT = 0", cell.ask(new Tweet("CloudDeLuna", "@GwenGoupix #stat #VIT ?")));
	}

}
