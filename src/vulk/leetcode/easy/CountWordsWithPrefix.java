package vulk.leetcode.easy;

import java.util.concurrent.ExecutionException;

import vulk.leetcode.Solution;
import vulk.leetcode.easy.CountWordsWithPrefix.Param;
import vulk.leetcode.util.ComUtil;
import vulk.leetcode.util.FileUtil;

/**
 * Solution for merge-two-sorted-lists on LeetCode
 * 
 * @author KJack
 *
 */
public class CountWordsWithPrefix extends Solution<Param, Integer> {

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
		Param param = new Param();

		param.words = new String[] { "pay", "attention", "practice", "attend" };

		param.prefix = "at";

		info.params = param;
		info.expectedValue = 2;

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

	}
}
