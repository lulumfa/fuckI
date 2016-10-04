// O(lgn), space O(lgn)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null) return null;
        
        if((p.val <= root.val && q.val >= root.val) || (q.val <= root.val && p.val >= root.val) || (p.val == root.val) || (q.val == root.val)) return root;
        
        if(p.val < root.val && q.val < root.val) {
            if(root.left == null) return null;
            return lowestCommonAncestor(root.left, p, q);
        }
        else {
            if(root.right == null) return null;
            return lowestCommonAncestor(root.right, p, q);
        }
    }
}
