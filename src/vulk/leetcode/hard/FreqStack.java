package vulk.leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 
 * Basically the starting point
 * 
 * @author KJack
 *
 */
public class FreqStack {

	Map<Integer, Integer> freq;
	List<Stack<Integer>> stackByFreq;

	public FreqStack() {
		this.freq = new HashMap<Integer, Integer>();
		this.stackByFreq = new ArrayList<>();

	}

	public void push(int val) {

		int valFreq = this.freq.getOrDefault(val, 0) + 1;
		this.freq.put(val, valFreq);

		if (valFreq > stackByFreq.size()) {
			stackByFreq.add(new Stack<Integer>());
		}
		Stack<Integer> stack = stackByFreq.get(valFreq - 1);
		stack.push(val);

	}

	public int pop() {

		Stack<Integer> stack = stackByFreq.get(stackByFreq.size() - 1);

		int popVal = stack.pop();
		this.freq.put(popVal, this.freq.get(popVal) - 1);
		if (stack.isEmpty()) {
			stackByFreq.remove(stackByFreq.size() - 1);
		}

		return popVal;

	}

}
