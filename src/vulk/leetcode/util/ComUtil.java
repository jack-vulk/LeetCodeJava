package vulk.leetcode.util;

import java.lang.reflect.Array;
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
	public static boolean isEmpty(Object data) {

		if (data == null) {
			return true;
		}

		if (data.getClass().isArray()) {
			return Array.getLength(data) == 0;
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
	public static boolean isNotEmpty(Object data) {
		return !isEmpty(data);
	}

	/**
	 * Print string present of data
	 * 
	 * @param data data to be printed
	 * @return string present of data
	 */
	@SuppressWarnings("rawtypes")
	public static String toString(Object data) {

		if (data == null) {
			return String.valueOf(null);
		}

		StringBuffer sb = new StringBuffer();

		if (data.getClass().isArray()) {
			Object[] aData = castToObjectArray(data);

			sb.append(Arrays.toString(aData));
		} else if (data instanceof Collection) {

			Collection cData = ((Collection) data);

			sb.append(Arrays.toString(cData.toArray()));
		} else {
			sb.append(data.toString());
		}

		return sb.toString();

	}

	/**
	 * 
	 * Safely cast an array object to Object[]
	 * 
	 * @param obj object to be cast
	 * @return array that present obj
	 */
	@SuppressWarnings("rawtypes")
	public static Object[] castToObjectArray(Object obj) {

		Class dataType = obj.getClass().getComponentType();
		if (dataType.isPrimitive()) {
			final int aLength = Array.getLength(obj);
			Object[] aData = new Object[aLength];

			for (int i = 0; i < aLength; i++) {
				aData[i] = Array.get(obj, i);
			}

			return aData;
		} else {
			return (Object[]) obj;
		}
	}

}
