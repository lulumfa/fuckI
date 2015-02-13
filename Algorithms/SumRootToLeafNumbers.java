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
        if(root==null) return 0;
        int[] res = new int[1];    
        helper(root, res, 0);
        return res[0];
    }
    
    private void helper(TreeNode root, int[] res, int sum) {
        sum = sum*10+root.val;
        if(root.left==null && root.right==null) res[0]+=sum;
        if(root.left!=null) helper(root.left, res, sum);
        if(root.right!=null) helper(root.right, res, sum);
    }
}

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
