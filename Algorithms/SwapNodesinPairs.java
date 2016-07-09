// http://blog.csdn.net/linhuanmars/article/details/19948569

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
