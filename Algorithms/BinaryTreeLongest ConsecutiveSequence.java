O(n), space(lgn) recursively

public class Solution {
    public int longestConsecutive(TreeNode root) {
        return root == null ? 0 : Math.max(dfs(root.left, 1, root.val), dfs(root.right, 1, root.val));
    }
    
    private int dfs(TreeNode root, int count, int val) {
        if(root == null) return count;
        int newCount = (root.val - 1 )== val ? count +1 : 1;
        return Math.max(count, Math.max(dfs(root.left, newCount, root.val), dfs(root.right, newCount, root.val)));
    }
}
