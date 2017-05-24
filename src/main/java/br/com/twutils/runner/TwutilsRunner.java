package br.com.twutils.runner;

import br.com.twutils.twitter.TwitterConsumer;
import br.com.twutils.twitter.impl.TwitterConsumerImpl;
import twitter4j.TwitterException;

/**
 * 
 * @author Adail Carvalho
 *
 * @version 0.1.0
 * 
 * @since @since 21-05-2017
 */
public class TwutilsRunner {
	
	private TwitterConsumer twitterConsumer;
	
	public TwutilsRunner() {
		twitterConsumer = new TwitterConsumerImpl();
	}
	
	public void searchTweetsFromStream(String output, String[] keywords) throws TwitterException {
		twitterConsumer.searchTweetsFromStream(output, keywords);
	}
	
	public void searchGeoTweetsFromStream() {
		
	}
}
