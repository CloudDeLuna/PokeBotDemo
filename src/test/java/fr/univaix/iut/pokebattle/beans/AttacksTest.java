package fr.univaix.iut.pokebattle.beans;

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

import fr.univaix.iut.pokebattle.DAO.DAOAttacks;
import fr.univaix.iut.pokebattle.DAO.DAOFactory;
import fr.univaix.iut.pokebattle.DAO.DAOPokemon;

public class AttacksTest {
	 private static EntityManager entityManager;
	    private static FlatXmlDataSet dataset;
	    private static DatabaseConnection dbUnitConnection;
	    private static EntityManagerFactory entityManagerFactory;

	    private DAOAttacks dao = DAOFactory.createDAOAttacks();
	    private DAOPokemon daoP = DAOFactory.createDAOPokemon();

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
	public void test() {
		Attacks att = new Attacks();
		List<Attacks> attaques = dao.findAll();
		
		att.setAttack(attaques.get(0).getAttack());
		att.setNiveau(attaques.get(0).getNiveau());
		att.setPP(attaques.get(0).getPP());
		att.setPPMAX(attaques.get(0).getPPMAX());
		att.setPrecision(attaques.get(0).getPrecision());
		att.setPuissance(attaques.get(0).getPuissance());
		att.setPokemon(daoP.getByNom("@Dracaufeu13"));
		
		assertThat(att.getAttack()).isEqualTo(attaques.get(0).getAttack());
		assertThat(att.getNiveau()).isEqualTo(attaques.get(0).getNiveau());
		assertThat(att.getPPMAX()).isEqualTo(attaques.get(0).getPPMAX());
		assertThat(att.getPP()).isEqualTo(attaques.get(0).getPP());
		assertThat(att.getPuissance()).isEqualTo(attaques.get(0).getPuissance());
		assertThat(att.getPrecision()).isEqualTo(attaques.get(0).getPrecision());
		
        assertThat(att.getPokemon().getNom()).isEqualTo("@Dracaufeu13");
        
		System.out.println(att.toString());
		System.out.println(att.hashCode());
	}

}