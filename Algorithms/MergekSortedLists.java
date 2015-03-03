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
