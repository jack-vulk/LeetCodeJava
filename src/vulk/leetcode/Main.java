package vulk.leetcode;

import java.security.InvalidParameterException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import vulk.leetcode.easy.MergeTwoSortedLst;

/**
 * 
 * Basically the starting point
 * 
 * @author KJack
 *
 */
public class Main {

	/**
	 * LOG
	 */
	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {

		// Init the solution with the specific class
		Solution solution = new MergeTwoSortedLst();

		try {
			solution.excute(args);
		} catch (InvalidParameterException e) {
			LOGGER.log(Level.SEVERE, "Invalid parameter", e);
		} catch (ExecutionException e) {
			LOGGER.log(Level.SEVERE, "Execution failed", e);
		}
	}
}
