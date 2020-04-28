// O(n), runtime and space
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        int[] ans = {1};
        depth(root, ans);
        return ans[0] - 1;
    }
    public int depth(TreeNode node, int[] ans) {
        if (node == null) return 0;
        int L = depth(node.left, ans);
        int R = depth(node.right, ans);
        ans[0] = Math.max(ans[0], L+R+1);
        return Math.max(L, R) + 1;
    }
}
