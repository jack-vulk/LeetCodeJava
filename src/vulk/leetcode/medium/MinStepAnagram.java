package vulk.leetcode.medium;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import vulk.leetcode.Solution;
import vulk.leetcode.medium.MinStepAnagram.Param;
import vulk.leetcode.util.ComUtil;
import vulk.leetcode.util.FileParser;
import vulk.leetcode.util.FileUtil;
import vulk.leetcode.util.IFileParser;

/**
 * Solution for merge-two-sorted-lists on LeetCode
 * 
 * @author KJack
 *
 */
public class MinStepAnagram extends Solution<Param, Integer> {

	private static final int PARAM_FIRST_WORD = 1;
	private static final int PARAM_SECOND_WORD = 2;
	private static final int PARAM_EXPECTED_OUTPUT = 3;

	public int minSteps(String s, String t) {

		char[] arrayS = s == null ? new char[] {} : s.toCharArray();
		char[] arrayT = t == null ? new char[] {} : t.toCharArray();
		Arrays.sort(arrayS);
		Arrays.sort(arrayT);

		return minStepsRecursive(String.valueOf(arrayS), String.valueOf(arrayT));
	}

	// ===========================
	// Solution using loop, right but not effective
	// ===========================
	private int minStepsIter(String s, String t) {

		if (s == null || s.length() == 0) {
			return t.length();
		} else if (t == null || t.length() == 0) {
			return s.length();
		}

		int countDiff = 0;
		String secondStr = String.valueOf(t.toCharArray());
		String firstString;

		for (int sIndex = 0; sIndex < s.length(); sIndex++) {

			firstString = String.valueOf(s.charAt(sIndex));

			if (secondStr.contains(firstString)) {
				secondStr = secondStr.replaceFirst(firstString, "");
			} else {
				countDiff++;
			}

		}

		if (secondStr.length() == 0 && t.length() == countDiff) {
			countDiff = 0;
		} else {
			countDiff += secondStr.length();
		}

		return countDiff;
	}

	// =================================
	// May cause stackoverflow
	// =================================
	public int minStepsRecursive(String s, String t) {

		if (s.length() == 0) {
			return t.length();
		} else if (t.length() == 0) {
			return s.length();
		}

		String remainingFirstString = "";
		String remainingSecondString = "";

		int comparision = s.substring(0, 1).compareTo(t.substring(0, 1));

		if (comparision == 0) {
			remainingFirstString = s.substring(1);
			remainingSecondString = t.substring(1);
			try {
				return minStepsRecursive(remainingFirstString, remainingSecondString);
			} catch (Exception e) {
				throw e;
			}
		} else {
			if (comparision > 0) {
				remainingFirstString = t.substring(1);
				remainingSecondString = s;
			} else {
				remainingFirstString = s.substring(1);
				remainingSecondString = t;
			}
			return 1 + minStepsRecursive(remainingFirstString, remainingSecondString);
		}

	}

	// ===================================
	// The following code must not be posted on to LeetCode
	// ===================================
	@Override
	protected Integer proc(Param param) throws ExecutionException {
		try {
			return minSteps(param.firstWord, param.secondWord);
		} catch (Exception e) {
			throw new ExecutionException(e);
		}
	}

	@Override
	protected ParsedInfo parseParam(String[] args) {

		ParsedInfo info = new ParsedInfo();
		info.params = new Param();

		try {
			FileParser.parse(args[PARAM_FILE_PATH], new IFileParser() {

				@Override
				public void readLine(int line, String data) {

					switch (line) {
					case PARAM_FIRST_WORD: 
						
						info.params.firstWord = data;
						break;
					case PARAM_SECOND_WORD:
						info.params.secondWord = data;
						break;
					case PARAM_EXPECTED_OUTPUT:
						info.expectedValue = Integer.parseInt(data);
						break;
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

		/**
		 * First List
		 */
		private String firstWord;

		/**
		 * Second List
		 */
		private String secondWord;

		@Override
		public String toString() {

			StringBuffer sb = new StringBuffer();
			sb.append(firstWord);
			sb.append(",");
			sb.append(secondWord);
			return sb.toString();
		}

	}
}
