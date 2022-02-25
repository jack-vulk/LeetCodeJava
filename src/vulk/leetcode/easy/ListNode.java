package vulk.leetcode.easy;

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
}
