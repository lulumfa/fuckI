// hardest part:         head.next =null;


// my solution
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode sortList(ListNode head) {
        if(head==null || head.next==null) return head;
        int len = getLength(head);
        int partition = len/2;
        ListNode left = head;
        ListNode right;
        int count = 0;
        while(count<partition-1) {
            head = head.next;
            count++;
        }
        right = head.next;
        head.next =null;
        left = sortList(left);
        right = sortList(right);
        return merge(left, right);
    }
    
    public int getLength(ListNode node) {
        int res = 0;
        while(node!=null) {
            res++;
            node = node.next;
        }
        return res;
    }
    
    private ListNode merge(ListNode node1, ListNode node2) {
        if(node1==null) return node2;
        if(node2==null) return node1;
        
        ListNode header = new ListNode(0);
        ListNode temp = header;
        while(node1!=null && node2!=null) {
            if(node1.val<=node2.val) {
                temp.next = node1;
                node1 = node1.next;
            } else {
                temp.next = node2;
                node2 = node2.next;
            }
            temp = temp.next;
        }
        if(node1!=null) {
            temp.next = node1;
        } else {
            temp.next = node2;
        }
        return header.next;
    }
}
