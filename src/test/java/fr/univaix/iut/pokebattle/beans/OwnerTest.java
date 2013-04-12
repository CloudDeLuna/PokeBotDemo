package fr.univaix.iut.pokebattle.beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

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
import fr.univaix.iut.pokebattle.DAO.DAOOwner;
import fr.univaix.iut.pokebattle.DAO.DAOPokemon;

public class OwnerTest {
	
		DAOPokemon daoP = DAOFactory.createDAOPokemon();
		DAOOwner daoO = DAOFactory.createDAOOwner();
		
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
			Owner sacha = new Owner (daoP.getByNom("INCONNU"), "@Sacha");
			daoO.persist(sacha);
			
			assertEquals(daoO.getByPokemon(daoP.getByNom("INCONNU")), sacha);
			Owner own = daoO.getByPokemon(daoP.getByNom("@GwenGoupix"));
			if (own==sacha);
			System.out.println(sacha.hashCode());
			
			own.setPrenom("cc");
			if (own==sacha);
			own.setPrenom(null);
			if (own==sacha);
			
			own.setPokemon(daoP.getByNom("@Dracaufeu13"));
			if (own==sacha);
			
			own.setPokemon(null);
			if (own==sacha);
			
			System.out.println(sacha.toString());
	
			if (own==sacha);
			
			sacha.setPokemon(null);
			sacha.setPrenom(null);
			System.out.println(sacha.hashCode());
		}

}
