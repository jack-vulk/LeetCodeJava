package vulk.leetcode.util;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

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
	public static boolean isValidFile(String filePath) {
		File targetFile = new File(filePath);
		return Files.isReadable(targetFile.toPath());
	}

	/**
	 * Check if the file path is a valid folder
	 * 
	 * @param filePath file path to be checked
	 * @return true(valid folder)
	 */
	public static boolean isValidPath(String filePath) {
		File targetFile = new File(filePath);

		return Files.isDirectory(targetFile.toPath());
	}

	/**
	 * Get path to all files in the same directory
	 * 
	 * @param directory target directory
	 * @return list of file paths
	 */
	public static List<String> getFiles(String directory) {
		File targetFile = new File(directory);
		File[] lstFiles = targetFile.listFiles();

		List<String> filePaths = new ArrayList<String>();

		for (File file : lstFiles) {
			filePaths.add(file.getAbsolutePath());
		}

		return filePaths;
	}
}
