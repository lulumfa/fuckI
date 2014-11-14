package leetcode;

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
    public ListNode insertionSortList(ListNode head) {
        if(head==null || head.next==null) return head;
        ListNode cap = new ListNode(0);
        ListNode temp = cap;
        ListNode next;
        while(head!=null) {
            while(temp.next!=null && temp.next.val <=head.val) {
                temp = temp.next;
            }
            next = head.next;
            head.next = temp.next;
            temp.next = head;
            head = next;
            temp = cap;
        }
        return cap.next;
    }
}
//More efficient in practice than most other simple quadratic (i.e., O(n2)) 
//algorithms such as selection sort or bubble sort; the best case (nearly sorted input) is O(n)
public class InsertionSortList.java  {
	// Definition for singly-linked list.
	  public class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) {
	          val = x;
	          next = null;
	      }
	 }
	 public static void main(String args[]){
		 Solution solution =new Solution();
		 ListNode start = null;
//		 ListNode start = solution.new ListNode(3);
//		 ListNode node1 = solution.new ListNode(1);
//		 ListNode node2 = solution.new ListNode(5);
//		 ListNode node3 = solution.new ListNode(4);
//		 ListNode node4 = solution.new ListNode(2);
//		 ListNode node5 = solution.new ListNode(1);
//		 ListNode node6 = solution.new ListNode(1);
//		 ListNode node7 = solution.new ListNode(1);
//		 ListNode node2 = solution.new ListNode(1);
//		 ListNode node3 = solution.new ListNode(1);
//		 ListNode node2 = solution.new ListNode(1);
//		 ListNode node3 = solution.new ListNode(1);
//		 ListNode node2 = solution.new ListNode(1);
//		 ListNode node3 = solution.new ListNode(1);
//		 ListNode node2 = solution.new ListNode(1);
//		 ListNode node3 = solution.new ListNode(1);
//		 start.next = node1;
//		node1.next = node2;
//		node2.next = node3;
//		node3.next = node4;
		ListNode ans = solution.insertionSortList(start);
		while(ans!=null){
			System.out.println(ans.val);
			ans = ans.next;
		}
	 }
	
	 public ListNode insertionSortList(ListNode head) {
	
		 	if(head==null){
		 		return null;
		 	}
		 	if(head.next==null){
	      		return head;
	      	}
		 	ListNode current = head;
		 	while(current.next!=null){
		 		if(current.val<=current.next.val){
		 			current = current.next;
		 		}else{
		 			ListNode insert = current.next;
		 			current.next = current.next.next;
		 			ListNode iter = head;
		 			
		 			if(head.val>insert.val){
	 					insert.next = head;
	 					head = insert;
	 					continue;
	 				}
		 			while(iter!=current){
		 				if(iter.next.val>insert.val){
		 					insert.next = iter.next;
		 					iter.next = insert;
		 					break;
		 				}
		 				iter = iter.next;
		 			}
		 			
		 		}
		 		
		 	}
		 	
		 	return head;
	 }
}
