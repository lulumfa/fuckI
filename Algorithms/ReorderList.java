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
        ListNode slow = head, fast = head;
        while(fast.next!=null) {
            fast = fast.next;
            if(fast.next!=null) {
                fast = fast.next;
            }else {
                break;
            }
            slow = slow.next;
        }
        ListNode head1 = head;
        ListNode head2 = slow.next;
        slow.next = null;
        ListNode pin = head2.next;
        head2.next = null;
        ListNode temp;
        while(pin!=null) {
            temp = pin.next;
            pin.next = head2;
            head2 = pin;
            pin = temp;
        }
        
        //combime the 2. q has equal or longer list
        ListNode p = head1;
        ListNode q = head2;
        while(q!=null) {
            ListNode pnext = p.next;
            ListNode qnext = q.next;
            p.next = q;
            q.next = pnext;
            p = pnext;
            q = qnext;
        }
    }
}
