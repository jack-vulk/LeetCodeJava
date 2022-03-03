package vulk.leetcode;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import vulk.leetcode.util.ComUtil;
import vulk.leetcode.util.ConfigLoader;
import vulk.leetcode.util.FileUtil;

/**
 * 
 * Basically the starting point
 * 
 * @author KJack
 *
 */
public class Main {

	/**
	 * Index to retrieve the input file's path
	 */
	protected static final int PARAM_FILE_PATH = 1;

	/**
	 * LOG
	 */
	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

	/**
	 * Entry point
	 * 
	 * @param args parameters
	 */
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {

		ConfigLoader.initResourcePath(args[0]);

		try {
			Solution solution = getSolution();
			execute(solution, args);
		} catch (InvalidParameterException e) {
			LOGGER.log(Level.SEVERE, "Invalid parameter", e);
		} catch (ExecutionException e) {
			LOGGER.log(Level.SEVERE, "Execution failed", e);
		} catch (IOException | ClassNotFoundException e) {
			LOGGER.log(Level.SEVERE, "Invalid configuration", e);
		}
	}

	/**
	 * 
	 * @param solution
	 * @param args
	 * @throws InvalidParameterException Thrown when the parameters are not set
	 *                                   correctly
	 * @throws ExecutionException        Thrown when there is something wrong during
	 *                                   runtime
	 */
	@SuppressWarnings("rawtypes")
	public static void execute(Solution solution, String[] args)
			throws InvalidParameterException, ExecutionException {

		if (ComUtil.isEmpty(args)) {
			throw new InvalidParameterException();
		}

		String inputConfig = null;
		try {
			inputConfig = ConfigLoader.getInputConfig(solution.getClass().getName());
		} catch (IOException e) {
			e.printStackTrace();
			throw new InvalidParameterException();
		}

		if (ComUtil.isEmpty(inputConfig)) {
			throw new InvalidParameterException();
		}

		final String filePath = args[PARAM_FILE_PATH] + inputConfig;

		if (FileUtil.isValidPath(filePath)) {

			List<String> files = FileUtil.getFiles(filePath);

			for (String inputFile : files) {
				solution.run(inputFile);
			}

		} else if (FileUtil.isValidFile(filePath)) {
			solution.run(filePath);
		} else {
			throw new InvalidParameterException();
		}
		
	}

	/**
	 * Init solution based on configuration
	 * 
	 * @return solution based instance
	 * @throws IOException               no input.configures file
	 * @throws ClassNotFoundException
	 * @throws InvalidParameterException
	 */
	@SuppressWarnings("rawtypes")
	public static Solution getSolution() throws ClassNotFoundException, IOException, InvalidParameterException {

		Class<?> targetClass = Class.forName(ConfigLoader.getInputConfig("target").trim());
		Object solutionObj = null;
		try {
			solutionObj = targetClass.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!(solutionObj instanceof Solution)) {
			throw new InvalidParameterException();
		}

		return (Solution) solutionObj;
	}

}
