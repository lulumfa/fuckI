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
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null || head.next==null) return head;
        ListNode node = head;
        
        while(node.next!=null) {
            while(node.next!=null && node.next.val==node.val) {
                node.next = node.next.next;
            }
            node = node.next;
            if(node==null) break;
        }
        return head;
    }
}
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null || head.next==null) return head;
        
        ListNode pre = head;
        ListNode cur = head.next;
        
        while(cur!=null) {
            if(pre.val==cur.val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;

        }
        return head;
    }
}
