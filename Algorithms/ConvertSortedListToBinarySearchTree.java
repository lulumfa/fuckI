//整体过程就是一次中序遍历，时间复杂度是O(n)，空间复杂度是栈空间O(logn)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if(head==null) return null;
        ListNode temp = head;
        int count = 0;
        while(temp!=null) {
            count++;
            temp = temp.next;
        }
        ListNode[] list = {head}; // put it in a list so that we can update it in the fly
        return helper(list, 0, count-1);
    }
    private TreeNode helper(ListNode[] list, int left, int right) {
        if(left> right) return null;
        int mid = (left+right)/2;
        TreeNode leftNode = helper(list, left, mid-1);
        TreeNode root = new TreeNode(list[0].val);
        root.left = leftNode;
        list[0] = list[0].next;
        TreeNode rightNode = helper(list, mid+1, right);
        root.right = rightNode;
        return root;
    }
}

// reference: http://blog.csdn.net/linhuanmars/article/details/23904937

// O(nlgn), space O(n) actually more readable but slower than the one below
public class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if(head==null) return null;
        return toBST(head,null);
    }
    public TreeNode toBST(ListNode head, ListNode tail){
        ListNode slow = head;
        ListNode fast = head;
        if(head==tail) return null;

        while(fast!=tail&&fast.next!=tail){
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode thead = new TreeNode(slow.val);
        thead.left = toBST(head,slow);
        thead.right = toBST(slow.next,tail);
        return thead;
    }
}


