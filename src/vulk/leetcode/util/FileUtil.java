package vulk.leetcode.util;

import java.io.File;
import java.nio.file.Files;

/**
 * Utility for dealing with File
 * 
 * @author KJack
 *
 */
public class FileUtil {

	/**
	 * Check if the file path is valid
	 * 
	 * @param filePath file path to be checked
	 * @return true(valid)
	 */
	public static boolean iValidFile(String filePath) {
		File targetFile = new File(filePath);
		return Files.isReadable(targetFile.toPath());
	}
}
