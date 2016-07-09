// my own

//public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length ==0) return null;
        return merge(lists, 0, lists.length-1);
        
    }

    private ListNode merge(ListNode[] lists, int left, int right){
        if(left == right) return lists[left];
        if(left+ 1 == right) return mergeTwoLists(lists[left], lists[right]);
        int mid = (left+right)/2;
        return mergeTwoLists(merge(lists, left, mid), merge(lists, mid+1, right));
    }
    
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        
        ListNode node1 = l1;
        ListNode node2 = l2;
        
        ListNode header = new ListNode(0);
        ListNode node = header;
        
        while(node1 != null && node2 != null) {
            if(node1.val < node2.val){
                node.next = new ListNode(node1.val);
                node1= node1.next;
            } else {
                node.next = new ListNode(node2.val);
                node2 = node2.next;
            }
            node = node.next;
        }
        
        if(node1 == null) node.next = node2;
        else node.next = node1;
        return header.next;
    }
}

// reference: http://blog.csdn.net/linhuanmars/article/details/19899259
//那么运行时间满足递推式T(k) = 2T(k/2)+O(n*k)。根据主定理，可以算出算法的总复杂度是O(nklogk)。
//如果不了解主定理的朋友，可以参见主定理-维基百科。空间复杂度的话是递归栈的大小O(logk)。

public ListNode mergeKLists(ArrayList<ListNode> lists) {
    if(lists==null || lists.size()==0)
        return null;
    return helper(lists,0,lists.size()-1);
}
private ListNode helper(ArrayList<ListNode> lists, int l, int r)
{
    if(l<r)
    {
        int m = (l+r)/2;
        return merge(helper(lists,l,m),helper(lists,m+1,r));
    }
    return lists.get(l);
}
private ListNode merge(ListNode l1, ListNode l2)
{ 
    ListNode dummy = new ListNode(0);
    dummy.next = l1;
    ListNode cur = dummy;
    while(l1!=null && l2!=null)
    {
        if(l1.val<l2.val)
        {
            l1 = l1.next;
        }
        else
        {
            ListNode next = l2.next;
            cur.next = l2;
            l2.next = l1;
            l2 = next;
        }
        cur = cur.next;
    }
    if(l2!=null)
        cur.next = l2;
    return dummy.next;
}
