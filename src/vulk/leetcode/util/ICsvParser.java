package vulk.leetcode.util;

import java.util.List;

/**
 * Interface for CsvParser
 * 
 * @author KJack
 *
 */
public interface ICsvParser {

	/**
	 * This method shall be call after the CsvParser finished reading a line
	 * 
	 * @param line processed line
	 * @param data parsed data
	 */
	void readLine(int line, List<String> data);
}
