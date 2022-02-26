package vulk.leetcode.easy;

import vulk.leetcode.util.ComUtil;

public class ListNode {

	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
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
}
