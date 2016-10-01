//O(n2) space O(n)

// we can simply copy it to an array and sort with lgn and in place O(n) space

package Leetcode;

import java.util.Stack;

public class SortStack {
	
	public static void main(String[] args) {
		SortStack ss = new SortStack();
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(3);
		stack.push(4);
		stack.push(3);
		stack.push(5);
		stack.push(1);
		System.out.println(ss.sort(stack));
	}
	
	public Stack<Integer> sort(Stack<Integer> stack) {
		Stack<Integer> copy = new Stack<Integer>();
		while(!stack.isEmpty()) {
			int temp = stack.pop();
			while(!copy.isEmpty() && copy.peek() > temp) {
				stack.push(copy.pop());
			}
			copy.push(temp);
		}
		System.out.println(copy);
		while(!copy.isEmpty()) {
			stack.push(copy.pop());
		}
		
		return stack;
	}
}
