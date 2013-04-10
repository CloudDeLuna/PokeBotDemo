package fr.univaix.iut.pokebattle.bot;

import twitter4j.TwitterException;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.smartcell.PokeCell.PokemonAttackCell;
import fr.univaix.iut.pokebattle.smartcell.PokeCell.PokemonCaptureCell;
import fr.univaix.iut.pokebattle.smartcell.PokeCell.PokemonCriesCell;
import fr.univaix.iut.pokebattle.smartcell.PokeCell.PokemonFeatureAttackCell;
import fr.univaix.iut.pokebattle.smartcell.PokeCell.PokemonFeatureCell;
import fr.univaix.iut.pokebattle.smartcell.PokeCell.PokemonKOCell;
import fr.univaix.iut.pokebattle.smartcell.PokeCell.PokemonOwnerCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public final class PokeBot implements Bot {
    /**
     * List of smartcell the questions go through to
     * find an answer.
     */

	private final SmartCell[] smartCells = new SmartCell[]{
	    		new PokemonFeatureAttackCell(),
	    		new PokemonAttackCell(),
	    		new PokemonCaptureCell(),
	            new PokemonOwnerCell(),
	            new PokemonFeatureCell(),
	            new PokemonKOCell(),
	            new PokemonCriesCell(),
	    };


    public SmartCell[] getSmartCells() {
		return smartCells;
	}
    /**
     * Ask something to Bot, it will respond to you.
     *
     * @param question The question you ask.
     * @return An answer... or null if it doesn't get it.
     * @throws TwitterException 
     * @throws IllegalStateException 
     */
    @Override
    public String ask(Tweet question) throws TwitterException {
        for (SmartCell cell : smartCells) {
            String answer = cell.ask(question);
            if (answer != null)
            {
                return answer;
            }
        }
        return null;
    }

}
