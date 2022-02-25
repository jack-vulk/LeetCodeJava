package vulk.leetcode;

import java.security.InvalidParameterException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import vulk.leetcode.util.ComUtil;

/**
 * 
 * An abstract solution class 
 * @author KJack
 *
 * @param <I> Input Type
 * @param <O> Output Type
 */
public abstract class Solution<I, O> {

	/**
	 * Index to retrieve the input file's path
	 */
	protected static final int PARAM_FILE_PATH = 0;

	/**
	 * LOG
	 */
	private static final Logger LOGGER = Logger.getLogger(Solution.class.getName());

	/**
	 * Entry point for the solution
	 * 
	 * @param args Params
	 * @return Output
	 * @throws InvalidParameterException Thrown when the parameters are not set
	 *                                   correctly
	 * @throws ExecutionException        Thrown when there is something wrong during
	 *                                   runtime
	 */
	public O run(String args[]) throws InvalidParameterException, ExecutionException {

		O result = this.execute(this.parse(args));
		printResult(result);
		return result;
	}

	/**
	 * Parse the args to parameter object
	 * 
	 * @param args params
	 * @return parameter Object
	 * @throws InvalidParameterException Thrown when the parameters are not set
	 *                                   correctly
	 */
	private I parse(String args[]) throws InvalidParameterException {

		// If the params are not set correctly (mostly on run/debug configuration)
		// throw exception
		if (!isValid(args)) {
			LOGGER.log(Level.SEVERE, "Invalid Parameter ");
			print(args);
			throw new InvalidParameterException();
		}

		LOGGER.info("InputFile: " + args[PARAM_FILE_PATH]);

		I params = this.parseParam(args);

		LOGGER.info("Input: " + params);

		return params;
	}

	/**
	 * 
	 * Print the array of object
	 * 
	 * @param args
	 */
	private static void print(Object[] args) {

		if (ComUtil.isEmpty(args)) {
			LOGGER.info("empty");
		} else {
			for (Object arg : args) {
				LOGGER.info(String.valueOf(arg));
			}
		}
	}

	/**
	 * Print the o
	 * 
	 * @param result
	 */
	protected void printResult(O result) {
		LOGGER.info("Output: " + result.toString());
	}

	protected abstract O execute(I param) throws ExecutionException;

	protected abstract I parseParam(String args[]) throws InvalidParameterException;

	protected abstract boolean isValid(String args[]);

}
