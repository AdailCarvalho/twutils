package br.com.twutils.twitter;

import java.util.List;

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
	 * 
	 * @param latitude
	 * @param longitude
	 * @param radius
	 * @param unit
	 * @param keywords
	 * @return A json list with tweets and geolocalization info, containing the given keywords.
	 * @throws TwitterException
	 */
	public List<TweetVO> searchGeoTweetsFromStream(double latitude, double longitude, double radius,
			String unit, String... keywords) throws TwitterException;
	
	/**
	 * 
	 * @param query
	 * @param sinceId
	 * @return A json list with tweets containing the given keywords.
	 * @throws TwitterException
	 */
	public List<TweetVO> searchTweetsFromStream(Query query, Long sinceId, List<TweetVO> tweetsListVO) 
			throws TwitterException;
	
	/**
	 * 
	 * @param keywords
	 * @return A json list with tweets containing the given keywords.
	 * @throws TwitterException
	 */
	public List<TweetVO> searchTweetsFromStream(String output, String... keywords) throws TwitterException;
}
