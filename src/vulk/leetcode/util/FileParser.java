package vulk.leetcode.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileParser {

	public static void parse(String filePath, IFileParser fileReader) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line;
		int lineNumber = 1;
		while ((line = br.readLine()) != null) {
			fileReader.readLine(lineNumber, line);
			lineNumber++;
		}

		br.close();

	}

}
