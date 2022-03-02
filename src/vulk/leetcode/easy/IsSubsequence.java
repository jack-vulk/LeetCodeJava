package vulk.leetcode.easy;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import vulk.leetcode.Solution;
import vulk.leetcode.easy.IsSubsequence.Param;
import vulk.leetcode.util.CsvParser;
import vulk.leetcode.util.ICsvParser;

/**
 * Solution for merge-two-sorted-lists on LeetCode
 * 
 * @author KJack
 *
 */
public class IsSubsequence extends Solution<Param, Boolean> {

	private static final int PARAM_INPUT_S = 1;
	private static final int PARAM_INPUT_T = 2;
	private static final int PARAM_EXPECTED_OUTPUT = 3;

	public boolean isSubsequence(String s, String t) {

		if (s == null || s.length() == 0) {
			return true;
		} else if (t == null || s.length() == 0) {
			return false;
		}

		final char[] aS = s.toCharArray();
		int indexS = 0;
		final char[] aT = t.toCharArray();

		for (int indexT = 0; indexT < aT.length; indexT++) {
			if (aT[indexT] == aS[indexS] && ++indexS >= aS.length) {
				return true;
			}
		}

		return false;
    }

	// ===================================
	// The following code must not be posted on to LeetCode
	// ===================================
	@Override
	protected Boolean proc(Param param) throws ExecutionException {
		try {
			return isSubsequence(param.s, param.t);
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
					case PARAM_INPUT_S:
						info.params.s = data.get(0);
						break;
					case PARAM_INPUT_T:
						info.params.t = data.get(0);
						break;
					case PARAM_EXPECTED_OUTPUT:
						info.expectedValue = Boolean.valueOf(data.get(0));
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

		private String s;

		private String t;

		@Override
		public String toString() {

			return String.valueOf(s) + "," + String.valueOf(t);

		}
	}
}
