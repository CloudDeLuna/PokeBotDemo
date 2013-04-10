package fr.univaix.iut.pokebattle.DAO;


import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.univaix.iut.pokebattle.beans.Owner;
import fr.univaix.iut.pokebattle.beans.Pokemon;


public class DAOOwner {

	private EntityManager entityManager;
	
	public DAOOwner(EntityManager em) {
		this.entityManager = em;
	}
	
	public List<Owner> findByPrenom ( String prenom ){
		try{	
			
			TypedQuery<Owner> query = entityManager.createNamedQuery(Owner.FIND_BY_PRENOM , Owner.class);
			query.setParameter("prenom", prenom);
			return query.getResultList();
			
		}
		
		catch(java.util.NoSuchElementException ex)
		{
			return null;
		}
	}
	
	public Owner getByPokemon ( Pokemon pokemon ){
		try 
		{

			TypedQuery<Owner> query = entityManager.createNamedQuery(Owner.FIND_BY_POKEMON , Owner.class);
			query.setParameter("pokemon", pokemon);
			List<Owner> lOwn = query.getResultList();
			
			Iterator<Owner> iter = lOwn.iterator();
			
			Owner own = iter.next();
			
			return own;
		}

		catch (java.util.NoSuchElementException exc)
		{
			return null;
		}
	}
	
	public List<Owner> findAll ( ){
		try{	
			
			TypedQuery<Owner> query = entityManager.createNamedQuery(Owner.FIND_ALL , Owner.class);
			return query.getResultList();
			
		}
		
		catch(java.util.NoSuchElementException ex)
		{
			return null;
		}
	}
	
	public void persist ( Owner own )
	{
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(own);
		this.entityManager.getTransaction().commit();
	}
	
	  public boolean delete(Owner own) {
	        try {
	        	this.entityManager.getTransaction().begin();
	            entityManager.remove(own);
	            this.entityManager.getTransaction().commit();
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	    }
	
	
}
