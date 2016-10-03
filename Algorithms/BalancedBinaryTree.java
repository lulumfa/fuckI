// my latest
// O(n), space O(lgn)

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
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) == Integer.MIN_VALUE ? false : true;
    }
    
    private int getHeight(TreeNode root) {
        if(root == null) return -1;
        int left = getHeight(root.left);
        if(left == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        int right = getHeight(root.right);
        if(right == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        
        int diff = right - left;
        if(Math.abs(diff) > 1) return Integer.MIN_VALUE;
        return Math.max(left, right) + 1;
    }
}

// my solution, reference: http://blog.csdn.net/linhuanmars/article/details/23731355
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
    public boolean isBalanced(TreeNode root) {
        return helper(root)!=-1;
    }
    private int helper(TreeNode node) {
        if(node==null) return 0;
        int left = helper(node.left);
        int right = helper(node.right);
        if(left==-1 || right==-1) return -1;
        if(Math.abs(left-right)>=2) return -1;
        return Math.max(left,right) +1;
    }
}


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
    public boolean isBalanced(TreeNode root) {  
        return height(root) != -1;  
    }  
  
    private int height(TreeNode root)  
    {  
        if(root == null)  
            return 0;  
  
        int leftHeight = height(root.left);  
        if(leftHeight == -1)  
            return -1;  
  
        int rightHeight = height(root.right);  
        if(rightHeight == -1)  
            return -1;  
  
        if(Math.abs(leftHeight - rightHeight) > 1)  
            return -1;  
  
        return 1 + Math.max(leftHeight, rightHeight);  
    }  
}  
