package fr.univaix.iut.pokebattle.bot;

import twitter4j.TwitterException;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.smartcell.NurseCell.NursePVCell;
import fr.univaix.iut.pokebattle.smartcell.NurseCell.NursePokeCenterCell;
import fr.univaix.iut.pokebattle.smartcell.NurseCell.NurseWakeUpPokeCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public final class NurseBot implements Bot {
   
	 private final SmartCell[] smartCells = new SmartCell[]{
			 new NursePVCell(),
			 new NursePokeCenterCell(),
			 new NurseWakeUpPokeCell(),
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
            String answer; 
            answer = cell.ask(question);
            if (answer != null)
            {
                return answer+ " #pokebattle";
            }
        }
        return null;
    }

}