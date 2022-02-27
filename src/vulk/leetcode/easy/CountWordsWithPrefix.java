package vulk.leetcode.easy;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import vulk.leetcode.Solution;
import vulk.leetcode.easy.CountWordsWithPrefix.Param;
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
public class CountWordsWithPrefix extends Solution<Param, Integer> {

	private static final int PARAM_WORD = 1;
	private static final int PARAM_PREFIX = 2;
	private static final int PARAM_EXPECTED_OUTPUT = 3;

	// ================
	// Recursion FTW
	// ================
	public int prefixCount(String[] words, String pref) {
        
		if (words == null || words.length == 0 || pref == null || pref.length() == 0) {
			return 0;
		}

		final int prefSize = pref.length();
		int count = 0;

		for (String word : words) {

			if (word.length() >= prefSize && word.substring(0, prefSize).equals(pref)) {
				count++;
			}

		}

		return count;
    }

	// ===================================
	// The following code must not be posted on to LeetCode
	// ===================================
	@Override
	protected Integer proc(Param param) throws ExecutionException {
		try {
			return prefixCount(param.words, param.prefix);
		} catch (Exception e) {
			throw new ExecutionException(e);
		}
	}

	@Override
	protected ParsedInfo parseParam(String[] args) {
		
		ParsedInfo info = new ParsedInfo();
		info.params = new Param();

		try {
			CsvParser.parse(args[PARAM_FILE_PATH], new ICsvParser() {

				@Override
				public void readLine(int line, List<String> data) {

					switch (line) {
					case PARAM_WORD:
						info.params.words = data.toArray(new String[] {});
						break;
					case PARAM_PREFIX:
						info.params.prefix = data.get(0);
						break;
					case PARAM_EXPECTED_OUTPUT:
						info.expectedValue = Integer.parseInt(data.get(0));
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

		/**
		 * First List
		 */
		private String[] words;

		/**
		 * 
		 */
		private String prefix;

		@Override
		public String toString() {

			StringBuffer sb = new StringBuffer();

			sb.append(Arrays.asList(words));
			sb.append(", ");
			sb.append(prefix);

			return sb.toString();

		}
	}
}
