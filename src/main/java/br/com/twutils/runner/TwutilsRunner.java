package br.com.twutils.runner;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import br.com.twutils.exception.TwutilsException;
import br.com.twutils.twitter.TwitterConsumer;
import br.com.twutils.twitter.impl.TwitterConsumerImpl;
import twitter4j.Query.Unit;

/**
 * 
 * @author Adail Carvalho
 *
 * @version 0.1.0
 * 
 * @since 21-05-2017
 */
public class TwutilsRunner {
	
	private static final Logger LOGGER = Logger.getLogger(TwutilsRunner.class);
	
	private TwitterConsumer twitterConsumer;
		
	public TwutilsRunner() {
		twitterConsumer = new TwitterConsumerImpl();
	}
		
	public void searchTweetsFromStream(String[] keywords, String output) throws TwutilsException {
		LOGGER.info("Starting tweets search");
		
		twitterConsumer.searchTweetsFromStream(output, keywords[0].split(","));
	}
	
	public void searchGeoTweetsFromStream(String[] keywords, 
			String[] coordinates, String unit, String output) throws TwutilsException {
		double latitude = 0L;
		double longitude = 0L;
		double radius = 0L;
		String distanceUnit = Unit.km.name();
		
		for (String data : coordinates) {
			if (!StringUtils.isNotBlank(data)) {
				throw new TwutilsException("Invalid geolocation coordinates. ");
			}
		}
		
		latitude = Double.parseDouble(coordinates[0].trim());
		longitude = Double.parseDouble(coordinates[1].trim());
		radius = Double.parseDouble(coordinates[2].trim());
		
		LOGGER.info("Starting tweets search by geolocation");
		LOGGER.info("Latitude = {" + latitude + "}, Longitude = {" + longitude + "}, Radius = {" +  radius +""+ distanceUnit +"}");
		
		twitterConsumer.searchGeoTweetsFromStream(output, latitude, longitude, radius, distanceUnit, keywords[0].split(","));
	}
}