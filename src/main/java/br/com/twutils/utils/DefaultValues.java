package br.com.twutils.utils;

/**
 * 
 * @author Adail Carvalho
 * 
 * @version 0.1.0
 * 
 * @since 27-08-2016
 */
public class DefaultValues {
	
	/**
	* The number of times the application will request TwitterSearchAPI for tweet data.
    */
	public static final int DEFAULT_COUNT = 1000000;
	
	public static final String DEFAULT_CSV_SEPARATOR = ";";

	public static final String QUOTES_ESCAPE = "\\";
	
	public static final String EMPTY_STRING = "";
	
	public static final String BREAKLINE = "\n";
	
	public static final String WINDOWS_SLASH = "\\";
	
	public static final String UNIX_SLASH = "/";
		
	/**
	* The interval (in seconds) between each request.
	*/
	public static final int DEFAULT_THREAD_WAIT_TIME = 10000;
	
	/**
	 * The fields extracted from tweet raw data.
	 */
	public static final String[] DEFAULT_TWEET_HEADER = {"id", "tweet", "keywords", "userId", "username","userScreenName", "userLocation",
			 "userFollowersCount", "countryCode", "countryName" , "placeFullName", "streetAddressName", "latitude",
			 "longitude", "createdAt", "retweetsCount", "favouriteCount"};

}
