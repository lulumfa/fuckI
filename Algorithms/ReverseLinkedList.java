package leetcode;

// Time complexity is O(n), space O(1)
public class ReverseLinkedList {
	 public class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) {
	         val = x;
	          next = null;
	      }
	  }
	public ListNode reverseBetween(ListNode head, int m, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(head==null|| head.next==null || m==n) return head;
        ListNode pre, node;

        ListNode cover = new ListNode(1);
        cover.next = head;
        node = cover;
        int i=0;
        for(;i<m-1;i++){
            node = node.next;
        }
        pre =node;
        node = node.next;
        i++;
        while(i<n && node.next!=null){
            ListNode temp = node.next;
            node.next =node.next.next;
            temp.next = pre.next;
            pre.next = temp;
            
         
            i++;
        }
        return cover.next;
    }
}

// raw reverse
// Time complexity is O(n), space O(n), cause generation of stack by recursion

public Node reverse(Node current)
{
 if (current== null || current.next==null) return current;
 Node nextItem = current.next;
 current.next = null;
 Node reverseRest = reverse(nextItem); // the reverseRest will always be the original last one.
 nextItem.next = current; // nextItem will always be the one before the new last one
 return reverseRest;
}
