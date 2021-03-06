package fr.univaix.iut.pokebattle.smartcell.PokeCell;

import twitter4j.TwitterException;
import fr.univaix.iut.pokebattle.DAO.DAOFactory;
import fr.univaix.iut.pokebattle.DAO.DAOOwner;
import fr.univaix.iut.pokebattle.DAO.DAOPokemon;
import fr.univaix.iut.pokebattle.beans.DataObjectAttack;
import fr.univaix.iut.pokebattle.beans.DataObjectPokemon;
import fr.univaix.iut.pokebattle.beans.Owner;
import fr.univaix.iut.pokebattle.beans.Pokedex;
import fr.univaix.iut.pokebattle.beans.Pokemon;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonAttackCell implements SmartCell 
{

		public String ask(Tweet question) throws TwitterException 
		{	
			if ( question.getText().contains("#attack")) 
			{
				DAOOwner daoOwn = DAOFactory.createDAOOwner();
				DAOPokemon daoPoke = DAOFactory.createDAOPokemon();
				
				String[] phrase = question.getText().split(" ");
				Pokemon poke = daoPoke.getByNom(phrase[0]);
				Owner owner = daoOwn.getByPokemon(poke);
									
	
				if ( owner.getPrenom().equals("@" + question.getScreenName())) 
				{
					final int trois = 3;
					poke = daoPoke.getByNom(phrase[trois]);
					
					Pokedex dex = Pokedex.getInstance();
					
					DataObjectPokemon goupix = dex.getPokemon("Goupix");
					
					String [] attack = phrase[2].split("#");
					
					for (DataObjectAttack i : goupix.getAttaques())
					{
						if (i.getNom().contains(attack[1]))
						{
							final int dix = 10;
							int pVPoke = poke.getPV();
							poke.setPV(pVPoke-dix);
							daoPoke.persist(poke);
							
							final int deux = 2;
							final int cinq = 5;
							final int six = 6;
							return phrase[trois] + " #attack " + phrase[deux] + " /cc " + phrase[cinq] + " " + owner.getPrenom() + " " + phrase[six]; 
						}
						else
						{
							return owner.getPrenom() + " O_o ?";
					
						}
					}
				}
				else
				{
					return "@" + question.getScreenName() + " " + owner.getPrenom() + " is my owner";
				}

			}//if contains attack
			return null;

		}//ask ()

}
