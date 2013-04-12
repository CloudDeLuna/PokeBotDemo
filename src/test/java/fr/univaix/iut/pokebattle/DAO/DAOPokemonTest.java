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

import fr.univaix.iut.pokebattle.beans.Pokemon;

public class DAOPokemonTest {

    private static EntityManager entityManager;
    private static FlatXmlDataSet dataset;
    private static DatabaseConnection dbUnitConnection;
    private static EntityManagerFactory entityManagerFactory;

    private DAOPokemon dao = DAOFactory.createDAOPokemon();
    private DAOOwner daoO = DAOFactory.createDAOOwner();

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
        //Clean the data from previous test and insert new data test.
        DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection, dataset);
    }

    @Test
    public void testFindAll() throws Exception {
        List<Pokemon> pokemons = dao.findAll();
        assertThat(pokemons.get(0).getNom()).isEqualTo("@GwenGoupix");
        assertThat(pokemons.get(1).getNom()).isEqualTo("@Smogogo13");
    }

    @Test
    public void testGetByNom() throws Exception {
        assertThat(dao.getByNom("@GwenGoupix").getNom()).isEqualTo("@GwenGoupix");
    }

    @Test
    public void testInsert() throws Exception {
        Pokemon raichu = new Pokemon();
        raichu.setNom("Raichu");
        raichu.setRace("Souris");
        dao.persist(raichu);
        assertThat(dao.getByNom("Raichu").getNom()).isEqualTo("Raichu");
        assertThat(dao.getByNom("Raichu").getRace()).isEqualTo("Souris");
    }

    @Test
    public void testUpdate() throws Exception {
        Pokemon goupix = dao.getByNom("@GwenGoupix");
        assertThat(goupix.getAttack()).isGreaterThan(0);
        goupix.setAttack(-1);
        dao.persist(goupix);
        assertThat(dao.getByNom("@GwenGoupix").getAttack()).isLessThan(0);
    }
    
    @Test
    public void testDelete() throws Exception {
    	daoO.delete(daoO.getByPokemon(dao.getByNom("@Dracaufeu13")));
        dao.delete(dao.getByNom("@Dracaufeu13"));
        assertThat(dao.getByNom("@Dracaufeu13")).isNull();
    }
}