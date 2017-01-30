// O(lgn) - O(n), space O(1)
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null) return null;
        
        TreeNode cur = root;
        
        while(cur != null) {
            if(p.val <= cur.val && q.val >= cur.val || p.val >= cur.val && q.val <= cur.val) return cur;
            if(p.val < cur.val) cur = cur.left;
            else cur = cur.right;
        }
        return null;
    }
}

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
