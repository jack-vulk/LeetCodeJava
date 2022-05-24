package vulk.leetcode.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Utility for csv parsing
 * 
 * @author KJack
 *
 */
public class CsvParser {

	private static final char DEFAULT_DELIMITER = ',';
	private static final char DOUBLE_QUOTE = '\"';

	/**
	 * Parse csv file
	 * 
	 * @param filePath  absolute path to the csv file
	 * @param csvParser callback for each parsed line
	 * @throws IOException
	 */
	public static void parse(String filePath, ICsvParser csvParser) throws IOException {

		// Just an overload to wrap the default settings
		parse(filePath, csvParser, DEFAULT_DELIMITER, true);

	}

	/**
	 * Parse csv file
	 * 
	 * @param filePath    absolute path to the csv file
	 * @param csvParser   callback for each parsed line
	 * @param delimiter
	 * @param removeQuote true = remove double quote from the retrieved value
	 * @throws IOException
	 */
	public static void parse(String filePath, ICsvParser csvParser, char delimiter, boolean removeQuote)
			throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line;
		// TODO: this should have been index 0
		int lineNumber = 1;

		while ((line = br.readLine()) != null) {

			// parse csv content on each line
			List<String> values = parse(line, delimiter, removeQuote);

			// call the callback
			csvParser.readLine(lineNumber, values);
			lineNumber++;

		}

		br.close();

	}

	/**
	 * Parse content presenting csv
	 * 
	 * @param content
	 * @param delimiter
	 * @param removeQuote true = remove double quote from the retrieved value
	 * @return csv values presented in content
	 */
	public static List<String> parse(String content, char delimiter, boolean removeQuote) {

		List<String> values = new LinkedList<>();

		if (ComUtil.isEmpty(content)) {
			return values;
		}

		int quoteCount = 0;
		StringBuffer currentNode = new StringBuffer();
		for (int cIdx = 0; cIdx < content.length(); cIdx++) {

			char currentChar = content.charAt(cIdx);

			if (delimiter == currentChar && quoteCount % 2 == 0) {
				// in case of the current char being the delimiter
				// if both opening and closing quotes are equally
				// presented, then this delimiter marks the end of the node
				// -> add this node to the values list
				String retrievedValue = removeQuote ? trimQuote(currentNode.toString()) : currentNode.toString();
				values.add(retrievedValue);
				// and reset the trackers
				quoteCount = 0;
				currentNode = new StringBuffer();
			} else {
				// else, this char is part of the retrieved node
				// (delimiter included!)
				currentNode.append(currentChar);

				// keep counting the quote
				// in order to determine whether the quote closed correctly
				if (currentChar == DOUBLE_QUOTE) {
					quoteCount++;
				}
			}

		}
		// push the last retrieved value (if any)
		// into the values list
		if (!currentNode.isEmpty()) {
			String retrievedValue = removeQuote ? trimQuote(currentNode.toString()) : currentNode.toString();
			values.add(retrievedValue);
		}

		return values;
	}

	/**
	 * Trim quote
	 * 
	 * @param content
	 * @return content after trimming opening and closing quote
	 */
	private static String trimQuote(String content) {

		// TODO: this method should have been in another Utility
		if (ComUtil.isEmpty(content)) {
			return content;
		}

		if (DOUBLE_QUOTE == content.charAt(0) && DOUBLE_QUOTE == content.charAt(content.length() - 1)) {
			return content.substring(1, content.length() - 1);
		} else {
			return content;
		}
	}
}
