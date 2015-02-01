// reference: http://blog.csdn.net/linhuanmars/article/details/24389429

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
// my own simpler one 

public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null|| head.next==null) return head;
        
        ListNode header = new ListNode(0);
        ListNode pre = header;
        header.next = head;
        ListNode cur = pre.next;
        
        while(cur!=null) {
            while(cur!=null && pre.next.val==cur.val) {
                cur = cur.next;
            }
            if(pre.next.next == cur) {
                pre = pre.next;
            } else {
                pre.next = cur;
            }
        }
        return header.next;
    }
}
 
 
 
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null || head.next ==null) return head;
        
        ListNode header = new ListNode(0);
        header.next = head;
        ListNode pre = header;
        ListNode cur = head;
        
        while(cur!=null) {
            while(cur.next!=null && pre.next.val==cur.next.val) {
                cur = cur.next;
            }
            if(pre.next==cur) {
                pre = pre.next;        
            } else {
                pre.next = cur.next;
            }
            cur = cur.next;
            
        }
        return header.next;
    }
}
