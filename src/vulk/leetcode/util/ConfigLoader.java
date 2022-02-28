package vulk.leetcode.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Load value from config file
 * 
 * @author KJack
 *
 */
public class ConfigLoader {

	/**
	 * Configures for inputFile
	 */
	public static final String INPUT_CONFIG = "input.properties";

	private static String RESOURCE_PATH = null;

	public static void initResourcePath(String resourcePath) {
		RESOURCE_PATH = String.copyValueOf(resourcePath.toCharArray());
	}

	/**
	 * Get input's configuration
	 * 
	 * @param propName property name
	 * @return configureValue
	 * @throws IOException thrown when configuration file is not found
	 */
	public static String getInputConfig(String propName) throws IOException {
		return getConfigValue(INPUT_CONFIG, propName);
	}

	/**
	 * Get configuration from specific file
	 * 
	 * @param configFile specified configuration file
	 * @param propName   property name
	 * @return configureValue
	 * @throws IOException thrown when configuration file is not found
	 */
	public static String getConfigValue(String configFile, String propName) throws IOException {

		Properties prop = new Properties();

		FileInputStream inputStream = new FileInputStream(makeFullPath(configFile));
		prop.load(inputStream);

		String value = prop.getProperty(propName);
		inputStream.close();

		return value;
	}

	private static String makeFullPath(String configFile) {
		return RESOURCE_PATH + "\\" + configFile;
	}

}
