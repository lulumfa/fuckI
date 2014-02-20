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
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if(head ==null|| head.next==null) return;
        int count =0;
        ListNode temp = head;
        while(temp!=null){
            count++;
            temp = temp.next;
        }
        int partition;
        if(count%2==0) partition = count/2-1;
        else partition = count/2;
        ListNode cursor = head;
        for(int i=0;i<partition;i++){
            cursor = cursor.next;
        }
        ListNode sec = cursor.next;
        cursor.next=null;
        sec = reverse(sec);
        //merge
        cursor = head;
        while(sec!=null){
            ListNode tmp1 = cursor.next;
            ListNode tmp2 = sec.next;
            cursor.next= sec;
            sec.next = tmp1;
            sec = tmp2;
            cursor = cursor.next.next;
        }
    }
    public ListNode reverse(ListNode node){
        if(node==null ||node.next==null )return node;
        ListNode pre = new ListNode(1);
        pre.next = node;
        while(node.next!=null){
            ListNode temp = node.next;
            node.next = node.next.next;
            temp.next = pre.next;
            pre.next = temp;
        }
        return pre.next;
    }
}
