package fr.univaix.iut.pokebattle.smartcell.PokeCell;

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

import fr.univaix.iut.pokebattle.DAO.DAOFactory;
import fr.univaix.iut.pokebattle.smartcell.PokeCell.PokemonKOCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonKOCellTest {
	
	PokemonKOCell cell = new PokemonKOCell();
	
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
	public void test() {
		assertEquals("#KO /cc @Kyiio @cybsip @CloudDeLuna", cell.ask(new Tweet("Kyiio", "@GwenGoupix -10pv /cc @CloudDeLuna")));
	}

}
