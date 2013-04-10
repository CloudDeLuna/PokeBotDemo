 package fr.univaix.iut.pokebattle.DAO;

import java.util.List;

import javax.persistence.EntityManager;
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
	
	public int countByRace ( String race )
	{
		
		try{	
			
			TypedQuery<Pokemon> query = entityManager.createNamedQuery(Pokemon.COUNT_POKE_PER_RACE , Pokemon.class);
			query.setParameter("race", race);
			return query.getFirstResult();
			
		}
		
		catch(Exception ex)
		{
			return 0;
		}
		
	}
	
	public void persist ( Pokemon poke )
	{
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(poke);
		this.entityManager.getTransaction().commit();
	}
	
  public boolean delete(Pokemon poke) {
        try {
        	this.entityManager.getTransaction().begin();
            entityManager.remove(poke);
            this.entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
	
}