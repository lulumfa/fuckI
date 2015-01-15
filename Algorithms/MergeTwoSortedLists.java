//reference: http://blog.csdn.net/linhuanmars/article/details/19712593

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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l2==null) return l1;
        if(l1==null) return l2;
        
        ListNode header = new ListNode(0);
        ListNode node = header;
        ListNode node1 = l1;
        ListNode node2 = l2;
        while(node1!=null && node2!=null) {
            if(node1.val<=node2.val) {
                node.next = node1;
                node1= node1.next;
            } else {
                node.next = node2;
                node2 = node2.next;
            }
            node = node.next;
        }
        if(node1!=null) {
            node.next = node1;
        } else {
            node.next = node2;
        }
        
        return header.next;
    }
}
