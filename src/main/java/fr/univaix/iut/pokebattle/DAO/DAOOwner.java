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
	
	public List<Owner> findByPrenom ( String Prenom ){
		try{	
			
			TypedQuery<Owner> query = entityManager.createNamedQuery(Owner.FIND_BY_PRENOM , Owner.class);
			query.setParameter("prenom", Prenom);
			return query.getResultList();
			
		}
		
		catch(java.util.NoSuchElementException Ex)
		{
			return null;
		}
	}
	
	public Owner getByPokemon ( Pokemon Pokemon ){
		try 
		{

			TypedQuery<Owner> query = entityManager.createNamedQuery(Owner.FIND_BY_POKEMON , Owner.class);
			query.setParameter("pokemon", Pokemon);
			List<Owner> LOwn = query.getResultList();
			
			Iterator<Owner> iter = LOwn.iterator();
			
			Owner Own = iter.next();
			
			return Own;
		}

		catch (java.util.NoSuchElementException Exc)
		{
			return null;
		}
	}

	public int computeNbPoke( String Prenom ){
		try{
			
			TypedQuery<Owner> query = entityManager.createNamedQuery(Owner.COUNT_POKE , Owner.class);
			query.setParameter("prenom", Prenom);
			return query.getFirstResult();
		}
		catch(java.util.NoSuchElementException Ex)
		{
			return 0;
		}
	}
	
	public List<Owner> findAll ( ){
		try{	
			
			TypedQuery<Owner> query = entityManager.createNamedQuery(Owner.FIND_ALL , Owner.class);
			return query.getResultList();
			
		}
		
		catch(java.util.NoSuchElementException Ex)
		{
			return null;
		}
	}
	
	
}
