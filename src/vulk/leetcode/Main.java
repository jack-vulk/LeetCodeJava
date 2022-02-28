package vulk.leetcode;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import vulk.leetcode.easy.SummaryRanges;
import vulk.leetcode.util.ComUtil;
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
	protected static final int PARAM_FILE_PATH = 0;

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
		// Init the solution with the specific class
		// Solution solution = new MergeTwoSortedLst();
		// Solution solution = new AddTwoNumber();
		// Solution solution = new CountWordsWithPrefix();
		// Solution solution = new MinStepAnagram();
		Solution solution = new SummaryRanges();

		try {
			execute(solution, args);
		} catch (InvalidParameterException e) {
			LOGGER.log(Level.SEVERE, "Invalid parameter", e);
		} catch (ExecutionException e) {
			LOGGER.log(Level.SEVERE, "Execution failed", e);
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

		final String filePath = args[PARAM_FILE_PATH];

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

}
