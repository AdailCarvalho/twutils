package br.com.twutils.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * @author Adail Carvalho
 * 
 * @version 0.1.0
 * 
 * @since 27-08-2016
 */
public class PropertiesHandler {
	
	public Map<String, String> getTwitterProperties() {
		Properties properties = new Properties();
		Map<String, String> twitterProperties = new HashMap<String, String>();
		String accessToken;
		String accessSecret;
		String consumerKey;
		String consumerSecret;
		try {

			properties.load(this.getClass().getResourceAsStream(
					"/social_networking.properties"));
			accessToken = properties
					.getProperty("twutils.twitter.access.token");
			accessSecret = properties
					.getProperty("twutils.twitter.access.secret");
			consumerKey = properties
					.getProperty("twutils.twitter.consumer.key");
			consumerSecret = properties
					.getProperty("twutils.twitter.consumer.secret");
			
			twitterProperties.put("accessToken", accessToken);
			twitterProperties.put("accessSecret", accessSecret);
			twitterProperties.put("consumerKey", consumerKey);
			twitterProperties.put("consumerSecret", consumerSecret);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return twitterProperties;
	}
}
