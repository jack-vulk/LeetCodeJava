package vulk.leetcode.medium;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import vulk.leetcode.Solution;
import vulk.leetcode.common.ListNode;
import vulk.leetcode.medium.L82RemoveDuplicationFromSortList.Param;
import vulk.leetcode.util.ComUtil;
import vulk.leetcode.util.CsvParser;
import vulk.leetcode.util.ICsvParser;

/**
 * Solution for merge-two-sorted-lists on LeetCode
 * 
 * @author KJack
 *
 */
public class L82RemoveDuplicationFromSortList extends Solution<Param, ListNode> {

	final static int PARAM_INPUT = 1;
	final static int PARAM_OUTPUT = 2;

	public ListNode deleteDuplicates(ListNode head) {

		if (head == null) {
			return null;
		} else if (head.next == null) {
			return head;
		}
		ListNode prev = null;
		ListNode current = head;
		ListNode ahead = head.next;
		ListNode resultHead = null;
		ListNode result = null;

		while (current != null) {

			ListNode nextNode = getNoDup(prev, current, ahead);
			if (nextNode != null) {

				if (result == null) {
					result = nextNode;
				} else {
					result.next = nextNode;
					result = result.next;
				}

				resultHead = resultHead == null ? result : resultHead;

			}

			prev = current;
			current = ahead;
			ahead = ahead == null ? null : ahead.next;
		}

		return resultHead;
	}

	private ListNode getNoDup(ListNode prev, ListNode current, ListNode ahead) {

		if (prev != null && prev.val == current.val) {
			return null;
		}

		if (ahead != null && ahead.val == current.val) {
			return null;
		}

		return new ListNode(current.val);
	}

	// ===================================
	// The following code must not be posted on to LeetCode
	// ===================================
	@Override
	protected ListNode proc(Param param) throws ExecutionException {
		try {
			return deleteDuplicates(param.inputList);
		} catch (Exception e) {
			throw new ExecutionException(e);
		}
	}

	@Override
	protected ParsedInfo parseParam(String filePath) {

		ParsedInfo info = new ParsedInfo();
		info.params = new Param();

		try {
			CsvParser.parse(filePath, new ICsvParser() {

				@Override
				public void readLine(int lineNumber, List<String> data) {

					if (lineNumber > 2 || ComUtil.isEmpty(data)) {
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

					switch (lineNumber) {
					case PARAM_INPUT: {
						info.params.inputList = resultNode;
						break;
					}
					case PARAM_OUTPUT: {
						info.expectedValue = resultNode;
						break;
					}
					}

				}
			});
		} catch (IOException e) {
			throw new InvalidParameterException();
		}

		return info;
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
		private ListNode inputList;

		@Override
		public String toString() {
			
			StringBuffer sb = new StringBuffer();
			sb.append(inputList);
			
			return sb.toString();
		}
	}
}
