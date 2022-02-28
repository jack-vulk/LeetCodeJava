package vulk.leetcode;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import vulk.leetcode.util.ComUtil;
import vulk.leetcode.util.FileUtil;

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
	 * LOG
	 */
	private static final Logger LOGGER = Logger.getLogger(Solution.class.getName());

	/**
	 * Entry point for the solution
	 * 
	 * @param inputFile inputFile
	 * @return Output
	 * @throws InvalidParameterException Thrown when the parameters are not set
	 *                                   correctly
	 * @throws ExecutionException        Thrown when there is something wrong during
	 *                                   runtime
	 */
	public O run(String inputFile) throws InvalidParameterException, ExecutionException {

		ParsedInfo params = this.parse(inputFile);
		O result = this.proc(params.params);
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
	 * @param inputFile inputFile
	 * @return parameter Object
	 * @throws InvalidParameterException Thrown when the parameters are not set
	 *                                   correctly
	 */
	private ParsedInfo parse(String inputFile) throws InvalidParameterException {

		// If the params are not set correctly (mostly on run/debug configuration)
		// throw exception
		if (!FileUtil.isValidFile(inputFile)) {
			LOGGER.log(Level.SEVERE, "Invalid Parameter " + inputFile);
			throw new InvalidParameterException();
		}

		LOGGER.info("InputFile: " + inputFile);

		ParsedInfo parsedInfo = this.parseParam(inputFile);

		LOGGER.info("Input: " + parsedInfo);

		return parsedInfo;
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
	 * @param filePath filePath to inputFile
	 * @return input and expected value
	 * @throws InvalidParameterException Thrown when the parameters could not be
	 *                                   parsed
	 */
	protected abstract ParsedInfo parseParam(String filePath) throws InvalidParameterException;

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
		
		@Override
		public String toString() {

			List<String> info = new ArrayList<>();
			info.add(String.valueOf(params));
			info.add(String.valueOf(expectedValue));

			return String.join(", ", info);

		}

	}

}
