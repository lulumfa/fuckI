// O(n), space O(n)

public class Solution {
    public int shortestLeafToLeafPath(TreeNode root) {
        int[] min = {Integer.MAX_VALUE};
        shortedDownLine(min, root);
        return min[0];
    }
    
    private int shortedDownLine(int[] min, TreeNode root) {
        if(root==null) return 0;
        int l = shortedDownLine(min, root.left);
        int r = shortedDownLine(min, root.right);
        int newSum = l + r + 1;
        if(l!=0 && r!=0) min[0] = Math.min(min[0], newSum);
        return Math.min(l, r) + 1;
    }
}
