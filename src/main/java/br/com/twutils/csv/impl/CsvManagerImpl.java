package br.com.twutils.csv.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

import br.com.twutils.csv.CsvManager;
import br.com.twutils.utils.DefaultValues;

/**
 * 
 * @author Adail Carvalho
 *
 * @version 1.0.0
 * 
 * @since 20-05-2017
 */

public class CsvManagerImpl implements CsvManager {

	@Override
	public CsvWriter getCsvWriter(String outputFile) {
		CsvWriter csvWriter = null;
		CsvWriterSettings settings = new CsvWriterSettings();
		
		settings.getFormat().setDelimiter(DefaultValues.DEFAULT_CSV_SEPARATOR.charAt(0));
		settings.getFormat().setQuoteEscape(DefaultValues.QUOTES_ESCAPE.charAt(0));
	
		try {
			csvWriter = new CsvWriter(new FileWriter
					(new File(outputFile)), settings);
		} catch (IOException e1) {
			
		}
		
		csvWriter.writeHeaders(DefaultValues.DEFAULT_TWEET_HEADER);
		csvWriter.flush();
		return csvWriter;
	}
}