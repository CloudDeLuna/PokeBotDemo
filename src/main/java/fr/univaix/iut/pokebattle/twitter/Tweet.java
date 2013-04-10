package fr.univaix.iut.pokebattle.twitter;

public class Tweet {
    private String text;
    private String screenName;
    private String dest;

	public Tweet(String text) {
        this.text = text;
    }
	
    public Tweet(String screenName, String text) {
        this("", screenName, text);
    }
	
    public Tweet(String dest, String screenName, String text) {
    	this.dest = dest;
        this.screenName = screenName;
        this.text = text;
    }


    public String getScreenName() {
        return screenName;
    }

    public String getText() {
        return text;
    }
    
    public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}
}
