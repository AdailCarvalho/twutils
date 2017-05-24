package br.com.twutils.twitter.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.univocity.parsers.csv.CsvWriter;

import br.com.twutils.csv.CsvManager;
import br.com.twutils.csv.impl.CsvManagerImpl;
import br.com.twutils.twitter.TwitterConsumer;
import br.com.twutils.utils.DefaultValues;
import br.com.twutils.utils.PropertiesHandler;
import br.com.twutils.vo.TweetVO;
import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.Query.Unit;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 * 
 * @author Adail Carvalho
 * 
 * @version 0.1.0
 * 
 * @since 27-08-2016
 */
public class TwitterConsumerImpl implements TwitterConsumer {
	
	private static final Logger LOG = Logger.getLogger(TwitterConsumerImpl.class);
	
	private static Twitter twitter;
	private String accessToken;
	private String accessSecret;
	private String consumerKey;
	private String consumerSecret;
		
	private String outputFilePath;
	
	CsvManager csvManager;
	PropertiesHandler properties;
	
	public TwitterConsumerImpl() {
		properties = new PropertiesHandler();
		csvManager = new CsvManagerImpl();
		loadTokens();
		twitter = authApplicationAccess();
	}
	
	public TwitterConsumerImpl(String accessToken, String accessSecret, String consumerKey, 
			String consumerSecret) {
		this.accessToken = accessToken;
		this.accessSecret = accessSecret;
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		authApplicationAccess();
	}
	
	public Twitter authApplicationAccess() {
		Twitter twitter = null;
		LOG.info("Authorizing application to consume API...");
		try {
			AccessToken accessTk;
			twitter = TwitterFactory.getSingleton();
			accessTk = new AccessToken(accessToken, accessSecret);
			twitter.setOAuthConsumer(consumerKey, consumerSecret);
			twitter.setOAuthAccessToken(accessTk);
			
		} catch (Exception e1) {
			LOG.info("Unable to authenticate application. ");
			throw new RuntimeException(e1.getMessage());
		}
		
		LOG.info("Authorized. ");
		return twitter;
	}
	
	public void loadTokens() {
		Map<String, String> twitterProperties = properties.getTwitterProperties(); 
		this.accessToken = twitterProperties.get("accessToken");
		this.accessSecret = twitterProperties.get("accessSecret");
		this.consumerKey = twitterProperties.get("consumerKey");
		this.consumerSecret = twitterProperties.get("consumerSecret");
	}
	
	public List<TweetVO> searchGeoTweetsFromStream(double latitude, double longitude, double radius,
			String unit, String... keywords) throws TwitterException {
		StringBuilder rawQuery = new StringBuilder();
		List<TweetVO> tweetsListVO = new ArrayList<TweetVO>();

		for (int i = 0; i < keywords.length; i++) {
			rawQuery.append("(" + keywords[i] + ")");
			if (i < keywords.length - 1) {
				rawQuery.append(" OR ");
			}
		}

		Query query = new Query(rawQuery.toString());
		query.setCount(DefaultValues.DEFAULT_COUNT);
		GeoLocation location = new GeoLocation(latitude, longitude);

		if (unit != "mi") {
			query.setGeoCode(location, radius, Unit.km);
		} else {
			query.setGeoCode(location, radius, Unit.mi);
		}

		return searchTweetsFromStream(query, 1L, tweetsListVO);
	}
	
	public List<TweetVO> searchTweetsFromStream(Query query, Long sinceId, List<TweetVO> tweetsListVO) throws TwitterException {
		QueryResult result;
		List<TweetVO> listWithTweets = tweetsListVO;
		
		CsvWriter tweetsWriter = csvManager.getCsvWriter(this.outputFilePath);
		
		Long sinId = sinceId;
		Query qry = query;
		qry.setCount(DefaultValues.DEFAULT_COUNT);
		qry.setSinceId(sinId);
		
		int requestNumber = 1;
		int tweetsCount = 0;
		
		
		while (requestNumber <= DefaultValues.DEFAULT_COUNT) {
			try {
				result = twitter.search(qry);
				sinId = result.getMaxId(); 
				
				for (Status status : result.getTweets()) {
					TweetVO twitterVO = new TweetVO();
					Double lat = status.getGeoLocation() == null ? 0.0 : status.getGeoLocation().getLatitude();
					Double lon = status.getGeoLocation() == null ? 0.0 : status.getGeoLocation().getLongitude();
					twitterVO.setTweetId(status.getId());
					twitterVO.setTweetText(status.getText().replaceAll(DefaultValues.BREAKLINE, DefaultValues.EMPTY_STRING));
					twitterVO.setUserScreenName(status.getUser().getName());
					twitterVO.setRetweetCount(status.getRetweetCount());
					twitterVO.setFavouriteCount(status.getFavoriteCount());
					twitterVO.setLatitude(lat);
					twitterVO.setLongitude(lon);
					twitterVO.setCreatedAt(status.getCreatedAt());
					
					tweetsWriter.writeRow(twitterVO.getTweetId(), twitterVO.getTweetText(), twitterVO.getUserScreenName(), 
							twitterVO.getLatitude(), twitterVO.getLongitude(), twitterVO.getCreatedAt(), twitterVO.getRetweetCount(), twitterVO.getFavouriteCount());
					listWithTweets.add(twitterVO);
				}
				
				tweetsCount += result.getTweets().size();
				
				LOG.info("Twitter requests count: " + requestNumber);
				LOG.info("Tweets retrieved: " + tweetsCount);
				tweetsWriter.flush();
				
				Thread.sleep(DefaultValues.DEFAULT_THREAD_WAIT_TIME);
				qry.setSinceId(sinId);
				result = null;
			} catch (TwitterException e1) {
				LOG.info("Could not get tweets from stream.");
				throw new RuntimeException(e1);
			} catch (InterruptedException e2) {
				throw new RuntimeException(e2);
			}
			
			requestNumber += 1;
		}
		
		tweetsWriter.close();
		return listWithTweets;
	}
	
	public List<TweetVO> searchTweetsFromStream(String outputFilePath, String... keywords) throws TwitterException {
		StringBuilder rawQuery = new StringBuilder();
		List<TweetVO> tweetsListVO = new ArrayList<TweetVO>();
		
		this.setOutputFilePath(outputFilePath);
		
		for (int i = 0; i < keywords.length; i++) {
			rawQuery.append("(" + keywords[i] + ")");
			if (i < keywords.length - 1) {
				rawQuery.append(" OR ");
			}
		}
		Query query = new Query(rawQuery.toString());
		
		LOG.info("Retrieve tweets contaiting the following words: " + rawQuery.toString());
		return searchTweetsFromStream(query, 1L, tweetsListVO);
	}
	
	public void setOutputFilePath(String outputFilePath) {
		this.outputFilePath = outputFilePath;
	}
}