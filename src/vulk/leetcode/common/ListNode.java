package vulk.leetcode.common;

import java.util.List;

import vulk.leetcode.util.ComUtil;

public class ListNode {

	public int val;
	public ListNode next;

	public ListNode() {
	}

	public ListNode(int val) {
		this.val = val;
	}

	public ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(String.valueOf(val));

		ListNode next = this.next;

		while (next != null) {

			sb.append(",");
			sb.append(next.val);

			next = next.next;

		}

		sb.append("]");

		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ListNode)) {
			return false;
		}

		ListNode castedObj = (ListNode) obj;

		ListNode currentNode = this;
		ListNode castNode = castedObj;

		do {

			if (currentNode.val != castNode.val) {
				return false;
			}

			currentNode = currentNode.next;
			castNode = castNode.next;
		} while (currentNode != null);

		return ComUtil.isEmpty(castNode);
				
	}

	/**
	 * Parse list of numeric data to ListNode
	 * 
	 * @param data list of numeric data
	 * @return head of the list
	 */
	public static ListNode parse(List<String> data) {

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

		return resultNode;
	}
}
