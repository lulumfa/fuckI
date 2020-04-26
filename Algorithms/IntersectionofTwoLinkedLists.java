// normal way
// O(n) ,space (1)
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
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lA=0,lB=0;
        ListNode pA=headA, pB=headB;
        if(headA==null||headB==null)    return null;
        while(pA!=null){
            lA++;
            pA=pA.next;
        }
        while(pB!=null){
            lB++;
            pB=pB.next;
        }
        for(int i=0;i<Math.abs(lA-lB);i++){
            if(lA<lB)
                headB=headB.next;
            else 
                headA=headA.next;
        }
        while(headA!=null&&headB!=null){
            if(headA==headB) return headA;
            headA=headA.next;
            headB=headB.next;
        }
        return null;
    }
}

// O(n), space O(n) hashset
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> common = new HashSet<>();
        ListNode a = headA;
        while(a != null){
            common.add(a);
            a = a.next;
        }
        a = headB;
        while(a != null && !common.contains(a)){
            a = a.next;
        }
        return a;
    }
}

// smart wayI found most solutions here preprocess linkedlists to get the difference in len.
Actually we don't care about the "value" of difference, we just want to make sure two pointers reach the intersection node at the same time.

We can use two iterations to do that. In the first iteration, we will reset the pointer of one linkedlist to the head of another linkedlist after it reaches the tail node. In the second iteration, we will move two pointers until they points to the same node. Our operations in first iteration will help us counteract the difference. So if two linkedlist intersects, the meeting point in second iteration must be the intersection point. If the two linked lists have no intersection at all, then the meeting pointer in second iteration must be the tail node of both lists, which is null
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    //boundary check
    if(headA == null || headB == null) return null;
    
    ListNode a = headA;
    ListNode b = headB;
    
    //if a & b have different len, then we will stop the loop after second iteration
    while( a != b){
    	//for the end of first iteration, we just reset the pointer to the head of another linkedlist
        a = a == null? headB : a.next;
        b = b == null? headA : b.next;    
    }
    
    return a;
}

// http://www.cnblogs.com/yuzhangcmu/p/4128794.html


public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        
        ListNode pA = headA;
        ListNode pB = headB;
        
        ListNode tailA = null;
        ListNode tailB = null;
        
        while (true) {
            if (pA == null) {
                pA = headB;
            }
            
            if (pB == null) {
                pB = headA;
            }
            
            if (pA.next == null) {
                tailA = pA;
            }
            
            if (pB.next == null) {
                tailB = pB;
            }
            
            //The two links have different tails. So just return null;
            if (tailA != null && tailB != null && tailA != tailB) {
                return null;
            }
            
            if (pA == pB) {
                return pA;
            }
            
            pA = pA.next;
            pB = pB.next;
        }
    }
