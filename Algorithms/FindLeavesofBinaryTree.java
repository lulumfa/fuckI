// O(n), space O(n) worst case

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
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null) return res;
        dfsRemoveAndGetHeight(root, res);
    
        return res;
    }
    
    private int dfsRemoveAndGetHeight(TreeNode root, List<List<Integer>> res) {
        if(root == null) return -1;
        int height = 1 + Math.max(dfsRemoveAndGetHeight(root.left, res), dfsRemoveAndGetHeight(root.right, res));
        if(res.size() <= height) res.add(new ArrayList<Integer>());
        res.get(height).add(root.val);
        root.left = null;
        root.right = null;
        return height;
    }
}
