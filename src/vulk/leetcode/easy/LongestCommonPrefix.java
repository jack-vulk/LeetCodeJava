package vulk.leetcode.easy;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import vulk.leetcode.Solution;
import vulk.leetcode.easy.LongestCommonPrefix.Param;
import vulk.leetcode.util.CsvParser;
import vulk.leetcode.util.ICsvParser;

/**
 * Solution for merge-two-sorted-lists on LeetCode
 * 
 * @author KJack
 *
 */
public class LongestCommonPrefix extends Solution<Param, String> {

	private static final int PARAM_INPUT = 1;
	private static final int PARAM_EXPECTED_OUTPUT = 2;

	public String longestCommonPrefix(String[] strs) {

		StringBuffer sb = new StringBuffer("");
		int cIndex = 0;
		char cChar;

		if (strs == null || strs.length < 1 || strs[0].length() == 0) {
			return sb.toString();
		} else if (strs.length == 1) {
			return strs[0];
		}

		final char[] firstStr = strs[0].toCharArray();

		for (cIndex = 0; cIndex < firstStr.length; cIndex++) {

			cChar = firstStr[cIndex];
			for (int cElement = 1; cElement < strs.length; cElement++) {
				if (cIndex >= strs[cElement].length() || cChar != strs[cElement].charAt(cIndex)) {
					return sb.toString();
				}
			}
			sb.append(cChar);
		}

		return sb.toString();

    }

	// ===================================
	// The following code must not be posted on to LeetCode
	// ===================================
	@Override
	protected String proc(Param param) throws ExecutionException {
		try {
			return longestCommonPrefix(param.strs);
		} catch (Exception e) {
			throw new ExecutionException(e);
		}
	}

	@Override
	protected ParsedInfo parseParam(String filePath) {
		
		ParsedInfo info = new ParsedInfo();
		info.params = new Param();

		try {
			CsvParser.parse(filePath, new ICsvParser() {

				@Override
				public void readLine(int line, List<String> data) {

					switch (line) {
					case PARAM_INPUT:
						info.params.strs = data.toArray(new String[] {});
						break;
					case PARAM_EXPECTED_OUTPUT:
						info.expectedValue = data.get(0);
						break;
					}

				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return info;
	}

	/**
	 * Param for this solution
	 * 
	 * @author KJack
	 *
	 */
	protected static class Param {

		private String[] strs;

		@Override
		public String toString() {

			return Arrays.toString(strs);

		}
	}
}
