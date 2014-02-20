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
        if(l1==null&&l2==null) return l1;
        if(l1==null) return l2;
        if(l2 ==null) return l1;
        ListNode res;
        if(l1.val<l2.val)
        {
            res = new ListNode(l1.val);
            l1 = l1.next;
        }
        else
        {
            res = new ListNode(l2.val);
            l2 = l2.next; 
        }
        ListNode sol = res;
        while(l1!=null && l2!=null)
        {
            if(l1.val<l2.val)
            {
                res.next = new ListNode(l1.val);
                l1 = l1.next;
            }
            else
            {
                res.next = new ListNode(l2.val);
                l2 = l2.next; 
            }
            res = res.next;
        }
        if(l1==null) res.next = l2;
        else res.next = l1;
        return sol;
        
    }
}
