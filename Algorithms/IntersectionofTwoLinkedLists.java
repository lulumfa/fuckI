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
