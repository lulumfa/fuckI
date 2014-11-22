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
        if (head == null || head.next == null)
			return;

		// partition the list into 2 sublists of equal length
		ListNode slowNode = head, fastNode = head;
		while (fastNode.next != null) {
			fastNode = fastNode.next;
			if (fastNode.next != null) {
				fastNode = fastNode.next;
			} else {
				break;
			}
			slowNode = slowNode.next;
		}
		// 2 sublist heads
		ListNode head1 = head, head2 = slowNode.next;
		// detach the two sublists
		slowNode.next = null;

		// reverse the second sublist
		ListNode cur = head2, post = cur.next;
		cur.next = null;
		while (post != null) {
			ListNode temp = post.next;
			post.next = cur;
			cur = post;
			post = temp;
		}
		head2 = cur; // the new head of the reversed sublist

		// merge the 2 sublists as required
		ListNode p = head1, q = head2;
		while (q != null) {
		    if(p.next== null) {
		        p.next = q;
		        if(q.next!=null) q = q.next;
		        q.next = null;
		        break;
		    }
			ListNode temp1 = p.next;
			ListNode temp2 = q.next;
			p.next = q;
			q.next = temp1;
			p = temp1;
			q = temp2;
		}
    }
}
