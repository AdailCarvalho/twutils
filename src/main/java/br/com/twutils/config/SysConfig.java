package br.com.twutils.config;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import br.com.twutils.utils.DefaultValues;

/**
 * 
 * @author Adail Carvalho
 * 
 * @version 1.0.0
 * 
 * @since 2017-05-22
 */
public class SysConfig {
	
	private static final Logger LOG = Logger.getLogger(SysConfig.class);

	static final String SO_SLASH = StringUtils.defaultIfEmpty(java.nio.file.FileSystems.getDefault().getSeparator(), 
			DefaultValues.WINDOWS_SLASH); 
	
	static final String SO = System.getProperty("os.name");
	
	static final String TWUTILS_PATH = "_opt_twutils_"; 
	
	String twutilsPath;
	
	public SysConfig () {
		configureDir();
		logConfigStatus();
	}
	
	public void configureDir () {
		LOG.info("Setting definitions... ");
		
		try {
			if (SO.startsWith("Windows")) {
				String escapeSlash = "\\\\";
				twutilsPath = "C:".concat(TWUTILS_PATH).replaceAll("_", escapeSlash);
				FileUtils.forceMkdir(new File(twutilsPath));
			} else {
				twutilsPath = TWUTILS_PATH.replace("_", SO_SLASH);
				FileUtils.forceMkdir(new File(twutilsPath));
			}
		} catch (IOException e) {
			LOG.error("Unable to create application directory. ");
		}
	}
	
	public void logConfigStatus() {
		LOG.info("SO: " + SO);
		LOG.info("Slash: " + SO_SLASH);
		LOG.info("Application main directory: " + this.twutilsPath);
	}
	
	public String getTwutilsPath() {
		return this.twutilsPath;
	}
}
