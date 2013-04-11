 package fr.univaix.iut.pokebattle.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.univaix.iut.pokebattle.beans.Pokemon;


public class DAOPokemon {

	
	private EntityManager entityManager;
	
	public DAOPokemon(EntityManager em) {
		this.entityManager = em;
	}
	
	public Pokemon getByNom ( String nom )
	{
		
		try{	
			
			TypedQuery<Pokemon> query = entityManager.createNamedQuery(Pokemon.FIND_BY_NOM , Pokemon.class);
			query.setParameter("nom", nom);
			return query.getSingleResult();
		}
		
		catch(Exception ex)
		{
			return null;
		}
		
	}
	
	public List<Pokemon> findByRace ( String race )
	{
		
		try{	
			
			TypedQuery<Pokemon> query = entityManager.createNamedQuery(Pokemon.FIND_BY_RACE , Pokemon.class);
			query.setParameter("race", race);
			return query.getResultList();
			
		}
		
		catch(Exception ex)
		{
			return null;
		}
		
	}
	
	public List<Pokemon> findAll ()
	{
		
		try{	
			
			TypedQuery<Pokemon> query = entityManager.createNamedQuery(Pokemon.FIND_ALL , Pokemon.class);
			return query.getResultList();
			
		}
		
		catch(java.util.NoSuchElementException ex)
		{
			return null;
		}
		
	}
	
	public void persist ( Pokemon poke )
	{
    	EntityTransaction e = entityManager.getTransaction();
    	e.begin();
		entityManager.persist(poke);
		e.commit();
	}
	
  public boolean delete(Pokemon poke) {
        try {
        	EntityTransaction e = entityManager.getTransaction();
        	e.begin();
            entityManager.remove(poke);
            e.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
	
}