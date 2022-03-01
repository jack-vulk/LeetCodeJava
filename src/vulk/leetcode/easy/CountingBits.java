package vulk.leetcode.easy;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import vulk.leetcode.Solution;
import vulk.leetcode.easy.CountingBits.Param;
import vulk.leetcode.util.CsvParser;
import vulk.leetcode.util.ICsvParser;

/**
 * Solution for merge-two-sorted-lists on LeetCode
 * 
 * @author KJack
 *
 */
public class CountingBits extends Solution<Param, int[]> {

	private static final int PARAM_INPUT = 1;
	private static final int PARAM_EXPECTED_OUTPUT = 2;

	public int[] countBits(int n) {

		// 0 -> 0
		// 1 -> 1
		// 2 -> 10
		// 3 -> 11
		// 4 -> 100
		// 5 -> 101
		// 6 -> 110
		// 7 -> 111
		// 8 -> 1000
		// 9 -> 1001
		// 10 -> 1010
		// 11 -> 1011
		// 12 -> 1100
		// 13 -> 1101
		// 14 -> 1110
		// 15 -> 1111

		int currentBits = 1;
		int currentTotalSlots;
		int usedUpSlot = -1;
		int i = 0;
		int[] result = new int[n + 1];
		result[i++] = 0;

		// Main
		while (i <= n) {

			result[i] = 1;
			usedUpSlot++;
			currentTotalSlots = (int) Math.pow(2, currentBits - 1);

			// Sub
			while (usedUpSlot < currentTotalSlots && i + usedUpSlot <= n) {
				result[i + usedUpSlot] = 1 + result[usedUpSlot];
				usedUpSlot++;
			}

			usedUpSlot = 0;
			i = (int) Math.pow(2, currentBits);
			currentBits++;

		}

		return result;

    }

	// ===================================
	// The following code must not be posted on to LeetCode
	// ===================================
	@Override
	protected boolean isMatch(int[] result, int[] expectedValue) {

		return Arrays.equals(result, expectedValue);
	}

	@Override
	protected int[] proc(Param param) throws ExecutionException {
		try {
			return countBits(param.n);
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
						info.params.n = Integer.parseInt(data.get(0));
						break;
					case PARAM_EXPECTED_OUTPUT:
						info.expectedValue = data.stream().mapToInt(Integer::parseInt).toArray();
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

		/**
		 * First List
		 */
		private int n;

		@Override
		public String toString() {

			return String.valueOf(n);

		}
	}
}
