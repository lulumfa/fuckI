// O(n), space O(H) hight, worst case n

class Solution {
    int maxDepth = 0;
    TreeNode lca = null;
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        dfs(root, 1);
        return lca;
    }
    
    private int dfs(TreeNode root, int depth) {
        if (root == null) {
            return 0;
        }
        // find a leaf, check if the leaf becomes the deepest leaf up until now
        if (root.left == null && root.right == null) {
            if (depth >= maxDepth) {
                lca = root;
                maxDepth = depth;
            }
            return depth;
        }
		// get the depth of deepest leaves on two sides
        int leftMax = dfs(root.left, depth + 1);
        int rightMax = dfs(root.right, depth + 1);
        // check the depth of deepest leaves on two sides
        // modify lca if leftMax = rightMax = maxDepth
        if (leftMax == rightMax && leftMax == maxDepth) {
            lca = root;
        }
        return Math.max(leftMax, rightMax);
    }
}
