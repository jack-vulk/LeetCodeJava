package vulk.leetcode.easy;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import vulk.leetcode.Solution;
import vulk.leetcode.easy.SummaryRanges.Param;
import vulk.leetcode.util.ComUtil;
import vulk.leetcode.util.CsvParser;
import vulk.leetcode.util.FileUtil;
import vulk.leetcode.util.ICsvParser;

/**
 * Solution for merge-two-sorted-lists on LeetCode
 * 
 * @author KJack
 *
 */
public class SummaryRanges extends Solution<Param, List<String>> {

	private static final int PARAM_LINE_INPUT = 1;
	private static final int PARAM_LINE_OUTPUT = 2;

	public List<String> summaryRanges(int[] nums) {

		List<String> results = new ArrayList<String>();

		final int numsSize = nums.length;
		int startNum;
		int endNum;
		int index = 0;
		int nextIndex;

		while (index < numsSize) {

			startNum = nums[index];
			endNum = startNum;

			for (nextIndex = index + 1; nextIndex < numsSize; nextIndex++) {

				if (Math.abs(nums[nextIndex] - nums[nextIndex - 1]) > 1) {
					break;
				}
				endNum = nums[nextIndex];
			}

			index = nextIndex;
			results.add(makeRange(startNum, endNum));

		}

		return results;
	}

	private String makeRange(int start, int end) {
		return start == end ? String.valueOf(start) : String.valueOf(start) + "->" + String.valueOf(end);
	}

	// ===================================
	// The following code must not be posted on to LeetCode
	// ===================================
	@Override
	protected List<String> proc(Param param) throws ExecutionException {
		try {
			return summaryRanges(param.nums);
		} catch (Exception e) {
			throw new ExecutionException(e);
		}
	}

	@Override
	protected ParsedInfo parseParam(String[] args) {

		ParsedInfo info = new ParsedInfo();
		info.params = new Param();

		final String filePath = args[PARAM_FILE_PATH];
		
		try {
			CsvParser.parse(filePath, new ICsvParser() {

				@Override
				public void readLine(int lineNumber, List<String> data) {

					switch (lineNumber) {
					case PARAM_LINE_INPUT: {
						info.params.nums = data.stream().mapToInt(Integer::parseInt).toArray();
						break;
					}
					case PARAM_LINE_OUTPUT: {
						info.expectedValue = data;
						break;
					}

					}

				}
			});
		} catch (IOException e) {
			throw new InvalidParameterException();
		}

		return info;
	}

	@Override
	protected boolean isValid(final String[] args) {

		return ComUtil.isNotEmpty(args) && FileUtil.isValidFile(args[PARAM_FILE_PATH]);
	}

	/**
	 * Param for this solution
	 * 
	 * @author KJack
	 *
	 */
	protected static class Param {

		int[] nums;

		@Override
		public String toString() {
			
			return Arrays.toString(nums);
		}
	}
}
