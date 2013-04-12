package fr.univaix.iut.pokebattle.beans;

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

import fr.univaix.iut.pokebattle.DAO.DAOCombat;
import fr.univaix.iut.pokebattle.DAO.DAOFactory;
import fr.univaix.iut.pokebattle.DAO.DAOPokemon;

public class CombatTest {

	DAOCombat daoC = DAOFactory.createDAOCombat();
	DAOPokemon daoP = DAOFactory.createDAOPokemon();
	
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
		Combat cbt = new Combat (2, daoP.getByNom("@GwenGoupix"), "@Cloud", daoP.getByNom("@Dracaufeu13"),	
				"@Sacha");
		
		System.out.println(cbt.hashCode());
		System.out.println(cbt.toString());		
		cbt.setOwner1(null);
		cbt.setOwner2(null);
		cbt.setPoke1(null);
		cbt.setPoke2(null);
		
		System.out.println(cbt.hashCode());
	}

}
