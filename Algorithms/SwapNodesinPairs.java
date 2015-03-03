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
