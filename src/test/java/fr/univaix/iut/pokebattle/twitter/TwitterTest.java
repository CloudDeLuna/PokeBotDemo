package fr.univaix.iut.pokebattle.twitter;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.twitter.hbc.httpclient.ControlStreamException;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.tuse.Credentials;

public class TwitterTest {
    @Test
    public void testCreateTweet() throws InterruptedException, ControlStreamException, IOException {
	    InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("twitter4j-Smogogo.properties");
	    Credentials credentials = Credentials.loadCredentials(inputStream);
	    TwitterBot twitterBot = new TwitterBot(new PokeBot(), credentials);
	    twitterBot.startBot();
    }

}
