package vulk.leetcode.medium;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;

import vulk.leetcode.Solution;
import vulk.leetcode.common.ListNode;
import vulk.leetcode.medium.AddTwoNumber.Param;
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
public class AddTwoNumber extends Solution<Param, ListNode> {

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

		if (l1 == null && l2 == null) {
			return null;
		}
		int sum = getNodeVal(l1) + getNodeVal(l2);
		if (sum >= 10) {
			ListNode remainingNextNode = getNextNode(l1) == null ? getNextNode(l2) : getNextNode(l1);
			if (remainingNextNode == null) {
				ListNode remainingNode = l1 == null ? l2 : l1;
				remainingNode.next = new ListNode();
				remainingNextNode = remainingNode.next;
			}
			addVal(remainingNextNode, 1);
			sum -= 10;
		}

		return new ListNode(sum, addTwoNumbers(getNextNode(l1), getNextNode(l2)));
	}

	/**
	 * Retrieve the next node<br>
	 * 
	 * @param node desired node from which the next node is retrieved
	 * @return next node
	 */
	private ListNode getNextNode(ListNode node) {
		return node == null ? null : node.next;
	}

	/**
	 * Retrieve value of the Node<br>
	 * zero if the node is null<br>
	 * 
	 * @param node desired note from which data is retrieved
	 * @return value of the node
	 */
	private int getNodeVal(ListNode node) {
		return node == null ? 0 : node.val;
	}

	/**
	 * Add value to existing node<br>
	 * if no node is presented, new node shall be created<br>
	 * 
	 * @param node desired node to add value into
	 * @param val value to be added
	 * @return node with added value
	 */
	private ListNode addVal(ListNode node, int val) {
		if (node == null) {
			return new ListNode(val);
		} else {
			node.val += val;
			return node;
		}
	}

	// ===================================
	// The following code must not be posted on to LeetCode
	// ===================================
	@Override
	protected ListNode proc(Param param) {
		return addTwoNumbers(param.firstList, param.secondList);
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
