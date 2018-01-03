package br.com.twutils;

import org.apache.log4j.Logger;

import br.com.twutils.cli.Cli;
import br.com.twutils.config.SysConfig;
import br.com.twutils.exception.TwutilsException;
import br.com.twutils.runner.TwutilsRunner;
import twitter4j.Query.Unit;

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
	
	public Twutils() {
		sysConfig = new SysConfig();
		runner = new TwutilsRunner();
		cmdLine = new Cli();
	}
	
	public int validateAndrun(String[] cmdArgs) throws TwutilsException {
		boolean isGeolocated = false;
		
		this.cmdLine.buildOptions(cmdArgs);
		
		String outputDefault = sysConfig.getTwutilsPath();
		String[] keywords = new String[3];
		String[] coordinates = new String[1];
		String[] coordinatesDel = new String[3];
		String[] unit = new String[1];
		String[] outputDir = new String[1];
		String distUnit = Unit.km.name();
		
		try {
			if (cmdLine.hasOption(TwutilsOptions.TWEETS_FROM_STREAM)) {
				keywords = cmdLine.getOptionValues(TwutilsOptions.TWEETS_FROM_STREAM);
				
				if (cmdLine.hasOption(TwutilsOptions.OUTPUT_PATH)) {
					outputDir = cmdLine.getOptionValues(TwutilsOptions.OUTPUT_PATH);
					outputDefault = outputDefault
							.concat(outputDir[0] + sysConfig.getSoSlash());
				}
				
				if (cmdLine.hasOption(TwutilsOptions.TWEETS_BY_LOCATION)) {
					coordinates = cmdLine.getOptionValues(TwutilsOptions.TWEETS_BY_LOCATION);
					coordinatesDel = coordinates[0].split(",");
					outputDir = cmdLine.getOptionValues(TwutilsOptions.OUTPUT_PATH);
					unit = cmdLine.getOptionValues(TwutilsOptions.UNIT);
					
					if (unit.length != 0) {
						distUnit = unit[0];
					} else {
						LOGGER.warn("No distance unit was informed. Using default measure - KM");
					}
					
					isGeolocated = true;
				}			
				
				if (keywords.length == 0) {
					LOGGER.warn("At least one keyword must be informed. ");
					cmdLine.help();
					return -1;
				}
				
				if (isGeolocated) {
					this.runner.searchGeoTweetsFromStream(keywords, coordinatesDel, distUnit, outputDefault);
	
				} else {
					this.runner.searchTweetsFromStream(keywords, outputDefault);
				}
				
			} else if (cmdLine.hasOption(TwutilsOptions.HELP)) {
					 cmdLine.help();
			} else {
				LOGGER.info("The entered option does not exists. ");
				cmdLine.help();
				return -1;
			}
		} catch (TwutilsException e1) {
			
		}
		
		return 0;
	}
	
	public static void main(String[] args) throws TwutilsException {
		int execStat = new Twutils()
				.validateAndrun(args);
		System.exit(execStat);
	}
}