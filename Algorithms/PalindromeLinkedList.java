// O(n), spapce O(1), no extra space, with tests

package Facebook;
 
public class PalindromeLinkedList {
	
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(2);
//		ListNode node5 = new ListNode(1);

		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
//		node4.next = node5;
		
		PalindromeLinkedList pl = new PalindromeLinkedList();
		
		
		System.out.println(pl.isPalindrome(node1));
		ListNode node = node1;
		while(node!= null) {
			System.out.println(node.val + "->");
			node = node.next;
		}
	}
	
    public boolean isPalindrome(ListNode head) {
        
        ListNode fast = head, slow = head;
        
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        
        if(fast != null) slow = slow.next;
        
        slow = reverse(slow);
        ListNode slowHead = slow;
        fast = head;
        while(slow != null) {
            if(fast.val != slow.val) {
                reverse(slowHead);
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        
        reverse(slowHead);
        
        return true;
    }
    
    private ListNode reverse(ListNode node) {
        if(node == null) return null;
        ListNode header = new ListNode(0);
        header.next = node;
        while(node.next != null) {
            ListNode temp = node.next.next;
            node.next.next = header.next;
            header.next = node.next;
            node.next = temp;
        }
        
        return header.next;
    }
}

// O(n), space (n)

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null) return true;
        ListNode reversed = cloneReverseLinkedList(head);
        return areEqual(head, reversed);
    }
    
    private boolean areEqual(ListNode node1, ListNode node2){
        if(node1 == null || node2 ==null ) return false;
        
        while(node1 != null && node2 != null) {
            if(node1.val != node2.val) return false;
            node1 = node1.next;
            node2 = node2.next;
        }
        return node1 == null && node2 == null;
    }
    
    private ListNode cloneReverseLinkedList(ListNode head) {
        ListNode header = new ListNode(0);
        ListNode node = head;
        while(node != null) {
            ListNode temp = new ListNode(node.val);
            temp.next = header.next;
            header.next = temp;
            node = node.next
        }
        return header.next;
    }
}
