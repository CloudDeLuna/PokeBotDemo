package fr.univaix.iut.pokebattle.DAO;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.univaix.iut.pokebattle.beans.Combat;
import fr.univaix.iut.pokebattle.beans.Owner;
import fr.univaix.iut.pokebattle.beans.Pokemon;


public class DAOCombat {

	private EntityManager entityManager;
	
	public DAOCombat(EntityManager em) {
		this.entityManager = em;
	}
	
	
	public Combat getByPokemon ( Pokemon pokemon){
		try 
		{

			TypedQuery<Combat> query = entityManager.createNamedQuery(Combat.GET_BY_NOM , Combat.class);
			query.setParameter("nom", pokemon );
			return query.getSingleResult();
			
		}

		catch (java.util.NoSuchElementException Exc)
		{
			return null;
		}
	}
	
	public Combat getByOwner ( String owner){
		try 
		{

			TypedQuery<Combat> query = entityManager.createNamedQuery(Combat.GET_BY_OWNER , Combat.class);
			query.setParameter("nom", owner );
			return query.getSingleResult();
			
		}

		catch (java.util.NoSuchElementException Exc)
		{
			return null;
		}
	}
	
	public Combat getByJuge (String nomJuge)
	{
		
		TypedQuery<Combat> query = entityManager.createNamedQuery(Combat.GET_BY_JUDGE , Combat.class);
		query.setParameter("nom", nomJuge );
		return query.getSingleResult();
	}
	
	public Combat insert (Combat combat)
	{
		EntityTransaction entT = entityManager.getTransaction();
		entT.begin();
		entityManager.persist(combat);
		entT.commit();
		return combat ;
	}
	
	public boolean delete (Combat combat)
	{
		try {
			EntityTransaction entT = entityManager.getTransaction();
			entT.begin();
			entityManager.remove(combat);
			entT.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	
	public boolean update (Combat combat)
	{
		try {
			EntityTransaction entT = entityManager.getTransaction();
			entT.begin();
			entityManager.merge(combat);
			entT.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	
	
}