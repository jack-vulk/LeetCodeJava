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

	public static void parse(String filePath, ICsvParser csvParser) throws IOException {

		parse(filePath, csvParser, DEFAULT_DELIMITER, true);

	}

	public static void parse(String filePath, ICsvParser csvParser, char delimiter, boolean removeQuote)
			throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line;
		int lineNumber = 1;
		while ((line = br.readLine()) != null) {

			List<String> values = parse(line, delimiter, removeQuote);

			csvParser.readLine(lineNumber, values);
			lineNumber++;

		}

		br.close();

	}

	public static List<String> parse(String content, char delimiter, boolean removeQuote) {

		List<String> values = new LinkedList<>();

		if (ComUtil.isEmpty(content)) {
			return values;
		}

		int quoteCount = 0;
		StringBuffer currentNode = new StringBuffer();
		for (int cIdx = 0; cIdx < content.length(); cIdx++) {

			char currentContent = content.charAt(cIdx);

			if (delimiter == currentContent && quoteCount % 2 == 0) {

				// if both opening and closing quotes are equally
				// presented, then this delimiter marks the end of the node
				String retrievedValue = removeQuote ? trimQuote(currentNode.toString()) : currentNode.toString();
				values.add(retrievedValue);
				// reset all the tracker
				quoteCount = 0;
				currentNode = new StringBuffer();
			} else {
				currentNode.append(currentContent);

				if (currentContent == DOUBLE_QUOTE) {
					quoteCount++;
				}
			}

		}
		if (!currentNode.isEmpty()) {
			String retrievedValue = removeQuote ? trimQuote(currentNode.toString()) : currentNode.toString();
			values.add(retrievedValue);
		}

		return values;
	}

	private static String trimQuote(String content) {

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
