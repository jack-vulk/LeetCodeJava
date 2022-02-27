package vulk.leetcode.util;

/**
 * Interface for FileParser
 * 
 * @author KJack
 *
 */
public interface IFileParser {

	/**
	 * This method shall be call after the FileParser finished reading a line
	 * 
	 * @param line processed line
	 * @param data parsed data
	 */
	void readLine(int line, String data);

}
