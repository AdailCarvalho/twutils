package br.com.twutils.runner;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import br.com.twutils.twitter.TwitterConsumer;
import br.com.twutils.twitter.impl.TwitterConsumerImpl;
import twitter4j.Query.Unit;
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
	
	private static final Logger LOGGER = Logger.getLogger(TwutilsRunner.class);
	
	private TwitterConsumer twitterConsumer;
	
	public TwutilsRunner() {
		twitterConsumer = new TwitterConsumerImpl();
	}
	
	public void searchTweetsFromStream(String[] keywords, String output) throws TwitterException {
		twitterConsumer.searchTweetsFromStream(output, keywords);
	}
	
	public void searchGeoTweetsFromStream(String[] keywords, 
			String[] coordinates, String[] unit, String output) throws TwitterException {
		double latitude = 0L;
		double longitude = 0L;
		double radius = 0L;
		String distanceUnit = Unit.km.name();
		
		for (String data : coordinates) {
			if (!StringUtils.isNotBlank(data)) {
				throw new TwitterException("Invalid geolocation coordinates. ");
			}
		}
		
		if (unit.length != 0) {
			distanceUnit = unit[0];
		} else {
			LOGGER.warn("No distance unit was informed. Using default measure - KM");
		}
		
		
		latitude = Long.valueOf(coordinates[0]);
		longitude = Long.valueOf(coordinates[1]);
		radius = Long.valueOf(coordinates[2]);
		
		twitterConsumer.searchGeoTweetsFromStream(latitude, longitude, radius, distanceUnit, keywords);
	}
}