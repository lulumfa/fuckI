//reference: http://blog.csdn.net/linhuanmars/article/details/19712593

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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l2==null) return l1;
        if(l1==null) return l2;
        
        ListNode header = new ListNode(0);
        ListNode node = header;
        ListNode node1 = l1;
        ListNode node2 = l2;
        while(node1!=null && node2!=null) {
            if(node1.val<=node2.val) {
                node.next = node1;
                node1= node1.next;
            } else {
                node.next = node2;
                node2 = node2.next;
            }
            node = node.next;
        }
        if(node1!=null) {
            node.next = node1;
        } else {
            node.next = node2;
        }
        
        return header.next;
    }
}

// iteractor

public static List<Integer> merge(List<Integer> listA, List<Integer> listB)
    {
        if (listA == null) return listB;
        if (listB == null) return listA;

        final ListIterator<Integer> iterA = listA.listIterator(0);
        final ListIterator<Integer> iterB = listB.listIterator(0);

        final List<Integer> result = new LinkedList<Integer>();
    
        merge(iterA, iterB, result);
        return result;
    }

    private static void merge(ListIterator<Integer> iterA,
                              ListIterator<Integer> iterB,
                              List<Integer>  result)
    {
        if (!iterA.hasNext())
        {
            while (iterB.hasNext()) { result.add(iterB.next()); }
            return;
        }

        if (!iterB.hasNext())
        {
            while (iterA.hasNext()) {result.add(iterA.next()); }
            return;
        }

        Integer a = iterA.next();
        Integer b = iterB.next();

        if (a < b)
        {
            result.add(a);
            b = iterB.previous(); // rewind
        }
        else if (b < a)
        {
            result.add(b);
            a = iterA.previous(); // rewind
        }
        else
        {
            result.add(a);
            result.add(b);
        }

        merge(iterA, iterB, result);
    }
