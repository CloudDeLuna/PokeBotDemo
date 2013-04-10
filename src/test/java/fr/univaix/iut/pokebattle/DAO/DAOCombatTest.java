package fr.univaix.iut.pokebattle.DAO;
import static org.fest.assertions.Assertions.assertThat;

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

import fr.univaix.iut.pokebattle.beans.Combat;

public class DAOCombatTest {

    private DAOCombat dao = DAOFactory.createDAOCombat();
    private DAOPokemon daoP = DAOFactory.createDAOPokemon();
    
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
        //Clean the data from previous test and insert new data test.
        DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection, dataset);
    }
    
    @Test
    public void testGetByPokemon() throws Exception {
    	assertThat(dao.getByPokemon(daoP.getByNom("@GwenGoupix"))).isNotNull();
    }
    
    @Test
    public void testGetByOwner() throws Exception {
    	assertThat(dao.getByOwner("@CloudDeLuna")).isNotNull();
    }
    
    @Test
    public void testGetMaxNumCB() throws Exception {
    	assertThat(dao.getMaxNumCB()).isGreaterThan(0);
    }

    @Test
    public void testDelete() throws Exception {
        dao.delete(dao.getByPokemon(daoP.getByNom(("@GwenGoupix"))));
        assertThat(dao.getByPokemon(daoP.getByNom(("@GwenGoupix")))).isNull();
    }

    @Test
    public void testInsert() throws Exception {
    	dao.delete(dao.getByPokemon(daoP.getByNom(("@GwenGoupix"))));
    	Combat fight = new Combat();
    	fight.setIdCombat(3);
    	fight.setOwner1("@Sacha");
    	fight.setPoke1(daoP.getByNom("@Dracaufeu13"));
    	fight.setPoke2(daoP.getByNom("INCONNU"));

        dao.insert(fight);
        assertThat(dao.getByOwner("@Sacha").getPoke1().getNom()).isEqualTo("@Dracaufeu13");
    }
}