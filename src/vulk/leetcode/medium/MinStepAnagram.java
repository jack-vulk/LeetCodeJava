package vulk.leetcode.medium;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import vulk.leetcode.Solution;
import vulk.leetcode.medium.MinStepAnagram.Param;
import vulk.leetcode.util.FileParser;
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

	private static final int PARAM_COMPARE_RESULT = 0;
	private static final int PARAM_COMPARE_MATCH_COUNT_FIRST = 1;
	private static final int PARAM_COMPARE_MATCH_COUNT_SECOND = 2;

	public int minSteps(String s, String t) {

		return minStepsHash(s, t);
	}

	// ===========================
	// Solution using hash
	// ===========================
	public int minStepsHash(String firstStr, String secondStr) {

		int[] firstStrHash = getHash(firstStr);
		int[] secondStrHash = getHash(secondStr);

		int count = 0;
		for (int cIndex = 0; cIndex < 26; cIndex++) {
			count += Math.abs(firstStrHash[cIndex] - secondStrHash[cIndex]);
		}

		return count;
	}

	public int[] getHash(String text) {

		int[] result = new int[26];

		if (text == null) {
			return result;
		}

		int cIndex = 0;
		char[] cLst = text.toCharArray();

		for (char cText : cLst) {

			cIndex = cText - 'a';

			result[cIndex] += 1;

		}

		return result;
	}

	// ===========================
	// Solution using loop
	// ===========================

	public int minStepsIterStart(String s, String t) {

		char[] arrayS = s == null ? new char[] {} : s.toCharArray();
		char[] arrayT = t == null ? new char[] {} : t.toCharArray();
		Arrays.sort(arrayS);
		Arrays.sort(arrayT);

		return minStepsIter(String.valueOf(arrayS), String.valueOf(arrayT));

	}

	private int minStepsIter(String s, String t) {

		if (s == null || s.length() == 0) {
			return t.length();
		} else if (t == null || t.length() == 0) {
			return s.length();
		}

		int[] comparision;
		int compareResult;
		int matchedCountFirst;
		int matchedCountSecond;
		int countDiff = 0;
		String remainingFirst = s;
		String remainingSecond = t;

		do {

			if (remainingFirst.length() == 0) {
				countDiff += remainingSecond.length();
				break;
			} else if (remainingSecond.length() == 0) {
				countDiff += remainingFirst.length();
				break;
			}

			comparision = compare(remainingFirst, remainingSecond);
			compareResult = comparision[PARAM_COMPARE_RESULT];
			matchedCountFirst = comparision[PARAM_COMPARE_MATCH_COUNT_FIRST];
			matchedCountSecond = comparision[PARAM_COMPARE_MATCH_COUNT_SECOND];

			if (compareResult == 0) {
				countDiff += Math.abs(matchedCountFirst - matchedCountSecond);
				remainingFirst = remainingFirst.substring(matchedCountFirst);
				remainingSecond = remainingSecond.substring(matchedCountSecond);
			} else {
				if (compareResult > 0) {
					// first > second
					String temp = remainingFirst;
					remainingFirst = remainingSecond.substring(matchedCountSecond);
					remainingSecond = temp;
					countDiff += matchedCountSecond;
				} else {
					// second > first
					String temp = remainingSecond;
					remainingSecond = remainingFirst.substring(matchedCountFirst);
					remainingFirst = temp;
					countDiff += matchedCountFirst;
				}
			}

		} while (true);

		return countDiff;
	}

	private int[] compare(String first, String second) {
		int[] result = new int[3];

		String remainingFirstChar = first.substring(0, 1);
		String remainingSecondChar = second.substring(0, 1);

		result[PARAM_COMPARE_RESULT] = remainingFirstChar.compareTo(remainingSecondChar);
		result[PARAM_COMPARE_MATCH_COUNT_FIRST] = first.lastIndexOf(remainingFirstChar) + 1;
		result[PARAM_COMPARE_MATCH_COUNT_SECOND] = second.lastIndexOf(remainingSecondChar) + 1;

		return result;
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
	protected ParsedInfo parseParam(String filePath) {

		ParsedInfo info = new ParsedInfo();
		info.params = new Param();

		try {
			FileParser.parse(filePath, new IFileParser() {

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
