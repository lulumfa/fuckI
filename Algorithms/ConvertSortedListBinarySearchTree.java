// reference: http://blog.csdn.net/linhuanmars/article/details/23904937

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
        ListNode[] list = {head};
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
