package vulk.leetcode.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import vulk.leetcode.Solution;
import vulk.leetcode.common.Node;
import vulk.leetcode.medium.L138CopyListWithRandomPointer.Param;

/**
 * Solution for merge-two-sorted-lists on LeetCode
 * 
 * @author KJack
 *
 */
public class L138CopyListWithRandomPointer extends Solution<Param, Node> {

	final static int PARAM_INPUT = 1;
	final static int PARAM_OUTPUT = 2;

	public Node copyRandomList(Node head) {

		Node resultHead = null;
		Node result = null;
		Node currentHead = head;
		Map<Node, Node> clonedNodes = new HashMap<>();

		while (currentHead != null) {

			if (result == null) {
				result = new Node(currentHead.val);
			} else {
				result.next = new Node(currentHead.val);
				result = result.next;
			}

			if (resultHead == null) {
				resultHead = result;
			}

			clonedNodes.put(currentHead, result);

			currentHead = currentHead.next;

		}

		// Traverse again to assign random
		currentHead = head;
		result = resultHead;
		while (currentHead != null) {

			if (currentHead.random != null) {
				result.random = clonedNodes.get(currentHead.random);
			}
			result = result.next;
			currentHead = currentHead.next;
		}

		return resultHead;

	}

	// ===================================
	// The following code must not be posted on to LeetCode
	// ===================================
	@Override
	protected Node proc(Param param) throws ExecutionException {
		try {
			return copyRandomList(param.inputList);
		} catch (Exception e) {
			throw new ExecutionException(e);
		}
	}

	@Override
	protected ParsedInfo parseParam(String filePath) {

		ParsedInfo info = new ParsedInfo();

		// TODO: implement later
		// currently hardcoding
		Node inputNode = new Node(7);
		inputNode.random = null;
		info.params.inputList = inputNode;

		inputNode.next = new Node(13);
		inputNode.next.random = inputNode;

		info.expectedValue = info.params.inputList;

		return info;
	}

	/**
	 * Param for this solution
	 * 
	 * @author KJack
	 *
	 */
	protected static class Param {

		private Node inputList;

		@Override
		public String toString() {
			
			StringBuffer sb = new StringBuffer();
			sb.append(inputList);
			
			return sb.toString();
		}
	}
}
