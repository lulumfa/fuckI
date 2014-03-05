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
    public ListNode partition(ListNode head, int x) {
        if(head==null) return head;
        ListNode front = new ListNode(0);
        ListNode tail = new ListNode(0);
        ListNode temp1 = front;
        ListNode temp2 = tail;
        ListNode iter = head;
        while(iter!=null)
        {
            if(iter.val<x)
            {
                temp1.next = iter;
                temp1 = temp1.next;
            }
            else
            {
                temp2.next = iter;
                temp2 = temp2.next;
            }
            iter = iter.next;
        }
        temp1.next = tail.next;
        temp2.next=null;
        return front.next;
    }
}
