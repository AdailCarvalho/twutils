package br.com.twutils.twitter;

import java.util.List;

import br.com.twutils.exception.TwutilsException;
import br.com.twutils.vo.TweetVO;

import twitter4j.Query;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 * 
 * @author Adail Carvalho
 * 
 * @version 0.1.0
 * 
 * @since 27-08-2016
 */
public interface TwitterConsumer {
	
	/**
	 * Authenticates the application to use Twitter Search API.
	 * @return Authorized twitter access instance. 
	 */
	public Twitter authApplicationAccess();
	
	/**
	 * @param output The path where the retrieve objects will be written.
	 * @param latitude
	 * @param longitude
	 * @param radius
	 * @param unit
	 * @param keywords
	 * @return A TweetVO Object list with tweets and geolocalization info, containing the given keywords.
	 * @throws TwitterException
	 */
	public List<TweetVO> searchGeoTweetsFromStream(String output, double latitude, double longitude, double radius,
			String unit, String... keywords) throws TwutilsException;
	
	/**
	 * 
	 * @param query
	 * @param sinceId
	 * @return A TweetVO Object list with tweets containing the given keywords.
	 * @throws TwitterException
	 */
	public List<TweetVO> searchTweetsFromStream(Query query, Long sinceId, List<TweetVO> tweetsListVO) 
			throws TwutilsException;
	
	/**
	 * @param output The path where the retrieve objects will be written.
	 * @param keywords
	 * @return A TweetVO Object list with tweets containing the given keywords.
	 * @throws TwitterException
	 */
	public List<TweetVO> searchTweetsFromStream(String output, String... keywords) throws TwutilsException;
}
