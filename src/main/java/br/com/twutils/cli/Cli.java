package br.com.twutils.cli;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;

import br.com.twutils.TwutilsOptions;
import br.com.twutils.exception.TwutilsException;

public class Cli {
	
	private static final Logger logger = Logger.getLogger(Cli.class);
	
	private CommandLine cmdLine;
	
	private CommandLineParser cmdParser;
	
	private Options options;
		
	public Cli() {
		cmdParser = new BasicParser();
		options = new Options();
	}
	
	@SuppressWarnings("static-access")
	public void buildOptions(String[] cmdArgs) throws TwutilsException {
		OptionGroup searchTweetsOptGrp = new OptionGroup();
		
		Option strTweetsOpt = OptionBuilder.withArgName("tt")
				.withLongOpt(TwutilsOptions.TWEETS_FROM_STREAM)
				.withDescription("Collect tweets that contains the given keywords. ")
				.hasArg(true)
				.isRequired(true)
				.create();
		
		Option geoTweetsOpt = OptionBuilder.withArgName("geo")
				.withLongOpt(TwutilsOptions.TWEETS_BY_LOCATION)
				.withDescription("Collect tweets that contains the given keywords, around a specific geolocation.")
				.hasArg(true)
				.isRequired(false)
				.create();
		
		Option unitOpt = OptionBuilder.withArgName("unit")
				.withLongOpt(TwutilsOptions.UNIT)
				.withDescription("Measure unit to be considered in geolocation search mode. ")
				.hasArg(true)
				.isRequired(false)
				.create();
		
		searchTweetsOptGrp.addOption(strTweetsOpt)
						  .addOption(geoTweetsOpt)
						  .addOption(unitOpt);
		
		Option outputOpt = OptionBuilder.withArgName("out")
				.withLongOpt(TwutilsOptions.OUTPUT_PATH)
				.withDescription("Output tweets data filename. ")
				.hasArg(true)
				.isRequired(true)
				.create();
		
		Option helpOpt = OptionBuilder.withArgName("h")
				.withLongOpt(TwutilsOptions.HELP)
				.withDescription("Show help. ")
				.hasArg(false)
				.create();
		
		options.addOptionGroup(searchTweetsOptGrp);
		options.addOption(outputOpt);
		options.addOption(helpOpt);

		try {
			if (cmdArgs.length == 0) {
				logger.warn("Missing required args: ");
				help();
				throw new TwutilsException("Missing required args. ");
			} else {
				cmdLine = cmdParser.parse(options, cmdArgs);
			}
		} catch (ParseException pe) {
			logger.error("Problem during options parsing.");
			help();
			throw new RuntimeException(pe);
		} catch (TwutilsException t1) {
			logger.error("Problem during options parsing.");
			throw new RuntimeException(t1);
		}
	}
	
	public boolean hasOption(String option) {
		return cmdLine.hasOption(option);
	}
	
	public String[] getOptionValues(String option) {
		return cmdLine.getOptionValues(option);
	}
	
	public void help() {
		HelpFormatter formater = new HelpFormatter();
		formater.printHelp("Twutils", options);
	}
}