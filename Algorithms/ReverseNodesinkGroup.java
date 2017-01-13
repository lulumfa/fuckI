// O(n), constant space

// borrowed but really good
public ListNode reverseKGroup(ListNode head, int k) {
        int n = 0;
        for (ListNode i = head; i != null; n++, i = i.next);
        
        ListNode dmy = new ListNode(0);
        dmy.next = head;
        for(ListNode prev = dmy, tail = head; n >= k; n -= k) {
            for (int i = 1; i < k; i++) {
                ListNode next = tail.next.next;
                tail.next.next = prev.next;
                prev.next = tail.next;
                tail.next = next;
            }
            
            prev = tail;
            tail = tail.next;
        }
        return dmy.next;
    }

// my own
// O(n), constant space
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k <=1) return head;
        
        ListNode header = new ListNode(0);
        header.next = head;
        ListNode node = header;
        
        int count = 0;
        ListNode pre = header;
        while(node!=null) {
            node = node.next;
            
            if(count == k){
                pre = reverse(pre, node);
                count = 0;
            }
            count++;
        }
        return header.next;
    }
    
    private ListNode reverse(ListNode start, ListNode end){
        ListNode head = start.next;
        ListNode cur = start.next.next;
        while(cur != end){
            ListNode next = cur.next;
            cur.next = start.next;
            start.next = cur;
            cur = next;
        }
        head.next = end;
        return head;
    }
}

// http://blog.csdn.net/linhuanmars/article/details/19957455

public ListNode reverseKGroup(ListNode head, int k) {
    if(head == null)
    {
        return null;
    }
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    int count = 0;
    ListNode pre = dummy;
    ListNode cur = head;
    while(cur != null)
    {
        count ++;
        ListNode next = cur.next;
        if(count == k)
        {
            pre = reverse(pre, next);
            count = 0;   
        }
        cur = next;
    }
    return dummy.next;
}
private ListNode reverse(ListNode pre, ListNode end)
{
    if(pre==null || pre.next==null)
        return pre;
    ListNode head = pre.next;
    ListNode cur = pre.next.next;
    while(cur!=end)
    {
        ListNode next = cur.next;
        cur.next = pre.next;
        pre.next = cur;
        cur = next;
    }
    head.next = end;
    return head;
}
