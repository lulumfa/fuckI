// my own

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || n <1) return head;
        ListNode header = new ListNode(0);
        header.next = head;
        ListNode fast = header, slow = header;
        int i = 0;
        while(i< n && fast.next !=null) {
            fast = fast.next;
            i++;
        }
        
        while(fast.next!= null) {
            fast = fast.next;
            slow = slow.next;
        }
        ListNode temp = slow.next;
        slow.next = slow.next.next;
        temp.next = null;
        return header.next;
    }
}

//http://blog.csdn.net/linhuanmars/article/details/19778441

public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null || n<=0 ) return head;
        ListNode runner = head;
        int count = 0;
        while(runner!=null && count < n){
            runner = runner.next;
            count++;
        }
        if(runner==null && count==n) return head.next;
        if(runner == null) return head;
        ListNode walker = head;
        while(runner.next !=null){
            runner = runner.next;
            walker = walker.next;
        }
        walker.next = walker.next.next;
        return head;
    }
}

public ListNode removeNthFromEnd(ListNode head, int n) {
    if(head == null)
        return null;
    int i=0;
    ListNode runner = head;
    while(runner!=null && i<n)
    {
        runner = runner.next;
        i++;
    }
    if(i<n)
        return head;
    if(runner == null)
        return head.next;
    ListNode walker = head;
    while(runner.next!=null)
    {
        walker = walker.next;
        runner = runner.next;
    }
    walker.next = walker.next.next;
    return head;
}
