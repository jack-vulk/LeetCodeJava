package vulk.leetcode.util;

import java.util.Arrays;
import java.util.Collection;

/**
 * Common Utility
 * 
 * @author KJack
 *
 */
public class ComUtil {

	/**
	 * Check whether data is empty
	 * 
	 * @param <T>  data type
	 * @param data data to be checked
	 * @return true(empty), false(not empty)
	 */
	@SuppressWarnings("rawtypes")
	public static <T> boolean isEmpty(T data) {

		if (data == null) {
			return true;
		}

		if (data.getClass().isArray()) {
			return Arrays.asList(data).size() == 0;
		} else if (data instanceof Collection) {
			return ((Collection) data).isEmpty();
		} else if (data instanceof CharSequence) {
			return ((CharSequence) data).isEmpty();
		}

		return false;
	}

	/**
	 * 
	 * Check whether data is not empty
	 * 
	 * @param <T>  data type
	 * @param data data to be checked
	 * @return true(not empty), false(empty)
	 */
	public static <T> boolean isNotEmpty(T data) {
		return !isEmpty(data);
	}

	/**
	 * Print string present of data
	 * 
	 * @param data data to be printed
	 * @return string present of data
	 */
	@SuppressWarnings("rawtypes")
	public static <T> String toString(T data) {

		if (data == null) {
			return String.valueOf(null);
		}

		StringBuffer sb = new StringBuffer();

		if (data.getClass().isArray()) {
			Object[] aData = ((Object[]) data);

			sb.append(toString(aData));
		} else if (data instanceof Collection) {

			Collection cData = ((Collection) data);

			sb.append(toString(cData.toArray()));
		} else if (data instanceof CharSequence) {

			CharSequence csData = ((CharSequence) data);
			sb.append(csData.toString());

		} else {
			sb.append(data.toString());
		}

		return sb.toString();

	}

	/**
	 * Print string present of array
	 * 
	 * @param arrayObject array to be printed
	 * @return string present of array
	 */
	private static String toString(Object[] arrayObject) {

		if (arrayObject == null) {
			return String.valueOf(null);
		}

		StringBuffer sb = new StringBuffer();

		sb.append("[");

		for (int i = 0; i < arrayObject.length; i++) {

			Object object = arrayObject[i];

			sb.append(String.valueOf(object));
			if (i + 1 < arrayObject.length) {
				sb.append(",");
			}
		}

		sb.append("]");

		return sb.toString();
	}

}
