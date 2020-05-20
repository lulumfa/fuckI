//reference: http://blog.csdn.net/linhuanmars/article/details/21503215

//总的时间复杂度还是O(n)，由于过程中没有用到额外空间，所以空间复杂度O(1)


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
    public void reorderList(ListNode head) {
        if(head==null || head.next==null) return;
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast.next!=null) {
            fast = fast.next;
            if(fast.next!=null) fast = fast.next;
            slow = slow.next;
        }
        ListNode head2 = slow.next;
        slow.next = null;
        ListNode head1 = head;
        
        ListNode header = new ListNode(0);
        ListNode node = head2;
        while(node!=null) {
            ListNode temp = node.next;
            node.next = header.next;
            header.next = node;
            node = temp;
        }
        head2 = header.next;
        ListNode node1 = head1;
        ListNode node2 = head2;
        while( node2!=null) {
            ListNode temp = node2.next;
            node2.next = node1.next;
            node1.next = node2;
            node1 = node2.next;
            node2 = temp;
        }
    }
}

    private ListNode recursion(ListNode head) {
        if(head==null || head.next==null) return head;
        return helper(head, head.next);
    }
    
    private ListNode helper(ListNode cur, ListNode next) {
        if(next==null) return cur;
        ListNode newHead = helper(next, next.next);
        next.next = cur;
        cur.next = null;
        return newHead;
    }
