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
    public int maxPathSum(TreeNode root) {
        if(root==null) return 0;
        int[] max = {Integer.MIN_VALUE};
        recursion(root,max);
        return max[0];
    }
    public int recursion(TreeNode node, int[] max)
    {
        if(node==null) return 0;
        int current = node.val;
        int leftLineMax = recursion(node.left, max);
        int rightLineMax = recursion(node.right, max);
        int maxLeftRight = Math.max(leftLineMax, rightLineMax);
        int maxLine = Math.max(maxLeftRight+current, current);
        max[0] = Math.max(max[0], Math.max(maxLine,leftLineMax+rightLineMax+current));
        return maxLine;
    }
}
