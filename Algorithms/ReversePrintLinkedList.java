// require O(n) space O(sqrt(n))

package Facebook;

import java.util.Stack;

public class ReversePrintLinkedList {
	public static void main(String[] args){
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		ListNode node6 = new ListNode(6);
		ListNode node7 = new ListNode(7);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		node6.next = node7;
		
		ReversePrintLinkedList ro = new ReversePrintLinkedList();
		
		ro.reversePrint(node1);
	}
	
	public void reversePrint(ListNode node) {
		if(node == null) return;
		int total = 0;
		ListNode iter = node;
		while(iter != null) {
			total++;
			iter = iter.next;
		}
		Stack<ListNode> stack = new Stack<ListNode>();
		int size = (int)(Math.sqrt(total));
		
		int count = 0;
		iter = node;
		stack.push(iter);
		while(iter != null) {
			count++;

			if(count > size) {
				stack.push(iter);
				count = 1;
			}
			iter = iter.next;
		}
		
		while(!stack.isEmpty()) {
			reversePrintKLength(stack.pop(), size);
		}
	}
	
	private void reversePrintKLength(ListNode node, int size) {
		if(node == null) return;
		if(size > 0) {
			reversePrintKLength(node.next, size -1);
			System.out.print("->" + node.val);
		}
	}
}
