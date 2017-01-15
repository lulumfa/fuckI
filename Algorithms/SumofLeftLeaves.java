//O(n), space O(height)  = O(n)

// iteratively also, but better sequence
public class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return 0;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        int sum = 0;
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(node.right != null) stack.push(node.right);
            if(node.left != null) {
                if(node.left.left == null && node.left.right == null) sum += node.left.val;
                else stack.push(node.left);
            }
        }
        return sum;
    }
}
 // iteratively
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
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return 0;
        int sum = 0;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(node.left != null) {
                if(node.left.left == null && node.left.right == null) sum += node.left.val;
                else stack.push(node.left);
            }
            if(node.right != null) stack.push(node.right);
        }
        
        return sum;
    }
}

// recursively

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
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return 0;
        int sum = 0;
        if(root.left != null) {
            if(root.left.left == null && root.left.right == null) {
                sum += root.left.val;
            } else {
                sum += sumOfLeftLeaves(root.left);
            }
        }
        if(root.right != null) {
            sum += sumOfLeftLeaves(root.right);
        }
        return sum;
    }
}
