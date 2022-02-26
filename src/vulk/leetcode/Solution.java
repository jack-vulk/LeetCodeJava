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

		ParsedInfo params = this.parse(args);
		O result = this.proc(params);
		printResult(result);

		if (isMatch(result, params.expectedValue)) {
			LOGGER.info("OK!");
		} else {
			LOGGER.info("NG!");
		}

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
	private ParsedInfo parse(String args[]) throws InvalidParameterException {

		// If the params are not set correctly (mostly on run/debug configuration)
		// throw exception
		if (!isValid(args)) {
			LOGGER.log(Level.SEVERE, "Invalid Parameter ");
			print(args);
			throw new InvalidParameterException();
		}

		LOGGER.info("InputFile: " + args[PARAM_FILE_PATH]);

		ParsedInfo parsedInfo = this.parseParam(args);

		LOGGER.info("Input: " + parsedInfo);

		return parsedInfo;
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
		LOGGER.info("Output: " + result);
	}

	/**
	 * Check whether the runtime's result matches with expected value
	 * 
	 * @param result        runtime's result
	 * @param expectedValue
	 * @return true(matched)
	 */
	protected boolean isMatch(O result, O expectedValue) {
		return (ComUtil.isEmpty(result) && ComUtil.isEmpty(expectedValue)) || expectedValue.equals(result);
	}

	/**
	 * Implementation of the solution
	 * 
	 * @param param parameter
	 * @return executation's output
	 * @throws ExecutionException Thrown when there is something wrong during
	 *                            runtime
	 */
	protected abstract O proc(I param) throws ExecutionException;

	/**
	 * Parse input's passed from main process to input and expected value
	 * 
	 * @param args parameter passed from main process
	 * @return input and expected value
	 * @throws InvalidParameterException Thrown when the parameters could not be
	 *                                   parsed
	 */
	protected abstract ParsedInfo parseParam(String args[]) throws InvalidParameterException;

	/**
	 * Check whether the input passed from main process matches with the I/F
	 * 
	 * @param args parameter from main process
	 * @return
	 */
	protected abstract boolean isValid(String args[]);

	/**
	 * Object used to store input and expected value
	 * 
	 * @author KJack
	 *
	 */
	protected class ParsedInfo {
		
		public ParsedInfo() {
			//
		}

		/**
		 * Parameter
		 */
		public I params;
		
		/**
		 * Expected output
		 */
		public O expectedValue;
		
	}

}
