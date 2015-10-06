// http://blog.csdn.net/linhuanmars/article/details/19948569



public ListNode swapPairs(ListNode head) {
    if(head == null)
        return null;
    ListNode helper = new ListNode(0);
    helper.next = head;
    ListNode pre = helper;
    ListNode cur = head;
    while(cur!=null && cur.next!=null)
    {
        ListNode next = cur.next.next;
        cur.next.next = cur;
        pre.next = cur.next;
        if(next!=null && next.next!=null)
            cur.next = next.next;
        else
            cur.next = next;
        pre = cur;
        cur = next;
    }
    return helper.next;
}

public class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head==null) return head;
        ListNode header = new ListNode(0);
        header.next = head;
        ListNode node = header;
        while(node.next!=null && node.next.next!=null){
            ListNode temp = node.next;
            node.next = temp.next;
            temp.next = node.next.next;
            node.next.next = temp;
            node = node.next.next;
        }
        return header.next;
    }
}
