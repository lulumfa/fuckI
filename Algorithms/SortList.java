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
 
 // time nlogn, space constant
public class Solution {
    public ListNode sortList(ListNode head) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
           if (head == null) {
            return head;
        }
		// Iterate ll and find the total num of nodes., ll means linkedlist
        int count = length(head);
        return mergeSortUtil(head, count);
    }

	// Divide linkedlist to two recursively.
    public static ListNode mergeSortUtil(ListNode head, int count) {
        if (count <= 1) {
            return head;
        }
		// Cut ll into two.
        int middle = count / 2, ruler = 1;
        ListNode prev = head;
        ListNode cursor = head.next;
        while (middle != ruler) {
            prev = prev.next;
            cursor = cursor.next;
			++ruler;
        }
        prev.next = null;
		// Recusively merge left and right part of ll.
        ListNode left = mergeSortUtil(head, middle);
        ListNode right = mergeSortUtil(cursor, count - middle);
		// Merge two sorted ll.
        return mergeLinkedList(left, right);
    }

	// Merge two sorted linkedlist.
    public static ListNode mergeLinkedList(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(0);
        ListNode cursor = dummy;
		// Append node in ascending order til one of the pointers becomes null.
        while (left != null && right != null) {
			if (left.val <= right.val) {
				cursor.next = new ListNode(left.val);
				left = left.next;
			} else {
				cursor.next = new ListNode(right.val);
				right = right.next;
			}
			cursor = cursor.next;
		}
		// Append non-null pointer to the end.
		if (left != null) {
			cursor.next = left;	
		}
		if (right != null) {
			cursor.next = right;	
		}
        return dummy.next;
    }
    	// Helper function to get the length of linkedlist.
	public static int length(ListNode head) {
		if (head == null) {
			return 0;	
		} else {
			int count = 0;
			while (head != null) {
				head = head.next;
				++count;
			}
			return count;
		}
	}
	
	//Solution2
	       public ListNode sortList(ListNode head) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
         if(head == null)
            return null;
		int length = GetListLength(head);
		if(length == 1){
			return head;
		}
		if(isSorted(head))
		    return head;
		Random rand = new Random();
		int pivotIndex = rand.nextInt(length-1);
		ListNode less = null;
		ListNode lessHead = null;
		ListNode greater = null;
		ListNode greaterHead = null;
		ListNode node = head;
		ListNode pivotNode = GetIndexAt(head, pivotIndex);
		int pivotValue = pivotNode.val;
		while(node != null){
		    if(node == pivotNode){
				node = node.next;
				continue;
		    }
			if(node.val <= pivotValue ){
				if(less == null){
					less = node;
					lessHead = node;
				}
				else{
					less.next = node;
					less = less.next;
				}
			}
			else if(node.val > pivotValue){
				if(greater == null){
					greater = node;
					greaterHead = node;
				}
				else{
					greater.next = node;
					greater = greater.next;
				}
			}
			node = node.next;
		}
		
		if(less != null)
			less.next = null;
		if(greater != null)
			greater.next = null;
		
		//System.out.println("PivotValue is " + pivotValue);
		//System.out.println("Less list is");
		//ListNode.listToString(lessHead);
		//System.out.println("Greater list is");
		//ListNode.listToString(greaterHead);
		
		less = sortList(lessHead);
		greater = sortList(greaterHead);
		
		//System.out.println("Less list sorted is");
		//ListNode.listToString(less);
		//System.out.println("Greater list sorted is");
		//ListNode.listToString(greater);
		if(less == null){
			node = pivotNode;
			less = node;
		}
		else{
			node = less;
			while(node.next != null){
				node = node.next;
			}
			node.next = pivotNode;
		}
		pivotNode.next = greater;
        return less;
	}
	
	public int GetListLength(ListNode head){
		int length = 1;
		ListNode node;
		node = head;
		while(node.next!=null){
			node = node.next;
			length++;
		}
		return length;
	}
	
	public ListNode GetIndexAt(ListNode head, int n){
		ListNode result = head;
		for(int i=0 ;i<n; i++){
			result = result.next;
			if(result == null)
				return null;
		}
		return result;
	}
	
	public boolean isSorted(ListNode head){
		ListNode node = head;
		while(node != null && node.next != null){
			if(node.next.val < node.val){
				return false;
			}
			node = node.next;
		}
		
		return true;
	}

}
