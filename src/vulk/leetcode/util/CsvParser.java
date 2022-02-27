package vulk.leetcode.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Utility for csv parsing
 * 
 * @author KJack
 *
 */
public class CsvParser {

	private static final String COMMA_DELIMITER = ",";

	public static void parse(String filePath, ICsvParser csvParser) throws IOException {

		parse(filePath, csvParser, true);

	}

	public static void parse(String filePath, ICsvParser csvParser, boolean removeQuote) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line;
		int lineNumber = 1;
		while ((line = br.readLine()) != null) {
			String[] values = line.split(COMMA_DELIMITER);

			if (removeQuote && ComUtil.isNotEmpty(values)) {
				for (int index = 0; index < values.length; index++) {
					values[index] = values[index].replace("\"", "");
				}
			}

			csvParser.readLine(lineNumber, Arrays.asList(values));
			lineNumber++;

		}

		br.close();

	}
}
