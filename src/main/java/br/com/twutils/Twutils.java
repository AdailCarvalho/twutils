package br.com.twutils;

import org.apache.log4j.Logger;

import br.com.twutils.cli.Cli;
import br.com.twutils.config.SysConfig;
import br.com.twutils.runner.TwutilsRunner;
import twitter4j.TwitterException;

/**
 * 
 * @author Adail Carvalho
 * 
 * @version 0.1.0
 * 
 * @since 27-08-2016
 */
public class Twutils {
	
	private static final Logger LOGGER = Logger.getLogger(Twutils.class);
	
	Cli cmdLine;
	
	SysConfig sysConfig;
	
	TwutilsRunner runner;
	
	public Twutils() throws TwitterException {
		sysConfig = new SysConfig();
		runner = new TwutilsRunner();
		cmdLine = new Cli();
	}
	
	public int run(String[] cmdArgs) throws TwitterException {
		cmdLine.buildOptions(cmdArgs);
		String outputDefault = "";
		
		if (cmdLine.hasOption(TwutilsOptions.TWEETS_FROM_STREAM)) {
			String[] keywords = cmdLine.getOptionValues(TwutilsOptions.TWEETS_FROM_STREAM);
			String[] outputDir = cmdLine.getOptionValues(TwutilsOptions.OUTPUT_PATH);
			
			if (keywords.length == 0) {
				
			}

			outputDefault = sysConfig.getTwutilsPath()
					.concat(outputDir[0]);

			this.runner.searchTweetsFromStream(keywords, outputDefault);
		} else if (cmdLine.hasOption(TwutilsOptions.TWEETS_BY_LOCATION)) {
			String[] keywords = cmdLine.getOptionValues(TwutilsOptions.TWEETS_FROM_STREAM);
			String[] coordinates = cmdLine.getOptionValues(TwutilsOptions.TWEETS_BY_LOCATION);
			String[] unit = cmdLine.getOptionValues(TwutilsOptions.UNIT);
			String[] outputDir = cmdLine.getOptionValues(TwutilsOptions.OUTPUT_PATH);
			
			if (coordinates.length == 0 || keywords.length == 0) {
				LOGGER.warn("Search by geolocation was selected but no coordinates were given. ");
				LOGGER.warn("Check out the correct usage. ");
				cmdLine.help();
				return -1;
			}
			
			outputDefault = sysConfig.getTwutilsPath()
					.concat(outputDir[0]);
			
			this.runner.searchGeoTweetsFromStream(keywords, coordinates, unit, outputDefault);
		} else if (cmdLine.hasOption(TwutilsOptions.HELP)) {
				 cmdLine.help();
		} else {
			LOGGER.info("The entered option does not exists. ");
			cmdLine.help();
			return -1;
		}	
		
		return 0;
	}
	
	public static void main(String[] args) throws TwitterException {
		int execStat = new Twutils()
				.run(args);
		System.exit(execStat);
	}
}