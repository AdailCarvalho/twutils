package br.com.twutils.csv;

import com.univocity.parsers.csv.CsvWriter;

import twitter4j.TwitterException;

/**
 * 
 * @author Adail Carvalho
 * 
 * @version 1.0.0
 * 
 * @since 20-05-2017
 */
public interface CsvManager {
	
	/**
	 * 
	 * @param outputPath The path were the file will be outputed.
	 * @return Configured CsvWriter instance,
	 */
	public CsvWriter getCsvWriter(String outputPath) throws TwitterException;

}
