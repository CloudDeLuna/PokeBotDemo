package fr.univaix.iut.pokebattle;

import fr.univaix.iut.pokebattle.twitter.Tweet;
import twitter4j.TwitterException;

public interface Bot {
    String ask(Tweet question) throws IllegalStateException, TwitterException;
}
