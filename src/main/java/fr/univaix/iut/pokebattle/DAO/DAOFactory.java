package fr.univaix.iut.pokebattle.DAO;

import javax.persistence.EntityManager;

public final class DAOFactory {
	
	private static EntityManager entityManager;
	
    private DAOFactory() {
		super();
	}

	public static synchronized void setEntityManager(EntityManager entityManager){
    	DAOFactory.entityManager = entityManager;
    }

	public static DAOOwner createDAOOwner() {
		return new DAOOwner(entityManager);
	}
	
	public static DAOPokemon createDAOPokemon() {
		return new DAOPokemon(entityManager);
	}
	
	public static DAOAttacks createDAOAttacks() {
		return new DAOAttacks(entityManager);
	}
	
	public static DAOCombat createDAOCombat() {
		return new DAOCombat(entityManager);

	}

}