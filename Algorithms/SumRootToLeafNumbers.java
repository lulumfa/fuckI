// reference: http://blog.csdn.net/linhuanmars/article/details/22913699

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
    public int sumNumbers(TreeNode root) {
        return dfsHelper(root, 0);
    }
    
    private int dfsHelper(TreeNode node, int sum) {
        if(node==null) return 0;
        if(node.left==null && node.right==null) {
            return sum*10+ node.val;
        }
        return dfsHelper(node.left, sum*10+node.val) + dfsHelper(node.right, sum*10 + node.val);
    }
}
