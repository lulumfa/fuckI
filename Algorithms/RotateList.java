//reference: http://blog.csdn.net/linhuanmars/article/details/21903027

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode rotateRight(ListNode head, int n) {
        if(head==null || n<0) return head;
        ListNode fast = head, slow = head;
        
        int count = 0;
        
        while(fast!=null && count<n) {
            fast = fast.next;
            count++;
        }
        
        if(fast==null) {
            n = n%count;
            fast = head;
            count =0;
            while(count<n) {
                fast = fast.next;
                count++;
            }
        }
        while(fast.next!=null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        fast.next = head;
        ListNode newHead = slow.next;
        slow.next = null;
        return newHead;
    }
}
