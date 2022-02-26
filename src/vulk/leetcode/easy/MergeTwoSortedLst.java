package vulk.leetcode.easy;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import vulk.leetcode.Solution;
import vulk.leetcode.common.ListNode;
import vulk.leetcode.easy.MergeTwoSortedLst.Param;
import vulk.leetcode.util.ComUtil;
import vulk.leetcode.util.CsvParser;
import vulk.leetcode.util.FileUtil;
import vulk.leetcode.util.ICsvParser;

/**
 * Solution for merge-two-sorted-lists on LeetCode
 * 
 * @author KJack
 *
 */
public class MergeTwoSortedLst extends Solution<Param, ListNode> {

	private static final int PARAM_LIST_NODE_FIRST_IDX = 0;
	private static final int PARAM_LIST_NODE_LAST_IDX = 1;

	// ================
	// Recursion FTW
	// ================
	public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

		// Provided with the following constrant
		// 1) list1 and list2 are sorted by ascending order

		if (list1 == null && list2 == null) {
			return null;
		} else if (list1 == null) {
			return list2;
		} else if (list2 == null) {
			return list1;
		}

		ListNode smaller = list1.val > list2.val ? list2 : list1;
		ListNode larger = smaller == list1 ? list2 : list1;
		int smallerVal = smaller.val;
		smaller = smaller.next;

		return new ListNode(smallerVal, mergeTwoLists(smaller, larger));

	}

	// ================
	// Using Map 7% performance
	// ================
	public ListNode mergeTwoListsMap(ListNode list1, ListNode list2) {

		if (list1 == null && list2 == null) {
			return null;
		} else if (list1 == null) {
			return list2;
		} else if (list2 == null) {
			return list1;
		}

		ListNode result = null;
		ListNode resultNode = null;

		Map<Integer, Integer> listMap = new TreeMap<>();
		ListNode node = list1;
		while (node != null) {
			listMap.put(node.val, listMap.getOrDefault(node.val, 0) + 1);
			node = node.next;
		}
		node = list2;
		while (node != null) {
			listMap.put(node.val, listMap.getOrDefault(node.val, 0) + 1);
			node = node.next;
		}

		for (Entry<Integer, Integer> entrySet : listMap.entrySet()) {
			ListNode[] aResult = get(entrySet.getKey(), entrySet.getValue());
			if (result == null) {
				result = aResult[PARAM_LIST_NODE_FIRST_IDX];
			}
			if (resultNode == null) {
				resultNode = aResult[PARAM_LIST_NODE_LAST_IDX];
			} else {
				resultNode.next = aResult[PARAM_LIST_NODE_FIRST_IDX];
				resultNode = aResult[PARAM_LIST_NODE_LAST_IDX];
			}
		}

		return result;

	}

	private ListNode[] get(int val, int count) {

		ListNode[] aResult = new ListNode[2];
		ListNode result = null;
		ListNode firstResult = null;

		for (int i = 0; i < count; i++) {

			if (result == null) {
				result = new ListNode(val, new ListNode());
			} else {
				result = result.next;
				result.val = val;
				result.next = new ListNode();
			}
			if (firstResult == null) {
				firstResult = result;
			}
		}

		if (result != null) {
			result.next = null;
		}

		aResult[PARAM_LIST_NODE_FIRST_IDX] = firstResult;
		aResult[PARAM_LIST_NODE_LAST_IDX] = result;

		return aResult;
	}

	// ===================================
	// The following code must not be posted on to LeetCode
	// ===================================
	@Override
	protected ListNode proc(Param param) {
		return mergeTwoLists(param.firstList, param.secondList);
	}

	@Override
	protected ParsedInfo parseParam(String[] args) {

		ParsedInfo info = new ParsedInfo();
		info.params = new Param();

		final String filePath = args[PARAM_FILE_PATH];
		
		try {
			CsvParser.parse(filePath, new ICsvParser() {

				@Override
				public void readLine(int lineNumber, List<String> data) {

					if (lineNumber > 3 || ComUtil.isEmpty(data)) {
						return;
					}

					ListNode dataNode = new ListNode();
					ListNode resultNode = dataNode;

					for (int index = 0; index < data.size(); index++) {
						dataNode.val = Integer.parseInt(data.get(index));
						if (index + 1 < data.size()) {
							ListNode nextNode = new ListNode();
							dataNode.next = nextNode;
							dataNode = nextNode;
						}
					}

					if (lineNumber == 1) {
						info.params.firstList = resultNode;
					} else if (lineNumber == 2) {
						info.params.secondList = resultNode;
					} else {
						info.expectedValue = resultNode;
					}

				}
			});
		} catch (IOException e) {
			throw new InvalidParameterException();
		}

		return info;
	}

	@Override
	protected boolean isValid(final String[] args) {

		return ComUtil.isNotEmpty(args) && FileUtil.isValidFile(args[PARAM_FILE_PATH]);
	}

	/**
	 * Param for this solution
	 * 
	 * @author KJack
	 *
	 */
	protected static class Param {

		/**
		 * First List
		 */
		private ListNode firstList;

		/**
		 * Second List
		 */
		private ListNode secondList;

		@Override
		public String toString() {
			
			StringBuffer sb = new StringBuffer();
			sb.append(firstList);
			sb.append(secondList);
			
			return sb.toString();
		}
	}
}
