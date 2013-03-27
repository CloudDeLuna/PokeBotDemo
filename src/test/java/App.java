import org.junit.Test;

import fr.univaix.iut.pokebattle.beans.DataObjectPokemon;
import fr.univaix.iut.pokebattle.beans.Pokedex;

public class App 
{
	@Test
	public void test() {
		
		Pokedex dex = Pokedex.getInstance();
		
		DataObjectPokemon Goupix = dex.getPokemon("Goupix");
		
		System.out.println(Goupix.getAttaques());
	}

}