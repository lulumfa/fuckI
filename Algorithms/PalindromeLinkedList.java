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
