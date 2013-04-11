package fr.univaix.iut.pokebattle.DAO;
import static org.fest.assertions.Assertions.assertThat;

import java.sql.Connection;
import java.util.List;

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

import fr.univaix.iut.pokebattle.beans.Owner;

public class DAOOwnerTest {

    private static EntityManager entityManager;
    private static FlatXmlDataSet dataset;
    private static DatabaseConnection dbUnitConnection;
    private static EntityManagerFactory entityManagerFactory;

    private DAOOwner dao = DAOFactory.createDAOOwner();
    private DAOPokemon daoP = DAOFactory.createDAOPokemon();

    @BeforeClass
    public static void initTestFixture() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("PokemonPU");
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
    public void testFindAll() throws Exception {
        List<Owner> owners = dao.findAll();
        assertThat(owners.get(0).getPrenom()).isEqualTo("@CloudDeLuna");
        assertThat(owners.get(1).getPrenom()).isEqualTo("@cybsip");
    }

    @Test
    public void testFindByPrenom() throws Exception {
    	List<Owner> owners = dao.findByPrenom("@CloudDeLuna");
        assertThat(owners.get(0).getPokemon().getNom()).isEqualTo("@GwenGoupix");
        assertThat(owners.get(1).getPokemon().getNom()).isEqualTo("@Dracaufeu13");
    }
    
    @Test
    public void testGetByPokemon() throws Exception {
    	assertThat(dao.getByPokemon(daoP.getByNom("@GwenGoupix"))).isNotNull();
    }

    @Test
    public void testDelete() throws Exception {
        dao.delete(dao.getByPokemon(daoP.getByNom(("@GwenGoupix"))));
        assertThat(dao.getByPokemon(daoP.getByNom(("@GwenGoupix")))).isNull();
    }

    @Test
    public void testInsert() throws Exception {
    	dao.delete(dao.getByPokemon(daoP.getByNom(("@GwenGoupix"))));
    	
        Owner sacha = new Owner();
        
        sacha.setPokemon(daoP.getByNom(("@GwenGoupix")));
        sacha.setPrenom("@sacha");
        dao.persist(sacha);
        assertThat(dao.getByPokemon(daoP.getByNom(("@GwenGoupix"))).getPrenom()).isEqualTo("@sacha");
        assertThat(dao.getByPokemon(daoP.getByNom(("@GwenGoupix"))).getPokemon().getNom()).isEqualTo("@GwenGoupix");
    }
}