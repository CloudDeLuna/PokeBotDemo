package fr.univaix.iut.pokebattle.beans;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.Arrays;

import com.google.gson.Gson;

public final class Pokedex {
	
	private static Pokedex pok = null;
	
    private Gson gson;
    private BufferedReader br;
    private DataObjectPokemon[] data;

	private Pokedex() {
		gson = new Gson();
		br = new BufferedReader(new InputStreamReader(
				Pokedex.class.getClassLoader().getResourceAsStream("pokedex.json")));
		data = gson.fromJson(br, DataObjectPokemon[].class);
	}
	
	public static Pokedex getInstance()
	{
		if (null != pok)
		{
			return pok;
		}
		pok = new Pokedex();
		return pok;
	}
	
	public DataObjectPokemon getPokemon (String pok)
	{		
        for (DataObjectPokemon  pokemon: data)
        {
        	if (pokemon.getNom().equals(pok))
        	{
        		return pokemon;
        	}
        }
		return null;
	}

	public DataObjectPokemon[] getData ()
	{		
		return data;
	}
	
	@Override
	public int hashCode() {
		  assert false : "hashCode not designed";
		  return 42;
	}

	@Override
	public String toString() {
		return "Pokedex [data=" + Arrays.toString(data) + "]";
	}
}
