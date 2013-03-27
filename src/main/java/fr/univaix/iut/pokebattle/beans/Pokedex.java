package fr.univaix.iut.pokebattle.beans;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.google.gson.Gson;

public class Pokedex {
	
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
		if (pok != null)
			return pok;
		return pok = new Pokedex();
	}
	
	public DataObjectPokemon getPokemon (String pok)
	{		
        for (DataObjectPokemon  pokemon: data)
        	if (pokemon.getNom().equals(pok))
        		return pokemon;
        
		return null;
	}

}
