package fr.univaix.iut.pokebattle.bot;

import static org.fest.assertions.Assertions.assertThat;
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
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class NurseBotTest {
    NurseBot nurseBot = new NurseBot();
    PokeBot	pokeBot = new PokeBot();
    
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

        dataset = new FlatXmlDataSetBuilder().build(Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("pokemonDataSet.xml"));
        
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
	public void test() throws IllegalStateException, TwitterException {
        assertEquals("@GwenGoupix #stat #PV ? #pokebattle", nurseBot.ask(new Tweet("CloudDeLuna", "@JoelleBourgPalet #heal @GwenGoupix")));
	}
	
	@Test
	public void testPV() throws IllegalStateException, TwitterException {
        assertEquals("@JoelleBourgPalet #PV = 100/100 #pokebattle", pokeBot.ask(new Tweet("JoelleBourgPalet", "@GwenGoupix #stat #PV ?")));
	}
	
	/*@Test
	public void testFulllife() throws IllegalStateException, TwitterException {
        assertEquals("@GwenGoupix come in the #pokecenter /cc @CloudDeLuna", nurseBot.ask(new Tweet("@GwenGoupix", "@JoelleBourgPalet #PV =90/100")));
	}*/
	
	@Test
	public void testDringDring() throws IllegalStateException, TwitterException {
        assertEquals("@CloudDeLuna @GwenGoupix is restored to full health #pokebattle", nurseBot.ask(new Tweet("@PokeTimer", "@JoelleBourgPalet #DringDring #MaxHealth 100 @GwenGoupix @CloudDeLuna")));
	}
	
	@Test
	public void testNonNurse() throws IllegalStateException, TwitterException
	{
        assertThat(nurseBot.ask(new Tweet("Salut"))).isNull();
        assertThat(nurseBot.ask(new Tweet("This is not a question."))).isNull();
	}

}
