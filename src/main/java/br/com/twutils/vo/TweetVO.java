package br.com.twutils.vo;

import java.util.Date;

import com.univocity.parsers.annotations.Parsed;

/**
 * 
 * @author Adail Carvalho
 * @since 1.0.1
 *
 */
public class TweetVO {

	@Parsed
	private Long tweetId;
	
	@Parsed
	private Long userId;

	@Parsed
	private String tweetText;

	@Parsed
	private String userScreenName;

	@Parsed
	private int retweetCount;

	@Parsed
	private int favouriteCount;

	@Parsed
	private Date createdAt;

	@Parsed
	private Double latitude;

	@Parsed
	private Double longitude;

	public Date getCreatedAt() {
		return createdAt;
	}

	public int getFavouriteCount() {
		return favouriteCount;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public int getRetweetCount() {
		return retweetCount;
	}

	public Long getTweetId() {
		return tweetId;
	}

	public String getTweetText() {
		return tweetText;
	}

	public Long getUserId() {
		return userId;
	}

	public String getUserScreenName() {
		return userScreenName;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setFavouriteCount(int favouriteCount) {
		this.favouriteCount = favouriteCount;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public void setRetweetCount(int retweetCount) {
		this.retweetCount = retweetCount;
	}

	public void setTweetId(Long tweetId) {
		this.tweetId = tweetId;
	}

	public void setTweetText(String tweetText) {
		this.tweetText = tweetText;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUserScreenName(String userScreenName) {
		this.userScreenName = userScreenName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + favouriteCount;
		result = prime * result
				+ ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result
				+ ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + retweetCount;
		result = prime * result + ((tweetId == null) ? 0 : tweetId.hashCode());
		result = prime * result
				+ ((tweetText == null) ? 0 : tweetText.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result
				+ ((userScreenName == null) ? 0 : userScreenName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TweetVO other = (TweetVO) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (favouriteCount != other.favouriteCount)
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (retweetCount != other.retweetCount)
			return false;
		if (tweetId == null) {
			if (other.tweetId != null)
				return false;
		} else if (!tweetId.equals(other.tweetId))
			return false;
		if (tweetText == null) {
			if (other.tweetText != null)
				return false;
		} else if (!tweetText.equals(other.tweetText))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userScreenName == null) {
			if (other.userScreenName != null)
				return false;
		} else if (!userScreenName.equals(other.userScreenName))
			return false;
		return true;
	}
}
