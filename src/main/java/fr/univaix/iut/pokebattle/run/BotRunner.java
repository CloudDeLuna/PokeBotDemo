package fr.univaix.iut.pokebattle.run;

import com.twitter.hbc.httpclient.ControlStreamException;
import fr.univaix.iut.pokebattle.bot.Bot;
import fr.univaix.iut.pokebattle.tuse.Credentials;
import fr.univaix.iut.pokebattle.twitter.TwitterBot;

import java.io.IOException;
import java.io.InputStream;

public final class BotRunner {
	
    private BotRunner() {
		super();
	}

	public static void runBot(Bot bot, String credentialsFileName) {
        try {
            InputStream inputStream = getResourceAsStream(credentialsFileName);
            Credentials credentials = Credentials.loadCredentials(inputStream);
            TwitterBot twitterBot = new TwitterBot(bot, credentials);
            twitterBot.startBot();
        } catch (InterruptedException e) {
        	e.getMessage();
        } catch (ControlStreamException e) {
        	e.getMessage();        } 
        catch (IOException e) {
        	e.getMessage();
        }
    }

    static InputStream getResourceAsStream(String fileName) {
        return PokemonMain.class.getClassLoader().getResourceAsStream(fileName);
    }
}